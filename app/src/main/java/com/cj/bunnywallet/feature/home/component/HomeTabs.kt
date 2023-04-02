package com.cj.bunnywallet.feature.home.component

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.cj.bunnywallet.R
import com.cj.bunnywallet.ui.theme.BunnyWalletTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeTabs(pagerState: PagerState) {
    val scope = rememberCoroutineScope()

    TabRow(
        selectedTabIndex = pagerState.currentPage,
        contentColor = MaterialTheme.colorScheme.primary,
    ) {
        HomeTabType.values().forEach {
            Tab(
                selected = pagerState.currentPage == it.page,
                onClick = {
                    scope.launch { pagerState.animateScrollToPage(page = it.page) }
                },
                text = { Text(text = stringResource(id = it.tabName)) },
                unselectedContentColor = MaterialTheme.colorScheme.inverseSurface
            )
        }
    }
}

enum class HomeTabType(val tabName: Int, val page: Int) {
    TOKEN(tabName = R.string.tokens, page = 0),
    NFT(tabName = R.string.nft, page = 1)
}

@OptIn(ExperimentalFoundationApi::class)
@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun PreviewHomeTabs() {
    BunnyWalletTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            HomeTabs(rememberPagerState())
        }
    }
}
