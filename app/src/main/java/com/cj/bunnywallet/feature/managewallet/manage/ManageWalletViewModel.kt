package com.cj.bunnywallet.feature.managewallet.manage

import androidx.lifecycle.ViewModel
import com.cj.bunnywallet.extensions.indexOfFirstOrNull
import com.cj.bunnywallet.navigation.AppNavigator
import com.cj.bunnywallet.navigation.NavEvent
import com.cj.bunnywallet.reducer.Reducer
import com.cj.bunnywallet.reducer.ReducerImp
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ManageWalletViewModel @Inject constructor(
    private val navigator: AppNavigator
) : ViewModel(), AppNavigator by navigator,
    Reducer<ManageWalletState> by ReducerImp(ManageWalletState()) {

    private val fakeData = listOf(
        Wallet(
            id = "1",
            name = "ETH wallet",
            accounts = listOf(
                Wallet.Account(address = "0x11", name = "Stake account", amount = 100.0),
                Wallet.Account(address = "0x12", name = "Default account", amount = 20.2),
            ),
            isExpand = true,
        ),
        Wallet(
            id = "2",
            name = "To the moon wallet",
            accounts = listOf(
                Wallet.Account(address = "0x21", name = "Shit coin account", amount = 666.6),
                Wallet.Account(address = "0x22", name = "Doge coin account", amount = 222.2),
            )
        ),
    )

    init {
        uiState = uiState.copy(wallets = fakeData, currentAccount = "0x21")
    }

    fun handleEvent(e: ManageWalletEvent) {
        when (e) {
            is ManageWalletEvent.ExpandWallet -> {
                val newWallets = uiState.wallets.toMutableList()

                uiState.wallets.indexOfFirstOrNull { it.id == e.walletId }
                    ?.let {
                        val wallet = uiState.wallets[it]
                        newWallets[it] = wallet.copy(isExpand = !wallet.isExpand)
                    }

                uiState = uiState.copy(wallets = newWallets)
            }

            is ManageWalletEvent.AddAccount -> {
                val newWallets = uiState.wallets.toMutableList()

                uiState.wallets.indexOfFirstOrNull { it.id == e.walletId }
                    ?.let {
                        // Temp logic for UI display,
                        // will update when implement real add account logic
                        val wallet = uiState.wallets[it]
                        val newAccounts = wallet.accounts.toMutableList()
                            .apply {
                                val num = size + 1
                                val newAccount = Wallet.Account(
                                    address = "0x${wallet.id}$num",
                                    name = "Account $num",
                                    amount = 0.0,
                                )
                                add(newAccount)
                            }
                        newWallets[it] = wallet.copy(accounts = newAccounts)
                    }

                uiState = uiState.copy(wallets = newWallets)
            }

            is ManageWalletEvent.SelectAccount ->
                uiState = uiState.copy(currentAccount = e.address)

            ManageWalletEvent.OnBackClick -> navigateTo(NavEvent.NavBack)
        }
    }
}