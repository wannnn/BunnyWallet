package com.cj.bunnywallet.feature.importwallet

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
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
import com.cj.bunnywallet.ui.customview.VerticalGrid
import com.cj.bunnywallet.feature.importwallet.components.ImportFromSeedTitle
import com.cj.bunnywallet.feature.importwallet.components.PhraseAmountSelector
import com.cj.bunnywallet.feature.importwallet.components.PhraseTextField
import com.cj.bunnywallet.ui.customview.CreatePwdView
import com.cj.bunnywallet.navigation.NavEvent
import com.cj.bunnywallet.ui.customview.VisibilityControlView
import com.cj.bunnywallet.ui.theme.BunnyWalletTheme

@Composable
fun ImportWalletScreen(
    uiState: ImportWalletState,
    uiEvent: (ImportWalletEvent) -> Unit,
    navEvent: (NavEvent) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        AppTopBar(onBackClicked = { navEvent(NavEvent.NavBack) })

        var phraseVisibility by remember { mutableStateOf(true) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            ImportFromSeedTitle()

            PhraseAmountSelector(
                currentPhraseAmount = uiState.phraseAmount,
                onSelected = { uiEvent.invoke(ImportWalletEvent.SetPhraseAmountType(it)) }
            )

            VisibilityControlView(
                title = stringResource(id = R.string.secret_recovery_phrase),
                isVisible = phraseVisibility,
                onVisibilityChange = {
                    phraseVisibility = phraseVisibility.not()
                }
            )

            key(uiState.phraseAmount.amount) {
                VerticalGrid(
                    modifier = Modifier.fillMaxWidth(),
                    columns = 2
                ) {
                    repeat(uiState.phraseAmount.amount) { i ->
                        var phrase by remember { mutableStateOf("") }
                        PhraseTextField(
                            number = "${(i + 1)}. ",
                            phraseState = phrase,
                            phraseStateUpdate = {
                                phrase = it.trim()
                                uiEvent.invoke(ImportWalletEvent.UpdatePhrase(i, phrase))
                            },
                            showPhrase = phraseVisibility
                        )
                    }
                }
            }

            if (uiState.showSetPwd) {
                CreatePwdView(
                    title = stringResource(id = R.string.set_up_pwd),
                    pwd = uiState.pwd,
                    onPwd = { uiEvent.invoke(ImportWalletEvent.SetPassword(it)) },
                    pwdErrMsg = uiState.pwdErrMsg?.let { stringResource(id = it) },
                    confirmPwd = uiState.confirmPwd,
                    onConfirmPwd = { uiEvent.invoke(ImportWalletEvent.SetConfirmPassword(it)) },
                    confirmPwdErrMsg = uiState.confirmPwdErrMsg?.let { stringResource(id = it) }
                )
            }

            CmnButton(
                text = stringResource(id = R.string.btn_import),
                onClick = { uiEvent.invoke(ImportWalletEvent.Import) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 35.dp),
                isLoading = uiState.isLoading
            )
        }
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun PreviewImportWalletScreen() {
    BunnyWalletTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            ImportWalletScreen(
                uiState = ImportWalletState(),
                uiEvent = {},
                navEvent = {}
            )
        }
    }
}