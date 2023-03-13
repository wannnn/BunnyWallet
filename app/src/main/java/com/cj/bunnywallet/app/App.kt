package com.cj.bunnywallet.app

import android.app.Application
import com.cj.bunnywallet.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import org.web3j.protocol.Web3j
import org.web3j.protocol.http.HttpService

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initWeb3j()
    }

    private fun initWeb3j() {
        val web3j = Web3j.build(
            HttpService(
                "https://${BuildConfig.ETH_DOMAIN}.g.alchemy.com/v2/${BuildConfig.ETH_KEY}"
            )
        )

        runCatching {
            web3j.web3ClientVersion().sendAsync().get()
        }
            .onSuccess {
                if (it.hasError()) {
                    println("Connected to Ethereum Failed: ${it.error.message}")
                } else {
                    println("Connected to Ethereum")
                }
            }
            .onFailure {
                println("Connected to Ethereum Exception: ${it.message}")
            }
    }
}
