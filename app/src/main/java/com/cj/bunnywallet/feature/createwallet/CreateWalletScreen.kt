package com.cj.bunnywallet.feature.createwallet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cj.bunnywallet.feature.common.AppTopBar
import com.cj.bunnywallet.feature.createwallet.component.bioswitch.CreateWalletBioSwitch
import com.cj.bunnywallet.feature.createwallet.component.createbtn.CreateWalletButton
import com.cj.bunnywallet.feature.createwallet.component.declaration.CreatePwdDeclaration
import com.cj.bunnywallet.feature.createwallet.component.progress.CreateWalletProgress
import com.cj.bunnywallet.feature.createwallet.component.pwdfield.CreatePwdField
import com.cj.bunnywallet.feature.createwallet.component.title.CreatePwdTitle

@Composable
fun CreateWalletRoute() {
    CreateWalletScreen()
}

@Composable
fun CreateWalletScreen() {
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
            CreateWalletButton()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCreateWalletScreen() {
    CreateWalletScreen()
}