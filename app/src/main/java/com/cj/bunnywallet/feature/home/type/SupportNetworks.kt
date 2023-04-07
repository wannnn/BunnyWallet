package com.cj.bunnywallet.feature.home.type

import androidx.compose.ui.graphics.Color
import com.cj.bunnywallet.R
import com.cj.bunnywallet.ui.theme.Amber200
import com.cj.bunnywallet.ui.theme.Blue200
import com.cj.bunnywallet.ui.theme.Pink200
import com.cj.bunnywallet.ui.theme.Purple200

enum class SupportNetworks(val networkName: Int, val initial: String, val color: Color) {
    MAIN(networkName = R.string.ethereum_main_network, initial = "", color = Color.Transparent),
    ROPSTEN(networkName = R.string.ropsten_test_network, initial = "R", color = Pink200),
    KOVAN(networkName = R.string.kovan_test_network, initial = "K", color = Purple200),
    RINKEBY(networkName = R.string.rinkeby_test_network, initial = "R", color = Amber200),
    GOERLI(networkName = R.string.goerli_test_network, initial = "G", color = Blue200),
}
