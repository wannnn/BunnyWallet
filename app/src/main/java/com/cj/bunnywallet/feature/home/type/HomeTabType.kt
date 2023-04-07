package com.cj.bunnywallet.feature.home.type

import com.cj.bunnywallet.R

enum class HomeTabType(val tabName: Int, val page: Int) {
    TOKEN(tabName = R.string.tokens, page = 0),
    NFT(tabName = R.string.nft, page = 1)
}