package com.cj.bunnywallet.navigation.route

sealed interface ManageWalletRoute : Routes {

    object ManageWalletGraph : ManageWalletRoute {
        override val route: String = "manage_wallet_graph_route"
    }

    object ManageWallet : ManageWalletRoute {
        override val route: String = "manage_wallet_route"
    }

    object EditWallet : ManageWalletRoute {
        override val route: String = "edit_wallet_route"
    }
}