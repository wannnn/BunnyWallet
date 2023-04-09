package com.cj.bunnywallet.feature.managecrypto

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cj.bunnywallet.R
import com.cj.bunnywallet.feature.common.AppTopBar
import com.cj.bunnywallet.feature.managecrypto.components.CryptoItem
import com.cj.bunnywallet.feature.managecrypto.components.CustomCryptoView
import com.cj.bunnywallet.feature.managecrypto.components.SearchTextField
import com.cj.bunnywallet.navigation.NavEvent
import com.cj.bunnywallet.navigation.route.MainRoute
import com.cj.bunnywallet.ui.theme.BunnyWalletTheme

@Composable
fun ManageCryptoScreen(
    navEvent: (NavEvent) -> Unit
) {

    Column(modifier = Modifier.fillMaxSize()) {
        AppTopBar(
            appbarTitle = R.string.manage_crypto,
            onBackClicked = { navEvent(NavEvent.NavBack) }
        )

        Column(modifier = Modifier.padding(horizontal = 16.dp)) {

            SearchTextField(modifier = Modifier.padding(vertical = 5.dp))

            CustomCryptoView { navEvent.invoke(NavEvent.NavTo(MainRoute.CustomCrypto.route)) }

            Divider()

            LazyColumn(
                contentPadding = PaddingValues(bottom = 24.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item {
                    ManageCryptoTitle(R.string.added_cryptos)
                }

                items(count = 5) {
                    CryptoItem(true)
                }

                item {
                    ManageCryptoTitle(R.string.popular_cryptos)
                }

                items(count = 5) {
                    CryptoItem(false)
                }
            }
        }
    }
}

@Composable
private fun ManageCryptoTitle(@StringRes titleId: Int) {
    Text(
        text = stringResource(id = titleId),
        modifier = Modifier.padding(top = 24.dp),
        style = MaterialTheme.typography.bodySmall
    )
}

@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun PreviewManageCryptoScreen() {
    BunnyWalletTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            ManageCryptoScreen(
                navEvent = {}
            )
        }
    }
}