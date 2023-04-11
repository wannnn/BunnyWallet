package com.cj.bunnywallet.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cj.bunnywallet.datasource.WalletDataStore
import com.cj.bunnywallet.extensions.shortAddress
import com.cj.bunnywallet.navigation.AppNavigator
import com.cj.bunnywallet.navigation.NavEvent
import com.cj.bunnywallet.navigation.route.MainRoute
import com.cj.bunnywallet.navigation.route.ManageWalletRoute
import com.cj.bunnywallet.proto.wallet.Account
import com.cj.bunnywallet.proto.wallet.Wallet
import com.cj.bunnywallet.reducer.Reducer
import com.cj.bunnywallet.reducer.ReducerImp
import com.cj.bunnywallet.utils.Web3jManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    navigator: AppNavigator,
    private val web3jManager: Web3jManager,
    private val walletDS: WalletDataStore,
) : ViewModel(), AppNavigator by navigator,
    Reducer<HomeState> by ReducerImp(HomeState()) {

    // These can remove if not use in future
    private var wallet: Wallet = Wallet.getDefaultInstance()
    private var account: Account = Account.getDefaultInstance()

    init {
        getWallet()
    }

    @OptIn(FlowPreview::class)
    private fun getWallet() {
        walletDS.selectedWallet
            .onEach {
                wallet = it
                account = it.accountsMap.getOrDefault(
                    it.selectedAccount,
                    Account.getDefaultInstance(),
                )
                uiState = uiState.copy(
                    accountName = account.name,
                    accountAddress = account.address.shortAddress,
                )
            }
            .flatMapMerge { web3jManager.getEthBalance(account.address) }
            .onEach { balanceEth -> uiState = uiState.copy(balance = balanceEth.toString()) }
            .catch { e -> Timber.d(message = "Get Balance Error: ${e.message}") }
            .launchIn(viewModelScope)
    }

    fun handleEvent(e: HomeEvent) {
        when (e) {
            HomeEvent.ManageWallet ->
                navigateTo(NavEvent.NavTo(route = ManageWalletRoute.ManageWallet.route))

            HomeEvent.NavToManageCrypto ->
                navigateTo(NavEvent.NavTo(route = MainRoute.ManageCrypto.route))
        }
    }
}