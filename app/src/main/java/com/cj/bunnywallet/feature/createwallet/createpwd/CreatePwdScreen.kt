package com.cj.bunnywallet.feature.createwallet.createpwd

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cj.bunnywallet.R
import com.cj.bunnywallet.feature.common.AppTopBar
import com.cj.bunnywallet.feature.common.CmnButton
import com.cj.bunnywallet.feature.createwallet.component.progress.CreateWalletProgress
import com.cj.bunnywallet.feature.createwallet.createpwd.component.CreatePwdDeclaration
import com.cj.bunnywallet.feature.createwallet.createpwd.component.CreatePwdField
import com.cj.bunnywallet.feature.createwallet.createpwd.component.CreatePwdTitle
import com.cj.bunnywallet.feature.createwallet.createpwd.component.CreateWalletBioSwitch
import com.cj.bunnywallet.navigation.NavEvent
import com.cj.bunnywallet.navigation.route.CreateWalletRoute

@Composable
fun CreatePwdRoute(viewModel: CreatePwdViewModel = hiltViewModel()) {
    CreatePwdScreen(viewModel::navigateTo)
}

@Composable
fun CreatePwdScreen(navEvent: (NavEvent) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AppTopBar()

        Column(
            modifier = Modifier.fillMaxWidth(fraction = 0.8f),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            CreateWalletProgress()
            CreatePwdTitle()
            CreatePwdField()
            CreateWalletBioSwitch()
            CreatePwdDeclaration()
            CmnButton(
                text = stringResource(id = R.string.create_password),
                onClick = { navEvent(NavEvent.NavTo(CreateWalletRoute.SecureWallet.route)) },
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCreatePwdScreen() {
    CreatePwdScreen {}
}