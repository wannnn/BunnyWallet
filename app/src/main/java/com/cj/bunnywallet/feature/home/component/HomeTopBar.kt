package com.cj.bunnywallet.feature.home.component

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cj.bunnywallet.R
import com.cj.bunnywallet.feature.common.CommonIconBtn
import com.cj.bunnywallet.feature.home.HomeEvent
import com.cj.bunnywallet.feature.home.dialog.NetworkChangeDialog
import com.cj.bunnywallet.feature.home.type.SupportNetwork
import com.cj.bunnywallet.ui.modifier.customShadow
import com.cj.bunnywallet.ui.theme.BunnyWalletTheme

@Composable
fun HomeTopBar(
    network: SupportNetwork,
    uiEvent: (HomeEvent) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .customShadow(blurRadius = 4.dp)
            .background(MaterialTheme.colorScheme.background),
    ) {
        CommonIconBtn(
            icon = R.drawable.ic_menu,
            modifier = Modifier
                .padding(start = 8.dp)
                .align(Alignment.CenterStart),
            onClick = {}
        )

        NetworkBar(
            network = network,
            selectNetwork = { uiEvent(HomeEvent.NetworkChange(it)) },
        )
    }
}

@Composable
fun BoxScope.NetworkBar(
    network: SupportNetwork,
    selectNetwork: (SupportNetwork) -> Unit,
) {
    var showDialog by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .align(Alignment.Center)
            .clip(shape = RoundedCornerShape(32.dp))
            .background(color = MaterialTheme.colorScheme.inverseOnSurface)
            .clickable { showDialog = true }
            .padding(horizontal = 8.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (network == SupportNetwork.MAIN) {
            Image(
                painter = painterResource(id = R.drawable.ic_eth),
                contentDescription = null,
                modifier = Modifier
                    .size(16.dp)
                    .padding(top = 2.dp),
            )
        } else {
            Text(
                text = network.initial,
                modifier = Modifier
                    .background(color = network.color, shape = CircleShape)
                    .size(16.dp)
                    .wrapContentHeight(),
                color = Color.White,
                fontSize = 10.sp,
                textAlign = TextAlign.Center,
            )
        }

        Text(
            text = stringResource(id = network.networkName),
            modifier = Modifier.padding(horizontal = 16.dp),
            fontSize = 14.sp,
        )

        Icon(
            painter = painterResource(id = R.drawable.ic_arrow_down),
            contentDescription = null,
            modifier = Modifier
                .size(16.dp)
                .padding(top = 2.dp),
            tint = MaterialTheme.colorScheme.onSurface,
        )
    }

    if (showDialog) {
        NetworkChangeDialog(
            network = network,
            selectNetwork = {
                selectNetwork(it)
                showDialog = false
            },
            onDismiss = { showDialog = false }
        )
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun PreviewHomeTopBar() {
    BunnyWalletTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            HomeTopBar(
                network = SupportNetwork.MAIN,
                uiEvent = {},
            )
        }
    }
}