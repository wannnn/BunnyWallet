package com.cj.bunnywallet.feature.managewallet.edit

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import com.cj.bunnywallet.R
import com.cj.bunnywallet.feature.common.AppTopBar
import com.cj.bunnywallet.feature.managewallet.edit.component.EditWalletList
import com.cj.bunnywallet.feature.managewallet.edit.dialog.EditDialog
import com.cj.bunnywallet.model.managewallet.EditInfo
import com.cj.bunnywallet.model.managewallet.EditWalletDisplay
import com.cj.bunnywallet.ui.theme.BunnyWalletTheme

@Composable
fun EditWalletScreen(
    uiState: EditWalletState,
    uiEvent: (EditWalletEvent) -> Unit,
    navBack: () -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        AppTopBar(
            onBackClicked = { navBack() },
            appbarTitle = R.string.edit_wallet,
        )

        EditWalletList(
            wallets = uiState.wallets,
            uiEvent = uiEvent,
        )

        if (uiState.editInfo != null) {
            EditDialog(
                name = uiState.editInfo.name,
                editType = uiState.editInfo.type,
                onDismiss = {
                    if (it.isBlank()) {
                        uiEvent(EditWalletEvent.NoUpdate)
                    } else {
                        val editInfo = EditInfo(
                            walletId = uiState.editInfo.walletId,
                            address = uiState.editInfo.address,
                            name = it,
                            type = uiState.editInfo.type,
                        )
                        uiEvent(EditWalletEvent.EditDone(editInfo = editInfo))
                    }
                },
            )
        }
    }
}

@Preview(name = "Light Mode", showBackground = true, wallpaper = Wallpapers.BLUE_DOMINATED_EXAMPLE)
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun PreviewEditWalletScreen() {
    val wallets = listOf(
        EditWalletDisplay(
            id = "xxx",
            name = "Wallet 1",
            accounts = listOf(
                EditWalletDisplay.EditAccountDisplay(
                    address = "0x1111111111111111111",
                    name = "Acc 1"
                ),
                EditWalletDisplay.EditAccountDisplay(
                    address = "0x2222222222222222222",
                    name = "Acc 2"
                ),
            ),
        )
    )

    BunnyWalletTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            EditWalletScreen(
                uiState = EditWalletState(wallets = wallets),
                uiEvent = {},
                navBack = {},
            )
        }
    }
}