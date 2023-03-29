package com.cj.bunnywallet.feature.startupscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.cj.bunnywallet.R

@Composable
fun StartupScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_bunny),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(fraction = 0.4f)
                .aspectRatio(1f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewStartupScreen() {
    StartupScreen()
}