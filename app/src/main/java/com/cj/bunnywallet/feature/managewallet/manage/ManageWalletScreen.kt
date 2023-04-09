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
import com.cj.bunnywallet.model.wallet.WalletDisplay
import com.cj.bunnywallet.proto.wallet.account
import com.cj.bunnywallet.proto.wallet.wallet
import com.cj.bunnywallet.proto.wallet.wallets
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
    BunnyWalletTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            ManageWalletScreen(
                uiState = ManageWalletState(wallets = genFakeData(), currentAccount = "0x3"),
                uiEvent = {},
            )
        }
    }
}

private fun genFakeData(): List<WalletDisplay> {
    val w1 = wallet {
        id = "1"
        name = "ETH wallet"
        accounts.putAll(
            mapOf(
                "0x11" to account { address = "0x11"; name = "Stake account" },
                "0x12" to account { address = "0x12"; name = "Default account" },
            )
        )
    }
    val w2 = wallet {
        id = "2"
        name = "To the moon wallet"
        accounts.putAll(
            mapOf(
                "0x21" to account { address = "0x21"; name = "Shit coin account" },
                "0x22" to account { address = "0x22"; name = "Doge coin account" },
            )
        )
    }
    return wallets { wallets.putAll(mapOf("w1" to w1, "w2" to w2)) }
        .walletsMap
        .values
        .map { w ->
            WalletDisplay(
                id = w.id,
                name = w.name,
                accounts = w.accountsMap.values.map { acc ->
                    WalletDisplay.AccountDisplay(address = acc.address, name = acc.name)
                },
            )
        }
}