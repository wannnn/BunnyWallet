package com.cj.bunnywallet.feature.home

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cj.bunnywallet.feature.home.component.AccountInfo
import com.cj.bunnywallet.feature.home.component.BalanceInfo
import com.cj.bunnywallet.feature.home.component.HomePagers
import com.cj.bunnywallet.feature.home.component.HomeTabs
import com.cj.bunnywallet.feature.home.component.HomeTopBar
import com.cj.bunnywallet.ui.theme.BunnyWalletTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(uiState: HomeState) {

    val pagerState = rememberPagerState()

    Column(modifier = Modifier.fillMaxSize()) {
        HomeTopBar()

        AccountInfo()

        HomeTabs(pagerState)

        HomePagers(pagerState, uiState)
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun PreviewHomeScreen() {
    BunnyWalletTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            HomeScreen(
                uiState = HomeState(),
            )
        }
    }
}