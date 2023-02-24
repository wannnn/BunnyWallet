package com.cj.bunnywallet.feature.createwallet.securewallet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cj.bunnywallet.R
import com.cj.bunnywallet.feature.common.AppTopBar
import com.cj.bunnywallet.feature.common.CmnButton
import com.cj.bunnywallet.feature.createwallet.component.progress.CreateWalletProgress
import com.cj.bunnywallet.feature.createwallet.securewallet.component.SRPDialog
import com.cj.bunnywallet.feature.createwallet.securewallet.component.SecureSuggestions
import com.cj.bunnywallet.feature.createwallet.securewallet.component.SecureWalletTitle

@Composable
fun SecureWalletRoute() {
    SecureWalletScreen()
}

@Composable
fun SecureWalletScreen() {

    var showDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AppTopBar()

        Column(
            modifier = Modifier
                .fillMaxWidth(fraction = 0.8f)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            CreateWalletProgress()
            SecureWalletTitle { showDialog = true }
            SecureSuggestions()
            CmnButton(
                text = stringResource(id = R.string.start),
                onClick = { /*TODO*/ },
                modifier = Modifier.fillMaxWidth(),
            )
        }

        if (showDialog) {
            SRPDialog { showDialog = false }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSecureWalletScreen() {
    SecureWalletScreen()
}