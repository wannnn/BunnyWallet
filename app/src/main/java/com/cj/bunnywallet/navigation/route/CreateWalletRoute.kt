package com.cj.bunnywallet.navigation.route

sealed interface CreateWalletRoute : Routes {

    object CreatePassword : CreateWalletRoute {
        override val route: String = "create_password_route"
    }

    object SecureWallet : CreateWalletRoute {
        override val route: String = "secure_wallet_route"
    }
}