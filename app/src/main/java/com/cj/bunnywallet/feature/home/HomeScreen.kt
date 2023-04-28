package com.cj.bunnywallet.feature.home

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cj.bunnywallet.feature.home.component.AccountInfo
import com.cj.bunnywallet.feature.home.component.HomePagers
import com.cj.bunnywallet.feature.home.component.HomeTabs
import com.cj.bunnywallet.feature.home.component.HomeTopBar
import com.cj.bunnywallet.feature.home.component.TransactionView
import com.cj.bunnywallet.feature.home.type.TransactionType
import com.cj.bunnywallet.ui.theme.BunnyWalletTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    uiState: HomeState,
    uiEvent: (HomeEvent) -> Unit,
) {
    val pagerState = rememberPagerState()

    Column(modifier = Modifier.fillMaxSize()) {
        HomeTopBar(
            network = uiState.network,
            uiEvent = uiEvent,
        )

        AccountInfo(
            accountName = uiState.accountName,
            accountAddress = uiState.accountAddress,
            onManageWalletClicked = { uiEvent(HomeEvent.ManageWallet) },
        )

        TransactionView {
            when (it) {
                TransactionType.SEND -> {

                }
                TransactionType.RECEIVE -> {

                }
                TransactionType.BUY -> {

                }
                TransactionType.HISTORY -> {

                }
            }
        }

        HomeTabs(pagerState)

        HomePagers(pagerState, uiState, uiEvent)
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun PreviewHomeScreen() {
    BunnyWalletTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            HomeScreen(uiState = HomeState(), uiEvent = {})
        }
    }
}