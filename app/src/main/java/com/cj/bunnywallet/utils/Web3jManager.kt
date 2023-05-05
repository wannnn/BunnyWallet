package com.cj.bunnywallet.utils

import com.cj.bunnywallet.helper.ApiHostHelper
import com.cj.bunnywallet.helper.WalletHelper
import com.cj.bunnywallet.helper.WalletHelperImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.web3j.protocol.Web3j
import org.web3j.protocol.core.DefaultBlockParameterName
import org.web3j.protocol.http.HttpService
import org.web3j.utils.Convert
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Web3jManager @Inject constructor(
    cryptoManager: CryptoManager,
    scope: CoroutineScope,
    apiHostHelper: ApiHostHelper,
) : WalletHelper by WalletHelperImpl(cryptoManager) {

    init {
        apiHostHelper.apiHosts
            .onEach { connectToEthereum(it) }
            .launchIn(scope)
    }

    private fun connectToEthereum(url: String) {
        runCatching { web3j.shutdown() }

        web3j = Web3j.build(HttpService(url))

        runCatching { web3j.web3ClientVersion().send() }
            .onSuccess {
                if (it.hasError()) {
                    Timber.d(message = "Connected to Ethereum Failed: ${it.error.message}")
                } else {
                    Timber.d(message = "Connected to Ethereum: $url")
                }
            }
            .onFailure {
                Timber.d(message = "Connected to Ethereum Exception: ${it.message}")
            }
    }

    fun getVersion() = runCatching { web3j.netVersion().sendAsync().get() }.getOrNull()

    fun getEthBalance(address: String) = flow {
        val balanceWei = web3j.ethGetBalance(address, DefaultBlockParameterName.LATEST)
            .sendAsync()
            .get()
            .balance
        Timber.d(message = "Balance(Wei): $balanceWei")

        val balanceEther = Convert.fromWei(balanceWei.toString(), Convert.Unit.ETHER)
        Timber.d(message = "Balance(ETH): $balanceEther")

        emit(balanceEther)
    }
        .flowOn(Dispatchers.IO)
        .catch { e -> Timber.d(message = "Get Balance Error: ${e.message}") }

    companion object {
        lateinit var web3j: Web3j
    }
}