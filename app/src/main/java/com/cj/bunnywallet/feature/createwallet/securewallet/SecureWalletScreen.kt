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
        navEvent = { uiEvent(SecureWalletEvent.HandleDialog(SecureWalletDialogType.WARN_SKIP)) },
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
        SecureWalletDialogType.SRP -> SRPDialog(onDismiss = { dismiss(uiEvent) })

        SecureWalletDialogType.PROTECT_WALLET_INFO ->
            ProtectWalletInfoDialog(onDismiss = { dismiss(uiEvent) })

        SecureWalletDialogType.WARN_SKIP -> WarnSkipDialog(onDismiss = { dismiss(uiEvent) })

        SecureWalletDialogType.HIDE -> {
            /** Hide Dialog **/
        }
    }
}

private fun dismiss(uiEvent: (SecureWalletEvent) -> Unit) {
    uiEvent(SecureWalletEvent.HandleDialog(SecureWalletDialogType.HIDE))
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

