package com.cj.bunnywallet.feature.home

import com.cj.bunnywallet.feature.home.type.SupportNetwork
import com.cj.bunnywallet.reducer.UiState

sealed interface HomeEvent {
    data class NetworkChange(val network: SupportNetwork) : HomeEvent

    object ManageWallet : HomeEvent
    object NavToManageCrypto : HomeEvent
}

data class HomeState(
    val network: SupportNetwork = SupportNetwork.MAIN,
    val accountName: String = "",
    val accountAddress: String = "",
    val balance: String = ""
) : UiState