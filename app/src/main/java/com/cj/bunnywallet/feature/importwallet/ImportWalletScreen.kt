package com.cj.bunnywallet.feature.importwallet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.cj.bunnywallet.feature.common.PasswordTextField
import com.cj.bunnywallet.feature.common.VerticalGrid
import com.cj.bunnywallet.feature.importwallet.components.ImportFromSeedTitle
import com.cj.bunnywallet.feature.importwallet.components.PhraseAmountSelector
import com.cj.bunnywallet.feature.importwallet.components.PhraseTextField
import com.cj.bunnywallet.feature.importwallet.components.VisibilityControlView

@Composable
fun ImportWalletRoute(
    uiState: ImportWalletState,
    uiEvent: (ImportWalletEvent) -> Unit
) {
    ImportWalletScreen(uiState, uiEvent)
}

@Composable
fun ImportWalletScreen(
    uiState: ImportWalletState,
    uiEvent: (ImportWalletEvent) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        AppTopBar {}

        var phraseVisibility by remember { mutableStateOf(true) }
        var pwdVisibility by remember { mutableStateOf(true) }

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

            VisibilityControlView(
                title = stringResource(id = R.string.set_up_pwd),
                isVisible = pwdVisibility,
                onVisibilityChange = {
                    pwdVisibility = pwdVisibility.not()
                }
            )

            PasswordTextField(
                passwordState = uiState.password,
                passwordStateUpdate = { uiEvent.invoke(ImportWalletEvent.SetPassword(it.trim())) },
                label = stringResource(id = R.string.create_password),
                showPassword = pwdVisibility,
                errorMsg = if (uiState.passwordValid) null else stringResource(id = R.string.eight_characters)
            )

            PasswordTextField(
                passwordState = uiState.confirmPassword,
                passwordStateUpdate = {
                    uiEvent.invoke(ImportWalletEvent.SetConfirmPassword(it.trim()))
                },
                label = stringResource(id = R.string.confirm_password),
                showPassword = pwdVisibility,
                errorMsg = if (uiState.confirmPasswordValid) null else stringResource(id = R.string.pwd_not_match)
            )

            CmnButton(
                text = stringResource(id = R.string.btn_import),
                onClick = { uiEvent.invoke(ImportWalletEvent.Import) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 35.dp)
            )
        }
    }
}

@Preview
@Composable
fun PreviewImportWalletScreen() {
    ImportWalletScreen(
        uiState = ImportWalletState(),
        uiEvent = {}
    )
}