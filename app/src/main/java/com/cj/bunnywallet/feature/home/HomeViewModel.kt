package com.cj.bunnywallet.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cj.bunnywallet.KEY_NETWORK
import com.cj.bunnywallet.datasource.local.BunnyPreferencesDataStore
import com.cj.bunnywallet.datasource.local.WalletDataStore
import com.cj.bunnywallet.extensions.shortAddress
import com.cj.bunnywallet.feature.home.type.SupportNetwork
import com.cj.bunnywallet.helper.ApiHostHelper
import com.cj.bunnywallet.navigation.AppNavigator
import com.cj.bunnywallet.navigation.NavEvent
import com.cj.bunnywallet.navigation.route.MainRoute
import com.cj.bunnywallet.navigation.route.ManageWalletRoute
import com.cj.bunnywallet.proto.wallet.Account
import com.cj.bunnywallet.reducer.Reducer
import com.cj.bunnywallet.reducer.ReducerImp
import com.cj.bunnywallet.utils.Web3jManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    navigator: AppNavigator,
    private val web3jManager: Web3jManager,
    private val apiHostHelper: ApiHostHelper,
    private val preferenceDS: BunnyPreferencesDataStore,
    private val walletDS: WalletDataStore,
) : ViewModel(), AppNavigator by navigator,
    Reducer<HomeState> by ReducerImp(HomeState()) {

    // These can remove if not use in future
    private var account: Account = Account.getDefaultInstance()

    init {
        checkIsConnected { setup() }
    }

    @OptIn(FlowPreview::class)
    private suspend fun setup() {
        combine(
            preferenceDS.getString(KEY_NETWORK),
            walletDS.currentAccount,
        ) { network, acc ->
            account = acc

            fun setupNetwork() = when (network) {
                SupportNetwork.MAIN.name -> SupportNetwork.MAIN
                SupportNetwork.GOERLI.name -> SupportNetwork.GOERLI
                else -> SupportNetwork.SEPOLIA
            }

            uiState = uiState.copy(
                network = setupNetwork(),
                accountName = account.name,
                accountAddress = account.address.shortAddress,
            )
        }
            .flatMapMerge { getEthBalance() }
            .collect()
    }

    private fun getEthBalance() = web3jManager.getEthBalance(account.address)
        .onEach { balanceEth -> uiState = uiState.copy(balance = balanceEth.toString()) }

    fun handleEvent(e: HomeEvent) {
        when (e) {
            is HomeEvent.NetworkChange -> viewModelScope.launch {
                preferenceDS.putString(KEY_NETWORK, e.network.name)
                apiHostHelper.updateUrl(e.network)
                checkIsConnected { getEthBalance().collect() }
                uiState = uiState.copy(network = e.network)
            }

            HomeEvent.ManageWallet ->
                navigateTo(NavEvent.NavTo(route = ManageWalletRoute.ManageWallet.route))

            HomeEvent.NavToManageCrypto ->
                navigateTo(NavEvent.NavTo(route = MainRoute.ManageCrypto.route))
        }
    }

    private fun checkIsConnected(action: suspend () -> Unit) =
        viewModelScope.launch(Dispatchers.IO) {
            while (web3jManager.getVersion() == null) {
                delay(RETRY_DELAY_TIME)
            }
            action()
        }

    private companion object {
        const val RETRY_DELAY_TIME = 200L
    }
}