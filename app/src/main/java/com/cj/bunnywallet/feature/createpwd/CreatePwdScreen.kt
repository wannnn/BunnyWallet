package com.cj.bunnywallet.feature.createpwd

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cj.bunnywallet.R
import com.cj.bunnywallet.feature.common.AppTopBar
import com.cj.bunnywallet.feature.common.CmnButton
import com.cj.bunnywallet.feature.createpwd.component.CreatePwdTitle
import com.cj.bunnywallet.feature.createpwd.component.CreateWalletBioSwitch
import com.cj.bunnywallet.feature.createwallet.component.Declaration
import com.cj.bunnywallet.navigation.NavEvent
import com.cj.bunnywallet.ui.customview.CreatePwdView

@Composable
fun CreatePwdScreen(
    uiState: CreatePwdState,
    uiEvent: (CreatePwdEvent) -> Unit,
    navEvent: (NavEvent) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AppTopBar(
            onBackClicked = { navEvent(NavEvent.NavBack) },
            showBackBtn = false,
        )

        CreatePwd(uiState, uiEvent)
    }
}

@Composable
fun CreatePwd(
    uiState: CreatePwdState,
    uiEvent: (CreatePwdEvent) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(fraction = 0.8f)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
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
}

@Preview(showBackground = true)
@Composable
fun PreviewCreatePwdScreen() {
    CreatePwdScreen(
        uiState = CreatePwdState(),
        uiEvent = {},
        navEvent = {},
    )
}
