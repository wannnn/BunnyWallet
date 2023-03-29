package com.cj.bunnywallet.utils

import com.cj.bunnywallet.BuildConfig
import org.web3j.protocol.Web3j
import org.web3j.protocol.http.HttpService
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Web3jManager @Inject constructor() {

    private lateinit var web3j: Web3j

    init {
        initWeb3j()
    }

    private fun initWeb3j() {
        web3j = Web3j.build(
            HttpService(
                "https://${BuildConfig.ETH_DOMAIN}.g.alchemy.com/v2/${BuildConfig.ETH_KEY}"
            )
        )

        runCatching {
            web3j.web3ClientVersion().sendAsync().get()
        }
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

    fun getWeb3j() = web3j
}