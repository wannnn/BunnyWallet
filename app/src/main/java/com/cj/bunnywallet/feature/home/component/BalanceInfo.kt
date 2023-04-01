package com.cj.bunnywallet.feature.home.component

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cj.bunnywallet.R
import com.cj.bunnywallet.feature.common.CommonIconBtn
import com.cj.bunnywallet.ui.customview.VisibilityIconBtn
import com.cj.bunnywallet.ui.theme.BunnyWalletTheme

@Composable
fun BalanceInfo(
    balance: String,
    balanceVisibility: Boolean,
    onVisibilityChange: () -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.assets),
                modifier = Modifier
                    .clip(RoundedCornerShape(5.dp))
                    .background(color = MaterialTheme.colorScheme.inverseOnSurface)
                    .padding(vertical = 4.dp, horizontal = 8.dp),
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
            )

            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                val bgModifier = Modifier.background(
                    color = MaterialTheme.colorScheme.inverseOnSurface,
                    shape = CircleShape
                )

                CommonIconBtn(
                    icon = R.drawable.ic_create,
                    modifier = bgModifier,
                    onClick = { }
                )

                CommonIconBtn(
                    icon = R.drawable.ic_add,
                    modifier = bgModifier,
                    onClick = { }
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            VisibilityIconBtn(
                isVisible = balanceVisibility,
                onVisibilityChange = onVisibilityChange
            )

            val currency = stringResource(id = R.string.usd)
            Text(
                text = balance.plus(" ").plus(currency),
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
            )
        }
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun PreviewBalanceInfo() {
    BunnyWalletTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            BalanceInfo(
                balance = "1.2345",
                balanceVisibility = true,
                onVisibilityChange = {}
            )
        }
    }
}