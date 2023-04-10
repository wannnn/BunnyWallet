package com.cj.bunnywallet.navigation.route

sealed interface CreateWalletRoute : Routes {

    object CreateWallet : MainRoute {
        override val route: String = "create_wallet_route"
    }

    object SecureWallet : CreateWalletRoute {
        override val route: String = "secure_wallet_route"
    }

    object ConfirmSRP : CreateWalletRoute {
        const val SRP = "srp"
        private const val BASE_ROUTE = "confirm_srp_route"

        override val route: String = "$BASE_ROUTE/{$SRP}"

        fun genRoute(srp: String) = "$BASE_ROUTE/$srp"
    }

    object Completed : CreateWalletRoute {
        override val route: String = "create_wallet_completed_route"
    }
}