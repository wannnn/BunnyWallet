package com.cj.bunnywallet.feature.createwallet.securewallet

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.cj.bunnywallet.navigation.AppNavigator
import com.cj.bunnywallet.navigation.route.CreateWalletRoute
import com.cj.bunnywallet.reducer.Reducer
import com.cj.bunnywallet.reducer.ReducerImp
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SecureWalletViewModel @Inject constructor(
    appNavigator: AppNavigator,
    savedStateHandle: SavedStateHandle,
) : ViewModel(),
    AppNavigator by appNavigator,
    Reducer<SecureWalletState> by ReducerImp(SecureWalletState()) {

    private val pwd: String = savedStateHandle[CreateWalletRoute.SecureWallet.PWD] ?: ""

    init {
        Timber.d(pwd)
    }

    fun handleEvent(e: SecureWalletEvent) {
        uiState = when (e) {
            is SecureWalletEvent.HandleDialog -> uiState.copy(dialogType = e.dialogType)
        }
    }
}