package com.cj.bunnywallet.helper

import com.cj.bunnywallet.BuildConfig
import com.cj.bunnywallet.feature.home.type.SupportNetwork
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiHostHelper @Inject constructor() {

    private val delegate = StateFlowDelegate("")
    private var apiHostState by delegate

    val apiHosts = delegate.stateFlow

    fun updateUrl(network: SupportNetwork) {
        apiHostState = when (network) {
            SupportNetwork.MAIN -> BASE_URL_MAIN + BuildConfig.ETH_KEY_MAIN

            SupportNetwork.SEPOLIA -> BASE_URL_SEPOLIA + BuildConfig.ETH_KEY_SEPOLIA

            SupportNetwork.GOERLI -> BASE_URL_GOERLI + BuildConfig.ETH_KEY_GOERLI
        }
    }

    private companion object {
        const val BASE_URL_MAIN = "https://eth-mainnet.g.alchemy.com/v2/"
        const val BASE_URL_SEPOLIA = "https://eth-sepolia.g.alchemy.com/v2/"
        const val BASE_URL_GOERLI = "https://eth-goerli.g.alchemy.com/v2/"
    }
}