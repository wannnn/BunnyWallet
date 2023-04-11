package com.cj.bunnywallet.utils

import com.cj.bunnywallet.BuildConfig
import com.cj.bunnywallet.utils.wallet.WalletHelper
import com.cj.bunnywallet.utils.wallet.WalletHelperImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.web3j.crypto.Bip44WalletUtils
import org.web3j.crypto.Credentials
import org.web3j.protocol.Web3j
import org.web3j.protocol.core.DefaultBlockParameterName
import org.web3j.protocol.http.HttpService
import org.web3j.utils.Convert
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Web3jManager @Inject constructor() : WalletHelper by WalletHelperImpl() {

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

    fun loadCredentials(mnemonic: String): Credentials =
        Bip44WalletUtils.loadBip44Credentials("", mnemonic)

    fun getEthBalance(address: String) = flow {
        val balanceWei = web3j.ethGetBalance(address, DefaultBlockParameterName.LATEST)
            .send()
            .balance
        Timber.d(message = "Balance(Wei): $balanceWei")

        val balanceEther = Convert.fromWei(balanceWei.toString(), Convert.Unit.ETHER)
        Timber.d(message = "Balance(ETH): $balanceEther")

        emit(balanceEther)
    }
        .flowOn(Dispatchers.IO)

    companion object {
        lateinit var web3j: Web3j

        private const val ALCHEMY_URL =
            "https://${BuildConfig.ETH_DOMAIN}.g.alchemy.com/v2/${BuildConfig.ETH_KEY}"
    }
}