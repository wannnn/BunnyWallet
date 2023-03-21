package com.cj.bunnywallet.feature.createwallet.confirmsrp

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.cj.bunnywallet.navigation.AppNavigator
import com.cj.bunnywallet.navigation.route.CreateWalletRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ConfirmSRPViewModel @Inject constructor(
    appNavigator: AppNavigator,
    savedStateHandle: SavedStateHandle,
) : ViewModel(), AppNavigator by appNavigator {

    private val mnemonic: String = savedStateHandle[CreateWalletRoute.ConfirmSRP.SRP] ?: ""

    init {
        println("ConfirmSRPViewModel mnemonic: $mnemonic")
    }
}