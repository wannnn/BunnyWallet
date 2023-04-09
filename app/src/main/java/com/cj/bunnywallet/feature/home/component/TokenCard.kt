package com.cj.bunnywallet.feature.home.component

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cj.bunnywallet.R
import com.cj.bunnywallet.ui.theme.BunnyWalletTheme

@Composable
fun TokenCard(onCardClick: () -> Unit) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(color = MaterialTheme.colorScheme.inversePrimary)
            .clickable { onCardClick.invoke() }
            .padding(15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.user_bunny),
            contentDescription = null,
            modifier = Modifier
                .weight(1f)
                .aspectRatio(1f)
        )

        Text(
            text = "Bunny USDT",
            modifier = Modifier
                .padding(start = 12.dp)
                .weight(weight = 3f),
            style = MaterialTheme.typography.bodyLarge
        )

        Column(
            modifier = Modifier.weight(weight = 5f),
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = "50.5555",
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                textAlign = TextAlign.End
            )

            Text(
                text = "≈ 0.05 USD",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.End
            )
        }
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun PreviewTokenCard() {
    BunnyWalletTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            TokenCard {}
        }
    }
}