package com.cj.bunnywallet.feature.home

import com.cj.bunnywallet.reducer.UiState

sealed interface HomeEvent {
    object ManageWallet : HomeEvent
    object NavToManageCrypto: HomeEvent
}

data class HomeState(
    val accountName: String = "",
    val accountAddress: String = "",
    val balance: String = ""
) : UiState