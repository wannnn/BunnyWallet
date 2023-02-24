package com.cj.bunnywallet.feature.importwallet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cj.bunnywallet.R
import com.cj.bunnywallet.extensions.spanItem
import com.cj.bunnywallet.feature.common.AppTopBar
import com.cj.bunnywallet.feature.common.CmnButton
import com.cj.bunnywallet.feature.common.PasswordTextField
import com.cj.bunnywallet.feature.importwallet.components.ImportFromSeedTitle
import com.cj.bunnywallet.feature.importwallet.components.PhraseAmountSelector
import com.cj.bunnywallet.feature.importwallet.components.PhraseTextField
import com.cj.bunnywallet.feature.importwallet.components.VisibilityControlView
import com.cj.bunnywallet.feature.importwallet.type.PhraseAmountType

@Composable
fun ImportWalletRoute() {
    ImportWalletScreen()
}

@Composable
fun ImportWalletScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        AppTopBar()

        val words = remember { mutableStateOf(PhraseAmountType.TWELVE_WORDS) }
        var wordsVisibility by remember { mutableStateOf(true) }
        var pwdVisibility by remember { mutableStateOf(true) }

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            contentPadding = PaddingValues(top = 24.dp, bottom = 35.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            content = {
                spanItem {
                    ImportFromSeedTitle()
                }

                spanItem {
                    PhraseAmountSelector(
                        currentPhraseAmount = words.value,
                        onSelected = { words.value = it }
                    )
                }

                spanItem {
                    VisibilityControlView(
                        title = stringResource(id = R.string.secret_recovery_phrase),
                        isVisible = wordsVisibility,
                        onVisibilityChange = {
                            wordsVisibility = wordsVisibility.not()
                        }
                    )
                }

                items(words.value.amount) { i ->
                    var phrase by remember { mutableStateOf("") }
                    PhraseTextField(
                        number = "${(i + 1)}. ",
                        phraseState = phrase,
                        phraseStateUpdate = { phrase = it }
                    )
                }

                spanItem {
                    VisibilityControlView(
                        title = stringResource(id = R.string.set_up_pwd),
                        isVisible = pwdVisibility,
                        onVisibilityChange = {
                            pwdVisibility = pwdVisibility.not()
                        }
                    )
                }

                spanItem {
                    var password by remember { mutableStateOf("") }
                    PasswordTextField(
                        passwordState = password,
                        passwordStateUpdate = { password = it },
                        label = stringResource(id = R.string.create_password)
                    )
                }

                spanItem {
                    var confirmPassword by remember { mutableStateOf("") }
                    PasswordTextField(
                        passwordState = confirmPassword,
                        passwordStateUpdate = { confirmPassword = it },
                        label = stringResource(id = R.string.confirm_password)
                    )
                }

                spanItem {
                    CmnButton(
                        text = stringResource(id = R.string.btn_import),
                        onClick = { /*TODO*/ },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        )
    }
}

@Preview
@Composable
fun PreviewImportWalletScreen() {
    ImportWalletScreen()
}