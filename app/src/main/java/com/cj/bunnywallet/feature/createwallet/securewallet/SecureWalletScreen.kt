package com.cj.bunnywallet.feature.createwallet.securewallet

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.cj.bunnywallet.feature.createwallet.CreateWalletStep
import com.cj.bunnywallet.feature.createwallet.component.CreateWalletContainer
import com.cj.bunnywallet.feature.createwallet.securewallet.component.SecureInfoView
import com.cj.bunnywallet.feature.createwallet.securewallet.dialog.ProtectWalletInfoDialog
import com.cj.bunnywallet.feature.createwallet.securewallet.dialog.SRPDialog
import com.cj.bunnywallet.feature.createwallet.securewallet.dialog.SecureWalletDialogType
import com.cj.bunnywallet.feature.createwallet.securewallet.dialog.WarnSkipDialog

@Composable
fun SecureWalletScreen(
    uiState: SecureWalletState,
    uiEvent: (SecureWalletEvent) -> Unit,
) {
    CreateWalletContainer(
        step = CreateWalletStep.SECURE_WALLET,
        navEvent = { uiEvent(SecureWalletEvent.OpenWarnSkipDialog) },
    ) {
        SecureWallet(uiState = uiState, uiEvent = uiEvent)
    }
}

@Composable
fun SecureWallet(
    uiState: SecureWalletState,
    uiEvent: (SecureWalletEvent) -> Unit,
) {
    SecureInfoView(uiEvent)

    // ConfirmPwd()

    // RevealSRPView()

    when (uiState.dialogType) {
        SecureWalletDialogType.SRP ->
            SRPDialog(onDismiss = { uiEvent(SecureWalletEvent.HideDialog) })

        SecureWalletDialogType.PROTECT_WALLET_INFO ->
            ProtectWalletInfoDialog(onDismiss = { uiEvent(SecureWalletEvent.HideDialog) })

        SecureWalletDialogType.WARN_SKIP ->
            WarnSkipDialog(onDismiss = { uiEvent(SecureWalletEvent.HideDialog) })

        SecureWalletDialogType.HIDE -> {
            /** Hide Dialog **/
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSecureWalletScreen() {
    SecureWalletScreen(uiState = SecureWalletState(), uiEvent = {})
}

@Preview(showBackground = true)
@Composable
fun PreviewSecureWallet() {
    SecureWallet(uiState = SecureWalletState(), uiEvent = {})
}

