package com.cj.bunnywallet.feature.createwallet.createpwd

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.cj.bunnywallet.R
import com.cj.bunnywallet.feature.common.CmnButton
import com.cj.bunnywallet.feature.createwallet.component.CreateWalletContainer
import com.cj.bunnywallet.feature.createwallet.component.Declaration
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
    CreateWalletContainer {
        CreatePwd(navEvent)
    }
}

@Composable
fun CreatePwd(navEvent: (NavEvent) -> Unit) {
    CreatePwdTitle()
    CreatePwdField()
    CreateWalletBioSwitch()
    Declaration(R.string.create_password_declaration)
    CmnButton(
        text = stringResource(id = R.string.create_password),
        onClick = { navEvent(NavEvent.NavTo(CreateWalletRoute.SecureWallet.route)) },
        modifier = Modifier.fillMaxWidth(),
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewCreatePwdScreen() {
    CreatePwdScreen {}
}

@Preview(showBackground = true, widthDp = 360)
@Composable
fun PreviewCreatePwd() {
    CreatePwd {}
}