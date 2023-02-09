package com.cj.bunnywallet.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cj.bunnywallet.navigation.NavEvent
import com.cj.bunnywallet.navigation.route.MainRoute

@Composable
fun ScreenA(navEvent: (NavEvent.NavTo) -> Unit) {
    ContentA(navEvent)
}

@Composable
fun ContentA(navEvent: (NavEvent.NavTo) -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = "Screen A",
            modifier = Modifier.clickable {
                println("Screen A click")
                navEvent(NavEvent.NavTo(MainRoute.ScreenB.route))
            }
        )
    }
}

@Preview
@Composable
fun PreviewContentA() {
    ContentA {}
}