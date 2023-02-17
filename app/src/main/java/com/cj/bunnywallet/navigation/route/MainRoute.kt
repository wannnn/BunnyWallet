package com.cj.bunnywallet.navigation.route

sealed interface MainRoute : Routes {

    object Entrance : MainRoute {
        override val route: String = "entrance_route"
    }

    object CreateWallet : MainRoute {
        override val route: String = "create_wallet_route"
    }

    object ImportWallet : MainRoute {
        override val route: String = "import_wallet_route"
    }
}
