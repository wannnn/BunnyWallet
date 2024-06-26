package com.cj.bunnywallet.navigation.route

sealed interface MainRoute : Routes {

    object Startup : MainRoute {
        override val route: String = "startup_route"
    }

    object CreatePassword : CreateWalletRoute {
        override val route: String = "create_password_route"
    }

    object Unlock : MainRoute {
        override val route: String = "unlock_route"
    }

    object WalletSetup : MainRoute {
        override val route: String = "wallet_setup_route"
    }

    object CreateWallet : MainRoute {
        override val route: String = "create_wallet_route"
    }

    object ImportWallet : MainRoute {
        override val route: String = "import_wallet_route"
    }

    object Home : MainRoute {
        override val route: String = "home_route"
    }

    object ManageCrypto : MainRoute {
        override val route: String = "manage_crypto_route"
    }

    object CustomCrypto : MainRoute {
        override val route: String = "custom_crypto_route"
    }
}
