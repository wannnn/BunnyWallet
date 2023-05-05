package com.cj.bunnywallet.feature.customcrypto

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cj.bunnywallet.R
import com.cj.bunnywallet.feature.common.AppTopBar
import com.cj.bunnywallet.feature.common.CmnButton
import com.cj.bunnywallet.feature.common.CommonTextField
import com.cj.bunnywallet.navigation.NavEvent
import com.cj.bunnywallet.ui.theme.BunnyWalletTheme

@Composable
fun CustomCryptoScreen(
    uiState: CustomCryptoState,
    uiEvent: (CustomCryptoEvent) -> Unit,
    navEvent: (NavEvent) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        AppTopBar(
            appbarTitle = R.string.custom_crypto,
            onBackClicked = { navEvent(NavEvent.NavBack) }
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {

            // contract address
            TitleAndTextField(
                titleId = R.string.contract_address,
                textField = {
                    CommonTextField(
                        valueState = uiState.address,
                        onValueUpdate = { uiEvent.invoke(CustomCryptoEvent.InputAddress(it)) },
                        hint = stringResource(id = R.string.contract_address_hint)
                    )
                }
            )

            // symbol
            TitleAndTextField(
                titleId = R.string.symbol,
                textField = {
                    CommonTextField(
                        valueState = uiState.symbol,
                        onValueUpdate = { uiEvent.invoke(CustomCryptoEvent.InputSymbol(it)) },
                        hint = stringResource(id = R.string.symbol_hint)
                    )
                }
            )

            // decimal point
            TitleAndTextField(
                titleId = R.string.decimal,
                textField = {
                    CommonTextField(
                        valueState = uiState.decimal,
                        onValueUpdate = { uiEvent.invoke(CustomCryptoEvent.InputDecimal(it)) },
                        hint = stringResource(id = R.string.decimal_hint),
                        keyboardType = KeyboardType.Decimal,
                        imeAction = ImeAction.Done
                    )
                }
            )

            CmnButton(
                text = stringResource(id = R.string.confirm),
                onClick = { uiEvent.invoke(CustomCryptoEvent.Confirm) },
                modifier = Modifier.fillMaxWidth(),
                isLoading = uiState.isLoading,
                enabled = uiState.btnEnable
            )
        }
    }
}

@Composable
private fun TitleAndTextField(
    @StringRes titleId: Int,
    textField: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Text(
            text = stringResource(id = titleId),
            style = MaterialTheme.typography.bodyMedium
        )
        textField()
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun PreviewCustomCryptoScreen() {
    BunnyWalletTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            CustomCryptoScreen(
                uiState = CustomCryptoState(),
                uiEvent = {},
                navEvent = {}
            )
        }
    }
}