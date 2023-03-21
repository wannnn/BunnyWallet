package com.cj.bunnywallet.feature.createwallet.securewallet

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.cj.bunnywallet.feature.createwallet.CreateWalletStep
import com.cj.bunnywallet.feature.createwallet.component.CreateWalletContainer
import com.cj.bunnywallet.feature.createwallet.securewallet.component.ConfirmPwd
import com.cj.bunnywallet.feature.createwallet.securewallet.component.RevealSRPView
import com.cj.bunnywallet.feature.createwallet.securewallet.component.SecureInfoView
import com.cj.bunnywallet.feature.createwallet.securewallet.dialog.ProtectWalletInfoDialog
import com.cj.bunnywallet.feature.createwallet.securewallet.dialog.SRPDialog
import com.cj.bunnywallet.feature.createwallet.securewallet.dialog.SecureWalletDialogType
import com.cj.bunnywallet.feature.createwallet.securewallet.dialog.WarnSkipDialog
import com.cj.bunnywallet.navigation.NavEvent

@Composable
fun SecureWalletScreen(
    uiState: SecureWalletState,
    uiEvent: (SecureWalletEvent) -> Unit,
    navEvent: (NavEvent) -> Unit,
) {
    val backAction: () -> Unit = when (uiState.step) {
        SecureWalletStep.READ_SECURE_INFO -> {
            { navEvent(NavEvent.NavBack) }
        }

        SecureWalletStep.CONFIRM_PWD -> {
            { uiEvent(SecureWalletEvent.UpdateStep(SecureWalletStep.READ_SECURE_INFO)) }
        }

        SecureWalletStep.GEN_SRP -> {
            { uiEvent(SecureWalletEvent.HandleDialog(SecureWalletDialogType.WARN_SKIP)) }
        }
    }

    BackHandler { backAction() }

    CreateWalletContainer(
        step = CreateWalletStep.SECURE_WALLET,
        topBarBackClick = backAction,
    ) {
        SecureWallet(uiState = uiState, uiEvent = uiEvent)
    }
}

@Composable
fun SecureWallet(
    uiState: SecureWalletState,
    uiEvent: (SecureWalletEvent) -> Unit,
) {
    when (uiState.step) {
        SecureWalletStep.READ_SECURE_INFO -> SecureInfoView(uiEvent = uiEvent)

        SecureWalletStep.CONFIRM_PWD -> ConfirmPwd(
            confirmErrMsgRes = uiState.confirmErrMsgRes,
            confirmBtnEnable = uiState.confirmBtnEnable,
            uiEvent = uiEvent,
        )

        SecureWalletStep.GEN_SRP -> RevealSRPView(
            mnemonic = uiState.mnemonic,
            uiEvent = uiEvent,
        )
    }

    when (uiState.dialogType) {
        SecureWalletDialogType.SRP -> SRPDialog(onDismiss = { dismiss(uiEvent) })

        SecureWalletDialogType.PROTECT_WALLET_INFO ->
            ProtectWalletInfoDialog(onDismiss = { dismiss(uiEvent) })

        SecureWalletDialogType.WARN_SKIP ->
            WarnSkipDialog(onDismiss = { handleSkipAction(it, uiEvent) })

        SecureWalletDialogType.HIDE -> {
            /** Hide Dialog **/
        }
    }
}

private fun dismiss(uiEvent: (SecureWalletEvent) -> Unit) {
    uiEvent(SecureWalletEvent.HandleDialog(SecureWalletDialogType.HIDE))
}

private fun handleSkipAction(skip: Boolean, uiEvent: (SecureWalletEvent) -> Unit) {
    if (skip) uiEvent(SecureWalletEvent.SkipGenSRP) else dismiss(uiEvent)
}

@Preview(showBackground = true)
@Composable
fun PreviewSecureWalletScreen() {
    SecureWalletScreen(uiState = SecureWalletState(), uiEvent = {}, navEvent = {})
}
