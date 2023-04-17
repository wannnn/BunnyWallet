package com.cj.bunnywallet.feature.home.component

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cj.bunnywallet.R
import com.cj.bunnywallet.ui.theme.BunnyWalletTheme
import com.cj.bunnywallet.ui.theme.NoRippleInteractionSource

@Composable
fun AccountInfo(
    accountName: String,
    accountAddress: String,
    onManageWalletClicked: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 8.dp)
            .clickable(
                interactionSource = NoRippleInteractionSource(),
                indication = null,
                onClick = { onManageWalletClicked() },
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(id = R.drawable.user_polar_bear),
            contentDescription = null,
            modifier = Modifier
                .weight(weight = 1f)
                .aspectRatio(ratio = 1f),
        )

        Column(
            modifier = Modifier
                .weight(weight = 4f)
                .aspectRatio(ratio = 4f)
                .padding(horizontal = 12.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
        ) {
            Text(
                text = accountName,
                modifier = Modifier.padding(horizontal = 4.dp),
                fontWeight = FontWeight.SemiBold,
                style = MaterialTheme.typography.titleMedium,
            )

            Text(
                text = accountAddress,
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(32.dp))
                    .background(color = MaterialTheme.colorScheme.inverseOnSurface)
                    .clickable { }
                    .padding(horizontal = 8.dp, vertical = 3.dp),
                fontSize = 14.sp,
            )
        }

        Icon(
            painter = painterResource(id = R.drawable.ic_arrow_forward),
            contentDescription = null,
            modifier = Modifier.size(15.dp),
        )
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun PreviewAccountInfo() {
    BunnyWalletTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            AccountInfo(
                accountName = "Shit coin account",
                accountAddress = "0x71C7...976F",
                onManageWalletClicked = {},
            )
        }
    }
}