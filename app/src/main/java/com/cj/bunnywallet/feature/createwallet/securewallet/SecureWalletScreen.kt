package com.cj.bunnywallet.feature.createwallet.securewallet

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.cj.bunnywallet.feature.createwallet.CreateWalletStep
import com.cj.bunnywallet.feature.createwallet.component.CreateWalletContainer
import com.cj.bunnywallet.feature.createwallet.createpwd.CreatePwdViewModel
import com.cj.bunnywallet.feature.createwallet.securewallet.component.RevealSRPView
import com.cj.bunnywallet.feature.createwallet.securewallet.component.SecureInfoView
import com.cj.bunnywallet.feature.createwallet.securewallet.dialog.SRPDialog
import com.cj.bunnywallet.navigation.NavEvent

@Composable
fun SecureWalletRoute(viewModel: CreatePwdViewModel = hiltViewModel()) {
    SecureWalletScreen(viewModel::navigateTo)
}

@Composable
fun SecureWalletScreen(navEvent: (NavEvent) -> Unit) {
    CreateWalletContainer(CreateWalletStep.SECURE_WALLET, {}) {
        SecureWallet(navEvent)
    }
}

@Composable
fun SecureWallet(navEvent: (NavEvent) -> Unit) {

    var showDialog by remember { mutableStateOf(false) }

    SecureInfoView(
        { showDialog = true },
        navEvent,
    )

    // ConfirmPwd()

    // RevealSRPView()

    if (showDialog) {
        SRPDialog { showDialog = false }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSecureWalletScreen() {
    SecureWalletScreen {}
}

@Preview(showBackground = true, widthDp = 360)
@Composable
fun PreviewSecureWallet() {
    SecureWallet {}
}

