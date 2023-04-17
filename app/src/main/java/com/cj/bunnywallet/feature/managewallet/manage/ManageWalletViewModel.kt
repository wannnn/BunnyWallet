package com.cj.bunnywallet.feature.managewallet.manage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cj.bunnywallet.datasource.local.WalletDataStore
import com.cj.bunnywallet.extensions.indexOfFirstOrNull
import com.cj.bunnywallet.feature.managewallet.manage.type.ManageType
import com.cj.bunnywallet.model.wallet.WalletDisplay
import com.cj.bunnywallet.navigation.AppNavigator
import com.cj.bunnywallet.navigation.NavEvent
import com.cj.bunnywallet.navigation.route.MainRoute
import com.cj.bunnywallet.proto.wallet.Wallets
import com.cj.bunnywallet.reducer.Reducer
import com.cj.bunnywallet.reducer.ReducerImp
import com.cj.bunnywallet.utils.CryptoManager
import com.cj.bunnywallet.utils.Web3jManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ManageWalletViewModel @Inject constructor(
    navigator: AppNavigator,
    private val walletDS: WalletDataStore,
    private val cryptoManager: CryptoManager,
    private val web3jManager: Web3jManager,
) : ViewModel(), AppNavigator by navigator,
    Reducer<ManageWalletState> by ReducerImp(ManageWalletState()) {

    init {
        getWallets()
    }

    private fun getWallets() {
        walletDS.wallets
            .onEach { uiState = uiState.copy(wallets = genDisplayData(it)) }
            .launchIn(viewModelScope)
    }

    private fun genDisplayData(wallets: Wallets): List<WalletDisplay> {
        return wallets.walletsMap
            .values
            .map { w ->
                val accounts = w.accountsMap.values.map { acc ->
                    WalletDisplay.AccountDisplay(
                        address = acc.address,
                        name = acc.name,
                        isCurrent = acc.address == wallets.currentAccount,
                    )
                }
                WalletDisplay(
                    id = w.id,
                    name = w.name,
                    accounts = accounts,
                    isExpand = w.id == wallets.currentWallet,
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
                val decryptId = cryptoManager.decrypt(e.walletId) ?: run {
                    Timber.d("Wallet ID decrypt fail")
                    return
                }
                val childNumber = uiState.wallets.find { it.id == e.walletId }
                    ?.accounts?.size
                    ?: run {
                        Timber.d("Get current account size fail")
                        return
                    }
                val account = web3jManager.deriveAccount(
                    mnemonic = decryptId,
                    childNumber = childNumber,
                )
                viewModelScope.launch {
                    walletDS.createAccount(e.walletId, account)
                }
            }

            is ManageWalletEvent.SelectAccount -> viewModelScope.launch {
                walletDS.updateCurrentAccount(e.walletId, e.address)
            }

            is ManageWalletEvent.MenuItemClick -> handleManageAction(e.type)

            ManageWalletEvent.OnBackClick -> navigateTo(NavEvent.NavBack)
        }
    }

    private fun handleManageAction(type: ManageType) {
        when (type) {
            ManageType.ADD_WALLET -> {
                navigateTo(NavEvent.NavTo(MainRoute.WalletSetup.route))
            }

            ManageType.EDIT_WALLET -> {
                // TODO
            }
        }
    }
}