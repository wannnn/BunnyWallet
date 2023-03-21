package com.cj.bunnywallet.navigation.route

sealed interface CreateWalletRoute : Routes {

    object CreatePassword : CreateWalletRoute {
        override val route: String = "create_password_route"
    }

    object SecureWallet : CreateWalletRoute {
        const val PWD = "pwd"
        private const val BASE_ROUTE = "secure_wallet_route"

        override val route: String = "$BASE_ROUTE/{$PWD}"

        fun genRoute(pwd: String) = "$BASE_ROUTE/$pwd"
    }

    object ConfirmSRP : CreateWalletRoute {
        const val SRP = "srp"
        private const val BASE_ROUTE = "confirm_srp_route"

        override val route: String = "$BASE_ROUTE/{$SRP}"

        fun genRoute(srp: String) = "$BASE_ROUTE/$srp"
    }

    object CreateWalletCompleted : CreateWalletRoute {
        override val route: String = "create_wallet_completed_route"
    }
}