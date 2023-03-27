package com.cj.bunnywallet.feature.createwallet.confirmsrp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cj.bunnywallet.R
import com.cj.bunnywallet.feature.common.CmnButton
import com.cj.bunnywallet.ui.customview.VerticalGrid
import com.cj.bunnywallet.feature.createwallet.CreateWalletStep
import com.cj.bunnywallet.feature.createwallet.component.ConfirmSRPContent
import com.cj.bunnywallet.feature.createwallet.component.CreateWalletContainer
import com.cj.bunnywallet.feature.createwallet.component.SRPBox
import com.cj.bunnywallet.feature.createwallet.confirmsrp.model.PhraseSlot
import com.cj.bunnywallet.navigation.NavEvent
import com.cj.bunnywallet.ui.theme.Gray300
import com.cj.bunnywallet.ui.theme.Green400
import com.cj.bunnywallet.ui.theme.Red400

@Composable
fun ConfirmSRPScreen(
    uiState: ConfirmSRPState,
    uiEvent: (ConfirmSRPEvent) -> Unit,
    navEvent: (NavEvent) -> Unit,
) {
    CreateWalletContainer(
        step = CreateWalletStep.CONFIRM_SRP,
        topBarBackClick = { navEvent(NavEvent.NavBack) },
    ) {
        ConfirmSRP(uiState = uiState, uiEvent = uiEvent)
    }
}

@Composable
fun ConfirmSRP(
    uiState: ConfirmSRPState,
    uiEvent: (ConfirmSRPEvent) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ConfirmSRPTitle()

        val borderColor = when (uiState.sprSelectedState) {
            SRPSelectedState.CORRECT -> Green400
            SRPSelectedState.WRONG -> Red400
            SRPSelectedState.UNDONE -> MaterialTheme.colorScheme.primaryContainer
        }

        SRPBox(borderColor = borderColor) {
            ConfirmSRPContent(
                mnemonic = uiState.selectedMnemonic,
                onPhraseSlotClick = { uiEvent(ConfirmSRPEvent.OnSlotClicked(it)) },
            )
        }

        if (uiState.warningVisibility) {
            SRPWrongOrderWarning()
        }

        SRPSelections(
            mnemonics = uiState.shuffledMnemonic,
            onShuffledPhraseClicked = {
                uiEvent(ConfirmSRPEvent.OnShuffledPhraseClicked(it))
            }
        )

        CmnButton(
            text = stringResource(id = R.string.complete_backup),
            onClick = { uiEvent(ConfirmSRPEvent.BackUpCompleted) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 24.dp),
            enabled = uiState.btnCompleteEnable,
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
private fun SRPSelections(
    mnemonics: List<PhraseSlot>,
    onShuffledPhraseClicked: (PhraseSlot) -> Unit,
) {
    VerticalGrid(columns = 3) {
        mnemonics.forEach { ps ->
            val bgColor = when {
                ps.selected -> Gray300
                else -> MaterialTheme.colorScheme.inversePrimary
            }

            Text(
                text = ps.phrase,
                modifier = Modifier
                    .padding(all = 4.dp)
                    .background(
                        color = bgColor,
                        shape = RoundedCornerShape(size = 32.dp),
                    )
                    .clip(shape = RoundedCornerShape(32.dp))
                    .clickable { onShuffledPhraseClicked(ps) }
                    .padding(vertical = 12.dp),
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
private fun SRPWrongOrderWarning() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = painterResource(id = R.drawable.ic_cancel),
            contentDescription = null,
            modifier = Modifier.size(size = 16.dp),
            tint = Red400,
        )
        Text(
            text = stringResource(id = R.string.wrong_order),
            modifier = Modifier.padding(start = 8.dp),
            color = Red400,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewConfirmSRPScreen() {
    ConfirmSRPScreen(
        uiState = ConfirmSRPState(),
        uiEvent = {},
        navEvent = {},
    )
}
