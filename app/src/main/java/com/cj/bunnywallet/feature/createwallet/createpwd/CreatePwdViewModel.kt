package com.cj.bunnywallet.feature.createwallet.createpwd

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavOptions
import com.cj.bunnywallet.KEY_PWD
import com.cj.bunnywallet.R
import com.cj.bunnywallet.datasource.BunnyDataStore
import com.cj.bunnywallet.extensions.isPasswordValid
import com.cj.bunnywallet.navigation.AppNavigator
import com.cj.bunnywallet.navigation.NavEvent
import com.cj.bunnywallet.navigation.route.CreateWalletRoute
import com.cj.bunnywallet.reducer.Reducer
import com.cj.bunnywallet.reducer.ReducerImp
import com.cj.bunnywallet.utils.CryptoManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreatePwdViewModel @Inject constructor(
    appNavigator: AppNavigator,
    private val dataStore: BunnyDataStore,
    private val manager: CryptoManager,
) : ViewModel(), AppNavigator by appNavigator,
    Reducer<CreatePwdState> by ReducerImp(CreatePwdState()) {

    init {
        dataStore.getString(key = KEY_PWD)
            .onEach { uiState = uiState.copy(showCreatedPwd = it.isBlank()) }
            .launchIn(viewModelScope)
    }

    fun handleEvent(e: CreatePwdEvent) {
        when (e) {
            is CreatePwdEvent.SetPwd -> {
                val isValidPwd = e.pwd.isPasswordValid()
                val pwdErrMsgRes = if (isValidPwd) null else R.string.password_condition_hint
                uiState = uiState.copy(
                    pwd = e.pwd,
                    pwdErrMsgRes = pwdErrMsgRes,
                    pwdIsValid = isValidPwd,
                )
            }

            is CreatePwdEvent.SetConfirmPwd -> {
                val isValidConfirmPwd = e.confirmPwd == uiState.pwd
                val pwdErrMsgRes = if (isValidConfirmPwd) null else R.string.pwd_not_match
                uiState = uiState.copy(
                    confirmPwd = e.confirmPwd,
                    confirmPwdErrMsgRes = pwdErrMsgRes,
                    confirmPwdIsValid = isValidConfirmPwd,
                )
            }

            is CreatePwdEvent.SetBiometrics ->
                uiState = uiState.copy(bioEnabled = e.isEnable)

            is CreatePwdEvent.SetCheckDeclaration -> uiState =
                uiState.copy(declarationChecked = e.isCheck)

            CreatePwdEvent.CreatePwd -> {
                val encryptedPwd = manager.encrypt(uiState.pwd) ?: return
                viewModelScope.launch {
                    dataStore.putString(KEY_PWD, encryptedPwd)
                    toSecureWallet()
                }
            }

            CreatePwdEvent.ToSecureWallet -> toSecureWallet()
        }
    }

    private fun toSecureWallet() {
        val navDestination = NavEvent.NavTo(
            route = CreateWalletRoute.SecureWallet.route,
            navOptions = NavOptions.Builder()
                .setPopUpTo(
                    route = CreateWalletRoute.CreatePassword.route,
                    inclusive = true,
                )
                .build(),
        )
        navigateTo(navDestination)
    }
}