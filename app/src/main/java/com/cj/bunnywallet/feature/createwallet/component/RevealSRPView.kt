package com.cj.bunnywallet.feature.createwallet.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cj.bunnywallet.MNEMONIC_SIZE_12
import com.cj.bunnywallet.R
import com.cj.bunnywallet.feature.common.CmnButton
import com.cj.bunnywallet.feature.common.CmnOutlineButton
import com.cj.bunnywallet.feature.createwallet.CreateWalletEvent

@Composable
fun RevealSRPView(
    mnemonic: List<String>,
    uiEvent: (CreateWalletEvent) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxWidth(fraction = .8f),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(
            text = stringResource(id = R.string.write_down_srp_title),
            modifier = Modifier.padding(vertical = 8.dp),
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleLarge,
        )

        Text(
            text = stringResource(id = R.string.write_down_srp_hint),
            style = MaterialTheme.typography.bodyMedium,
        )

        SRPBox {
            if (mnemonic.isNotEmpty()) {
                RevealSRPContent(mnemonic = mnemonic)
            } else {
                SRPSectionHide(onReveal = { uiEvent(CreateWalletEvent.RevealSRP) })
            }
        }

        CmnButton(
            text = stringResource(id = R.string._continue),
            onClick = { uiEvent(CreateWalletEvent.Continue) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            enabled = mnemonic.isNotEmpty(),
        )
    }
}

@Composable
fun SRPSectionHide(onReveal: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black, RoundedCornerShape(size = 10.dp))
            .padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Icon(
            painter = painterResource(id = R.drawable.ic_visibility_off),
            contentDescription = null,
            modifier = Modifier.padding(vertical = 20.dp),
            tint = Color.White,
        )

        Text(
            text = stringResource(id = R.string.tap_to_reveal_srp_1),
            modifier = Modifier.padding(vertical = 8.dp),
            color = Color.White,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )

        Text(
            text = stringResource(id = R.string.tap_to_reveal_srp_2),
            color = Color.White,
            style = MaterialTheme.typography.bodyMedium,
        )

        CmnOutlineButton(
            text = stringResource(id = R.string.reveal),
            onClick = onReveal,
            modifier = Modifier
                .fillMaxWidth(fraction = 0.5f)
                .padding(vertical = 20.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
            ),
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewRevealSRPViewNotReveal() {
    RevealSRPView(mnemonic = emptyList(), uiEvent = {})
}

@Preview(showBackground = true)
@Composable
fun PreviewRevealSRPViewReveal() {
    RevealSRPView(
        mnemonic = List(size = MNEMONIC_SIZE_12) { "bunny" },
        uiEvent = {},
    )
}