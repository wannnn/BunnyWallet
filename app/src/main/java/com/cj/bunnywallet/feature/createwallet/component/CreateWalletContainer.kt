package com.cj.bunnywallet.feature.createwallet.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cj.bunnywallet.feature.common.AppTopBar
import com.cj.bunnywallet.feature.createwallet.CreateWalletStep
import com.cj.bunnywallet.feature.createwallet.component.progress.CreateWalletProgress
import com.cj.bunnywallet.feature.createwallet.createpwd.CreatePwd
import com.cj.bunnywallet.feature.createwallet.createpwd.CreatePwdState
import com.cj.bunnywallet.navigation.NavEvent

@Composable
fun CreateWalletContainer(
    step: CreateWalletStep,
    topBarBackClick: () -> Unit,
    content: @Composable ColumnScope.() -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AppTopBar(onBackClicked = topBarBackClick)
        Column(
            modifier = Modifier
                .fillMaxWidth(fraction = 0.8f)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            CreateWalletProgress(step = step)
            content()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCreateWalletContainer() {
    CreateWalletContainer(
        step = CreateWalletStep.CREATE_PWD,
        topBarBackClick = {},
    ) {
        CreatePwd(
            uiState = CreatePwdState(),
            uiEvent = {},
        )
    }
}