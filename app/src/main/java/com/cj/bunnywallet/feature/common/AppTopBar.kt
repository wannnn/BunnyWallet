package com.cj.bunnywallet.feature.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.cj.bunnywallet.R
import com.cj.bunnywallet.ui.theme.Lavender100

@Composable
fun AppTopBar() {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .background(color = Lavender100)
            .zIndex(1f),
    ) {

        Image(
            painter = painterResource(id = R.drawable.ic_arrow_back),
            contentDescription = null,
            modifier = Modifier
                .padding(start = 24.dp)
                .clickable {
                    // TODO back action
                }
                .align(Alignment.CenterStart)
        )

        Text(
            text = stringResource(id = R.string.app_name),
            modifier = Modifier
                .padding(top = 16.dp, bottom = 16.dp)
                .align(Alignment.Center),
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@Preview
@Composable
fun PreviewAppTopBar() {
    AppTopBar()
}