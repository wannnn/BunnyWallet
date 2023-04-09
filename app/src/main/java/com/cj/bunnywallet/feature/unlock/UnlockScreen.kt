package com.cj.bunnywallet.feature.unlock

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cj.bunnywallet.R
import com.cj.bunnywallet.feature.common.CmnButton
import com.cj.bunnywallet.feature.common.PasswordTextField
import com.cj.bunnywallet.ui.theme.BunnyWalletTheme

@Composable
fun UnlockScreen(
    uiState: UnlockState,
    uiEvent: (UnlockEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.user_bunny),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth(fraction = 0.4f)
                    .aspectRatio(1f)
            )

            Text(
                text = stringResource(id = R.string.welcome_back),
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
            )

            PasswordTextField(
                passwordState = uiState.pwd,
                passwordStateUpdate = { uiEvent.invoke(UnlockEvent.SetPassword(it)) },
                label = stringResource(id = R.string.password),
                imeAction = ImeAction.Done,
                errorMsg = uiState.invalidPwdMsg?.let { stringResource(id = it) }
            )

            CmnButton(
                text = stringResource(id = R.string.unlock),
                onClick = { uiEvent.invoke(UnlockEvent.Unlock) },
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = stringResource(id = R.string.reset_wallet_hint),
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )

            Text(
                text = stringResource(id = R.string.reset_wallet),
                modifier = Modifier.clickable {
                    // TODO reset wallet
                },
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun PreviewUnlockScreen() {
    BunnyWalletTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            UnlockScreen(
                uiState = UnlockState(),
                uiEvent = {}
            )
        }
    }
}