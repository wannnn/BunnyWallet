package com.cj.bunnywallet.feature.home.type

import androidx.compose.ui.graphics.Color
import com.cj.bunnywallet.R
import com.cj.bunnywallet.ui.theme.Blue200
import com.cj.bunnywallet.ui.theme.Purple200

enum class SupportNetwork(val networkName: Int, val initial: String, val color: Color) {
    MAIN(networkName = R.string.ethereum_main_network, initial = "", color = Color.Transparent),
    SEPOLIA(networkName = R.string.sepolia_test_network, initial = "S", color = Purple200),
    GOERLI(networkName = R.string.goerli_test_network, initial = "G", color = Blue200),
}
