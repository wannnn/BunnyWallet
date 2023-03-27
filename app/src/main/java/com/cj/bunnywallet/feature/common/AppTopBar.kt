package com.cj.bunnywallet.feature.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cj.bunnywallet.R

@Composable
fun AppTopBar(
    onBackClicked: () -> Unit,
    showBackBtn: Boolean = true,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
    ) {

        if (showBackBtn) {
            IconButton(
                onClick = onBackClicked,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .align(Alignment.CenterStart)
            ) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBack,
                    contentDescription = null
                )
            }
        }

        Text(
            text = stringResource(id = R.string.app_name),
            modifier = Modifier
                .padding(top = 16.dp, bottom = 16.dp)
                .align(Alignment.Center),
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAppTopBar() {
    AppTopBar(onBackClicked = {})
}