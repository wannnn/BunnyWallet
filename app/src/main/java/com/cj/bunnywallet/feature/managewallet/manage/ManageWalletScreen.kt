package com.cj.bunnywallet.feature.managewallet.manage

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cj.bunnywallet.R
import com.cj.bunnywallet.feature.common.AppTopBar
import com.cj.bunnywallet.feature.managewallet.manage.component.ManageDropDown
import com.cj.bunnywallet.feature.managewallet.manage.component.WalletList
import com.cj.bunnywallet.ui.theme.BunnyWalletTheme

@Composable
fun ManageWalletScreen(
    uiState: ManageWalletState,
    uiEvent: (ManageWalletEvent) -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        AppTopBar(
            onBackClicked = { uiEvent(ManageWalletEvent.OnBackClick) },
            appbarTitle = R.string.manage_wallet,
            tailContent = { ManageDropDown() },
        )

        WalletList(
            wallets = uiState.wallets,
            currentAccount = uiState.currentAccount,
            uiEvent = uiEvent,
        )
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun PreviewManageWalletScreen() {
    val fakeData = listOf(
        Wallet(
            id = "1",
            name = "ETH wallet",
            accounts = listOf(
                Wallet.Account(address = "0x1", name = "Stake account", amount = 100.0),
                Wallet.Account(address = "0x2", name = "Default account", amount = 20.2),
            ),
            isExpand = true,
        ),
        Wallet(
            id = "2",
            name = "To the moon wallet",
            accounts = listOf(
                Wallet.Account(address = "0x3", name = "Shit coin account", amount = 666.6),
                Wallet.Account(address = "0x4", name = "Doge coin account", amount = 222.2),
            )
        ),
    )

    BunnyWalletTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            ManageWalletScreen(
                uiState = ManageWalletState(wallets = fakeData, currentAccount = "0x3"),
                uiEvent = {},
            )
        }
    }
}