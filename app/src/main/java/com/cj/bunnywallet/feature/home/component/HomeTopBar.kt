package com.cj.bunnywallet.feature.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cj.bunnywallet.R
import com.cj.bunnywallet.feature.common.CommonIconBtn
import com.cj.bunnywallet.ui.theme.Gray100

@Composable
fun HomeTopBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
    ) {
        CommonIconBtn(
            icon = R.drawable.ic_menu,
            modifier = Modifier
                .padding(start = 8.dp)
                .align(Alignment.CenterStart),
            onClick = {}
        )

        NetworkBar()
    }
}

@Composable
fun BoxScope.NetworkBar() {
    Row(
        modifier = Modifier
            .align(Alignment.Center)
            .background(
                color = Gray100,
                shape = RoundedCornerShape(size = 32.dp),
            )
            .padding(horizontal = 8.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_eth),
            contentDescription = null,
            modifier = Modifier
                .size(16.dp)
                .padding(top = 2.dp),
        )

        Text(
            text = stringResource(id = R.string.ethereum_main_network),
            modifier = Modifier.padding(horizontal = 16.dp),
            fontSize = 14.sp,
        )

        Image(
            painter = painterResource(id = R.drawable.ic_arrow_down),
            contentDescription = null,
            modifier = Modifier
                .size(16.dp)
                .padding(top = 2.dp),
        )
    }
}