package com.cj.bunnywallet.feature.managewallet.manage

import androidx.lifecycle.ViewModel
import com.cj.bunnywallet.extensions.indexOfFirstOrNull
import com.cj.bunnywallet.model.wallet.WalletDisplay
import com.cj.bunnywallet.navigation.AppNavigator
import com.cj.bunnywallet.navigation.NavEvent
import com.cj.bunnywallet.proto.wallet.account
import com.cj.bunnywallet.proto.wallet.wallet
import com.cj.bunnywallet.proto.wallet.wallets
import com.cj.bunnywallet.reducer.Reducer
import com.cj.bunnywallet.reducer.ReducerImp
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ManageWalletViewModel @Inject constructor(
    private val navigator: AppNavigator
) : ViewModel(), AppNavigator by navigator,
    Reducer<ManageWalletState> by ReducerImp(ManageWalletState()) {

    init {
        uiState = uiState.copy(wallets = genFakeData(), currentAccount = "0x21")
    }

    private fun genFakeData(): List<WalletDisplay> {
        val w1 = wallet {
            id = "1"
            name = "ETH wallet"
            accounts.putAll(
                mapOf(
                    "0x11" to account { address = "0x11"; name = "Stake account" },
                    "0x12" to account { address = "0x12"; name = "Default account" },
                )
            )
        }
        val w2 = wallet {
            id = "2"
            name = "To the moon wallet"
            accounts.putAll(
                mapOf(
                    "0x21" to account { address = "0x21"; name = "Shit coin account" },
                    "0x22" to account { address = "0x22"; name = "Doge coin account" },
                )
            )
        }
        return wallets { wallets.putAll(mapOf("w1" to w1, "w2" to w2)) }
            .walletsMap
            .values
            .map { w ->
                WalletDisplay(
                    id = w.id,
                    name = w.name,
                    accounts = w.accountsMap.values.map { acc ->
                        WalletDisplay.AccountDisplay(address = acc.address, name = acc.name)
                    },
                )
            }
    }

    fun handleEvent(e: ManageWalletEvent) {
        when (e) {
            is ManageWalletEvent.ExpandWallet -> {
                val newWallets = uiState.wallets.toMutableList()

                uiState.wallets.indexOfFirstOrNull { it.id == e.walletId }?.let {
                    val wallet = uiState.wallets[it]
                    newWallets[it] = wallet.copy(isExpand = !wallet.isExpand)
                }

                uiState = uiState.copy(wallets = newWallets)
            }

            is ManageWalletEvent.AddAccount -> {
                val newWallets = uiState.wallets.toMutableList()

                uiState.wallets.indexOfFirstOrNull { it.id == e.walletId }?.let {
                    // Temp logic for UI display,
                    // will update when implement real add account logic
                    val wallet = uiState.wallets[it]
                    val newAccounts = wallet.accounts.toMutableList().apply {
                        val num = size + 1
                        val newAccount = WalletDisplay.AccountDisplay(
                            address = "0x${wallet.id}$num",
                            name = "Account $num",
                        )
                        add(newAccount)
                    }
                    newWallets[it] = wallet.copy(accounts = newAccounts)
                }

                uiState = uiState.copy(wallets = newWallets)
            }

            is ManageWalletEvent.SelectAccount -> uiState = uiState.copy(currentAccount = e.address)

            ManageWalletEvent.OnBackClick -> navigateTo(NavEvent.NavBack)
        }
    }
}