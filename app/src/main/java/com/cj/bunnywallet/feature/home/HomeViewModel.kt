package com.cj.bunnywallet.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cj.bunnywallet.navigation.AppNavigator
import com.cj.bunnywallet.navigation.NavEvent
import com.cj.bunnywallet.navigation.route.MainRoute
import com.cj.bunnywallet.reducer.Reducer
import com.cj.bunnywallet.reducer.ReducerImp
import com.cj.bunnywallet.utils.Web3jManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val web3jManager: Web3jManager,
    private val navigator: AppNavigator,
) : ViewModel(), AppNavigator by navigator, Reducer<HomeState> by ReducerImp(HomeState()) {

    init {
        getAccountBalance()
    }

    fun handleEvent(e: HomeEvent) {
        when (e) {
            HomeEvent.NavToManageCrypto -> {
                navigateTo(NavEvent.NavTo(route = MainRoute.ManageCrypto.route))
            }
        }
    }

    private fun getAccountBalance() {
        web3jManager.getEthBalance()
            .onEach { balanceEth -> uiState = uiState.copy(balance = balanceEth.toString()) }
            .launchIn(viewModelScope)
    }
}