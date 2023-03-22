package com.cj.bunnywallet.feature.createwallet.createpwd

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.cj.bunnywallet.R
import com.cj.bunnywallet.feature.common.CmnButton
import com.cj.bunnywallet.feature.createwallet.CreateWalletStep
import com.cj.bunnywallet.feature.createwallet.component.CreateWalletContainer
import com.cj.bunnywallet.feature.createwallet.component.Declaration
import com.cj.bunnywallet.feature.createwallet.createpwd.component.CreatePwdTitle
import com.cj.bunnywallet.feature.createwallet.createpwd.component.CreateWalletBioSwitch
import com.cj.bunnywallet.navigation.NavEvent
import com.cj.bunnywallet.ui.customview.CreatePwdView

@Composable
fun CreatePwdScreen(
    uiState: CreatePwdState,
    uiEvent: (CreatePwdEvent) -> Unit,
    navEvent: (NavEvent) -> Unit,
) {
    if (uiState.showCreatedPwd) {
        CreateWalletContainer(
            step = CreateWalletStep.CREATE_PWD,
            topBarBackClick = { navEvent(NavEvent.NavBack) },
        ) {
            CreatePwd(uiState, uiEvent)
        }
    } else {
        uiEvent(CreatePwdEvent.ToSecureWallet)
    }
}

@Composable
fun CreatePwd(
    uiState: CreatePwdState,
    uiEvent: (CreatePwdEvent) -> Unit,
) {
    CreatePwdTitle()
    CreatePwdView(
        pwd = uiState.pwd,
        onPwd = { uiEvent(CreatePwdEvent.SetPwd(it)) },
        pwdErrMsg = uiState.pwdErrMsgRes?.let { stringResource(id = it) },
        confirmPwd = uiState.confirmPwd,
        onConfirmPwd = { uiEvent(CreatePwdEvent.SetConfirmPwd(it)) },
        confirmPwdErrMsg = uiState.confirmPwdErrMsgRes?.let { stringResource(id = it) }
    )
    CreateWalletBioSwitch(bioEnabled = uiState.bioEnabled, uiEvent = uiEvent)
    Declaration(
        declaration = R.string.create_password_declaration,
        checked = uiState.declarationChecked,
        onCheckedChange = { uiEvent(CreatePwdEvent.SetCheckDeclaration(it)) }
    )
    CmnButton(
        text = stringResource(id = R.string.create_password),
        onClick = { uiEvent(CreatePwdEvent.CreatePwd) },
        modifier = Modifier.fillMaxWidth(),
        enabled = uiState.createPwdBtnEnabled,
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewCreatePwdScreen() {
    CreatePwdScreen(
        uiState = CreatePwdState(showCreatedPwd = true),
        uiEvent = {},
        navEvent = {},
    )
}
