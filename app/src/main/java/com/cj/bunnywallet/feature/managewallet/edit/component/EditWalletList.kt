package com.cj.bunnywallet.feature.managewallet.edit.component

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cj.bunnywallet.R
import com.cj.bunnywallet.extensions.shortAddress
import com.cj.bunnywallet.feature.common.CommonIconBtn
import com.cj.bunnywallet.feature.managewallet.edit.EditWalletEvent
import com.cj.bunnywallet.model.managewallet.EditWalletDisplay
import com.cj.bunnywallet.ui.theme.BunnyWalletTheme
import com.cj.bunnywallet.ui.theme.Red400

@Composable
fun EditWalletList(
    wallets: List<EditWalletDisplay>,
    uiEvent: (EditWalletEvent) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 12.dp),
    ) {
        wallets.forEach {
            editWalletInfo(
                wallet = it,
                uiEvent = uiEvent,
            )
        }
    }
}

private fun LazyListScope.editWalletInfo(
    wallet: EditWalletDisplay,
    uiEvent: (EditWalletEvent) -> Unit,
) {
    wallet(
        walletId = wallet.id,
        walletName = wallet.name,
        edit = { uiEvent(it) }
    )

    accounts(
        walletId = wallet.id,
        accounts = wallet.accounts,
        edit = { uiEvent(it) }
    )
}

private fun LazyListScope.wallet(
    walletId: String,
    walletName: String,
    edit: (EditWalletEvent) -> Unit,
) {
    item {
        Column(
            modifier = Modifier
                .padding(top = 12.dp)
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.inverseOnSurface,
                    shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp),
                )
                .padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 4.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = walletName,
                    modifier = Modifier.weight(1f),
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 21.sp,
                    fontWeight = FontWeight.SemiBold,
                )

                CommonIconBtn(
                    icon = R.drawable.ic_create,
                    onClick = {
                        val e = EditWalletEvent.EditWalletName(
                            walletId = walletId,
                            walletName = walletName,
                        )
                        edit(e)
                    },
                )

                Divider(
                    modifier = Modifier
                        .fillMaxHeight(fraction = .5f)
                        .padding(horizontal = 2.dp)
                        .width(1.dp)
                )

                CommonIconBtn(
                    icon = R.drawable.ic_remove_circle,
                    tint = Red400,
                    onClick = { /* TODO */ },
                )
            }

            Divider(modifier = Modifier.padding(top = 4.dp))
        }
    }
}

private fun LazyListScope.accounts(
    walletId: String,
    accounts: List<EditWalletDisplay.EditAccountDisplay>,
    edit: (EditWalletEvent) -> Unit,
) {
    itemsIndexed(
        items = accounts,
        key = { _, acc -> acc.address },
    ) { index, acc ->
        val shape: Shape
        val padding: Modifier
        if (index == accounts.lastIndex) {
            shape = RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp)
            padding = Modifier.padding(start = 16.dp, top = 4.dp, end = 16.dp, bottom = 16.dp)
        } else {
            shape = RectangleShape
            padding = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
                .background(
                    color = MaterialTheme.colorScheme.inverseOnSurface,
                    shape = shape,
                )
                .then(padding),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = acc.name,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.SemiBold,
                )

                Text(
                    text = acc.address.shortAddress,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.secondary,
                )
            }

            CommonIconBtn(
                icon = R.drawable.ic_create,
                onClick = {
                    val e = EditWalletEvent.EditAccountName(
                        walletId = walletId,
                        address = acc.address,
                        accountName = acc.name,
                    )
                    edit(e)
                },
            )
        }
    }
}

@Preview(name = "Light Mode", showBackground = true, wallpaper = Wallpapers.BLUE_DOMINATED_EXAMPLE)
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun PreviewEditWalletList() {
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
            EditWalletList(wallets = wallets, uiEvent = {})
        }
    }
}