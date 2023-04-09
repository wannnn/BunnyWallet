package com.cj.bunnywallet.utils

import com.cj.bunnywallet.BuildConfig
import com.cj.bunnywallet.KEY_MNEMONIC
import com.cj.bunnywallet.datasource.BunnyPreferencesDataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import org.web3j.crypto.Bip32ECKeyPair
import org.web3j.crypto.Bip44WalletUtils
import org.web3j.crypto.Credentials
import org.web3j.crypto.MnemonicUtils
import org.web3j.protocol.Web3j
import org.web3j.protocol.core.DefaultBlockParameterName
import org.web3j.protocol.http.HttpService
import org.web3j.utils.Convert
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Web3jManager @Inject constructor(
    private val dataStore: BunnyPreferencesDataStore,
    private val cryptoManager: CryptoManager,
) {
    private var credentials: Credentials? = null

    private var _address: String = ""
    val address get() = _address

    fun initWeb3j() {
        web3j = Web3j.build(HttpService(ALCHEMY_URL))

        runCatching { web3j.web3ClientVersion().send() }
            .onSuccess {
                if (it.hasError()) {
                    Timber.d(message = "Connected to Ethereum Failed: ${it.error.message}")
                } else {
                    Timber.d(message = "Connected to Ethereum")
                }
            }
            .onFailure {
                Timber.d(message = "Connected to Ethereum Exception: ${it.message}")
            }
    }

    private fun loadCredentials() = dataStore.getString(KEY_MNEMONIC)
        .map { encryptedMnemonic ->
            val mnemonic = cryptoManager.decrypt(encryptedMnemonic)
                ?: throw ArithmeticException("Mnemonic decrypted fail")
            Bip44WalletUtils.loadBip44Credentials("", mnemonic).let {
                credentials = it
                _address = it.address
                Timber.d(message = "Address: $_address")
            }
        }
        .catch { Timber.d(message = "Load Credential fail: ${it.message}") }
        .flowOn(Dispatchers.IO)

    @OptIn(FlowPreview::class)
    fun getEthBalance() =
        if (credentials == null) {
            loadCredentials().flatMapConcat { getBalance() }
        } else {
            getBalance()
        }

    private fun getBalance() = flow {
        val balanceWei = web3j.ethGetBalance(address, DefaultBlockParameterName.LATEST)
            .send()
            .balance
        Timber.d(message = "Balance(Wei): $balanceWei")

        val balanceEther = Convert.fromWei(balanceWei.toString(), Convert.Unit.ETHER)
        Timber.d(message = "Balance(ETH): $balanceEther")

        emit(balanceEther)
    }
        .flowOn(Dispatchers.IO)

    /**
     *  Mnemonic Code Converter
     *  https://iancoleman.io/bip39/#english
     */
    fun deriveChild(mnemonic: String, childNumber: Int) {
        val seed = MnemonicUtils.generateSeed(mnemonic, "")
        val masterKeypair = Bip32ECKeyPair.generateKeyPair(seed)
        val path = intArrayOf(
            BIP44 or Bip32ECKeyPair.HARDENED_BIT,
            COIN_ETH or Bip32ECKeyPair.HARDENED_BIT,
            0 or Bip32ECKeyPair.HARDENED_BIT,
            0,
            childNumber,
        )
        val childKeypair = Bip32ECKeyPair.deriveKeyPair(masterKeypair, path)
        Credentials.create(childKeypair).let { c ->
            Timber.d(message = "credentials address: ${c.address}")
        }
    }

    companion object {
        lateinit var web3j: Web3j

        private const val BIP44 = 44
        private const val COIN_ETH = 60

        private const val ALCHEMY_URL =
            "https://${BuildConfig.ETH_DOMAIN}.g.alchemy.com/v2/${BuildConfig.ETH_KEY}"
    }
}