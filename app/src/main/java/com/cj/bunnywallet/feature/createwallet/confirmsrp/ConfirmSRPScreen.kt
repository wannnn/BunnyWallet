package com.cj.bunnywallet.feature.createwallet.confirmsrp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cj.bunnywallet.R
import com.cj.bunnywallet.feature.common.CmnButton
import com.cj.bunnywallet.feature.common.VerticalGrid
import com.cj.bunnywallet.feature.createwallet.CreateWalletStep
import com.cj.bunnywallet.feature.createwallet.component.CreateWalletContainer
import com.cj.bunnywallet.feature.createwallet.component.SRPBox
import com.cj.bunnywallet.feature.createwallet.component.SRPContent
import com.cj.bunnywallet.feature.createwallet.component.srpDashedBorder
import com.cj.bunnywallet.navigation.NavEvent
import com.cj.bunnywallet.navigation.route.CreateWalletRoute
import com.cj.bunnywallet.ui.theme.PurpleGrey80

@Composable
fun ConfirmSRPRoute(viewModel: ConfirmSRPViewModel = hiltViewModel()) {
    ConfirmSRPScreen(viewModel::navigateTo)
}

@Composable
fun ConfirmSRPScreen(navEvent: (NavEvent) -> Unit) {
    CreateWalletContainer(
        step = CreateWalletStep.CONFIRM_SRP,
        topBarBackClick = {},
    ) {
        ConfirmSRP(navEvent)
    }
}

@Composable
fun ConfirmSRP(navEvent: (NavEvent) -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ConfirmSRPTitle()

        SRPBox {
            SRPContent(
                mnemonic = listOf("", "", "", "", "", "", "", "", "", "", "", ""),
                modifier = srpDashedBorder,
            )
        }

        SRPSelections(
            listOf(
                "summer", "summer", "summer", "summer",
                "whale", "whale", "whale", "whale",
                "thank", "thank", "thank", "thank",
            )
        )

        CmnButton(
            text = stringResource(id = R.string.complete_backup),
            onClick = { navEvent(NavEvent.NavTo(CreateWalletRoute.CreateWalletCompleted.route)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 24.dp),
        )
    }
}

@Composable
private fun ConfirmSRPTitle() {
    Column(
        modifier = Modifier
            .fillMaxWidth(fraction = 0.8f)
            .padding(vertical = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(id = R.string.confirm_srp),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge,
        )
        Text(
            text = stringResource(R.string.confirm_srp_hint),
            modifier = Modifier.padding(top = 12.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

@Composable
fun SRPSelections(mnemonics: List<String>) {
    VerticalGrid(columns = 3) {
        mnemonics.forEach { phrase ->
            Text(
                text = phrase,
                modifier = Modifier
                    .padding(all = 4.dp)
                    .background(
                        color = PurpleGrey80,
                        shape = RoundedCornerShape(size = 20.dp),
                    )
                    .padding(vertical = 12.dp),
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewConfirmSRPScreen() {
    ConfirmSRPScreen {}
}
