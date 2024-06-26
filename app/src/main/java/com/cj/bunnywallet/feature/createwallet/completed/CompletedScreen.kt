package com.cj.bunnywallet.feature.createwallet.completed

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Icon
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
import com.cj.bunnywallet.R
import com.cj.bunnywallet.feature.common.CmnButton
import com.cj.bunnywallet.feature.createwallet.CreateWalletStep
import com.cj.bunnywallet.feature.createwallet.component.CreateWalletContainer

@Composable
fun CompletedScreen(
    uiState: CompletedState,
    uiEvent: (CompletedEvent) -> Unit,
) {
    BackHandler { uiEvent(CompletedEvent.Done) }

    CreateWalletContainer(
        step = CreateWalletStep.DONE,
        topBarBackClick = {},
        showBackBtn = false,
    ) {
        Completed(uiState = uiState, uiEvent = uiEvent)
    }
}

@Composable
private fun Completed(
    uiState: CompletedState,
    uiEvent: (CompletedEvent) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 8.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            imageVector = Icons.Filled.ThumbUp,
            contentDescription = null,
            modifier = Modifier
                .padding(top = 12.dp)
                .size(50.dp),
        )

        Text(
            text = stringResource(id = R.string.congratulations),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineMedium,
        )

        Text(
            text = stringResource(R.string.complete_msg_1),
            modifier = Modifier.padding(top = 12.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium,
        )

        Text(
            text = stringResource(R.string.leave_yourself_a_hint),
            modifier = Modifier
                .padding(top = 12.dp)
                .clickable { uiEvent(CompletedEvent.HandleDialog(true)) },
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium,
        )

        Text(
            text = stringResource(R.string.complete_msg_2),
            modifier = Modifier.padding(top = 12.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium,
        )

        CmnButton(
            text = stringResource(id = R.string.done),
            onClick = { uiEvent(CompletedEvent.Done) },
            modifier = Modifier.fillMaxWidth(),
        )
    }

    if (uiState.showDialog) {
        RecoveryHintDialog(
            onDismiss = { uiEvent(CompletedEvent.HandleDialog(false)) },
            saveHint = { uiEvent(CompletedEvent.SaveHint(it)) },
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewCompleted() {
    CompletedScreen(
        uiState = CompletedState(),
        uiEvent = {},
    )
}