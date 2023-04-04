package com.cj.bunnywallet.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cj.bunnywallet.navigation.AppNavigator
import com.cj.bunnywallet.reducer.Reducer
import com.cj.bunnywallet.reducer.ReducerImp
import com.cj.bunnywallet.utils.ERC20
import com.cj.bunnywallet.utils.Web3jManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val web3jManager: Web3jManager,
    private val navigator: AppNavigator,
) : ViewModel(), AppNavigator by navigator, Reducer<HomeState> by ReducerImp(HomeState()) {

    init {
        getAccountBalance()
    }

    private fun getAccountBalance() {
        web3jManager.getEthBalance()
            .onEach { balanceEth -> uiState = uiState.copy(balance = balanceEth.toString()) }
            .launchIn(viewModelScope)
    }
}