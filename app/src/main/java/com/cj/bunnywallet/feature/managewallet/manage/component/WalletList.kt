package com.cj.bunnywallet.feature.managewallet.manage.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cj.bunnywallet.R
import com.cj.bunnywallet.feature.common.CommonIconBtn
import com.cj.bunnywallet.feature.managewallet.manage.ManageWalletEvent
import com.cj.bunnywallet.feature.managewallet.manage.Wallet

@Composable
fun WalletList(
    wallets: List<Wallet>,
    currentAccount: String,
    uiEvent: (ManageWalletEvent) -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 12.dp),
    ) {
        wallets.forEach {
            walletInfo(
                wallet = it,
                currentAccount = currentAccount,
                uiEvent = uiEvent,
            )
        }
    }
}

private fun LazyListScope.walletInfo(
    wallet: Wallet,
    currentAccount: String,
    uiEvent: (ManageWalletEvent) -> Unit,
) {
    walletItem(
        wallet = wallet,
        expandClicked = { uiEvent(ManageWalletEvent.ExpandWallet(wallet.id)) },
    )

    if (wallet.isExpand) {
        accountItems(
            accounts = wallet.accounts,
            currentAccount = currentAccount,
            onAccountSelected = { uiEvent(ManageWalletEvent.SelectAccount(it)) },
        )

        addAccountItem(addAccount = { uiEvent(ManageWalletEvent.AddAccount(wallet.id)) })
    }
}

private fun LazyListScope.walletItem(
    wallet: Wallet,
    expandClicked: () -> Unit,
) {
    item {
        val innerPadding: Modifier
        val bgShape: Shape

        if (wallet.isExpand) {
            innerPadding = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 4.dp)
            bgShape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)
        } else {
            innerPadding = Modifier.padding(all = 16.dp)
            bgShape = RoundedCornerShape(size = 8.dp)
        }

        Column(
            modifier = Modifier
                .padding(top = 12.dp)
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.inverseOnSurface,
                    shape = bgShape,
                )
                .then(innerPadding),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Info(
                    name = wallet.name,
                    nameSize = 21.sp,
                    amount = wallet.amount,
                    amountSize = 17.sp,
                    nameColor = MaterialTheme.colorScheme.primary,
                )

                CommonIconBtn(
                    icon = if (wallet.isExpand) {
                        R.drawable.ic_arrow_up
                    } else {
                        R.drawable.ic_arrow_down
                    },
                    onClick = { expandClicked() }
                )
            }

            if (wallet.isExpand) {
                Divider(modifier = Modifier.padding(top = 4.dp))
            }
        }
    }
}

private fun LazyListScope.accountItems(
    accounts: List<Wallet.Account>,
    currentAccount: String,
    onAccountSelected: (String) -> Unit,
) {
    items(accounts) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.inverseOnSurface)
                .padding(start = 18.dp, end = 30.dp, top = 4.dp, bottom = 4.dp)
                .clickable { onAccountSelected(it.address) },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Info(
                name = it.name,
                nameSize = 18.sp,
                amount = it.amount.toString(),
                amountSize = 14.sp,
            )

            if (it.address == currentAccount) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_check_filled),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp),
                    tint = MaterialTheme.colorScheme.secondary,
                )
            }
        }
    }
}

private fun LazyListScope.addAccountItem(addAccount: () -> Unit) {
    item {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.inverseOnSurface,
                    shape = RoundedCornerShape(bottomEnd = 8.dp, bottomStart = 8.dp),
                )
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Divider(modifier = Modifier.padding(top = 4.dp))

            Row(
                modifier = Modifier
                    .padding(vertical = 12.dp)
                    .clickable { addAccount() },
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_add_filled),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(end = 12.dp)
                        .size(20.dp),
                    tint = MaterialTheme.colorScheme.secondary,
                )

                Text(text = stringResource(id = R.string.add_account))
            }
        }
    }
}

@Composable
private fun Info(
    name: String, nameSize: TextUnit,
    amount: String, amountSize: TextUnit,
    nameColor: Color = Color.Unspecified,
) {
    Column {
        Text(
            text = name,
            color = nameColor,
            fontSize = nameSize,
            fontWeight = FontWeight.SemiBold,
        )

        Text(
            text = amount,
            color = MaterialTheme.colorScheme.secondary,
            fontSize = amountSize,
        )
    }
}