package com.cj.bunnywallet.feature.unlock

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavOptions
import com.cj.bunnywallet.KEY_PWD
import com.cj.bunnywallet.R
import com.cj.bunnywallet.datasource.BunnyDataStore
import com.cj.bunnywallet.navigation.AppNavigator
import com.cj.bunnywallet.navigation.NavEvent
import com.cj.bunnywallet.navigation.route.MainRoute
import com.cj.bunnywallet.reducer.Reducer
import com.cj.bunnywallet.reducer.ReducerImp
import com.cj.bunnywallet.utils.CryptoManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class UnlockViewModel @Inject constructor(
    private val dataStore: BunnyDataStore,
    private val manager: CryptoManager,
    private val navigator: AppNavigator
) : ViewModel(), AppNavigator by navigator, Reducer<UnlockState> by ReducerImp(UnlockState()) {

    fun handleEvent(e: UnlockEvent) {
        when (e) {
            is UnlockEvent.SetPassword -> uiState = uiState.copy(pwd = e.pwd)
            is UnlockEvent.Unlock -> unlockWallet()
        }
    }

    private fun unlockWallet() {
        dataStore.getString(KEY_PWD)
            .onEach {
                val decryptPwd = manager.decrypt(it)
                if (decryptPwd == uiState.pwd) {
                    navToHome()
                } else {
                    uiState = uiState.copy(invalidPwdMsg = R.string.invalid_password)
                }
            }
            .launchIn(viewModelScope)
    }

    private fun navToHome() {
        val navEvent = NavEvent.NavTo(
            route = MainRoute.Home.route,
            navOptions = NavOptions.Builder()
                .setPopUpTo(
                    route = MainRoute.Unlock.route,
                    inclusive = true,
                )
                .build(),
        )
        navigateTo(navEvent)
    }
}