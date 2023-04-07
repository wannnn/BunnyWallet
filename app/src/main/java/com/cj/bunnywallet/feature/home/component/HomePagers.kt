package com.cj.bunnywallet.feature.home.component

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.cj.bunnywallet.feature.home.HomeEvent
import com.cj.bunnywallet.feature.home.HomeState
import com.cj.bunnywallet.ui.theme.BunnyWalletTheme


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomePagers(
    pagerState: PagerState,
    uiState: HomeState,
    uiEvent: (HomeEvent) -> Unit
) {
    HorizontalPager(
        pageCount = 2,
        state = pagerState,
    ) {
        when (it) {
            0 -> TokensList(uiState, uiEvent)
            1 -> NFTsList()
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun PreviewHomePagers() {
    BunnyWalletTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            HomePagers(rememberPagerState(), HomeState()) {}
        }
    }
}