package com.cj.bunnywallet.feature.customcrypto

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cj.bunnywallet.datasource.local.WalletDataStore
import com.cj.bunnywallet.datasource.repo.TokenRepository
import com.cj.bunnywallet.extensions.onLoading
import com.cj.bunnywallet.model.token.TokenMetadataParams
import com.cj.bunnywallet.navigation.AppNavigator
import com.cj.bunnywallet.navigation.NavEvent
import com.cj.bunnywallet.proto.wallet.crypto
import com.cj.bunnywallet.reducer.Reducer
import com.cj.bunnywallet.reducer.ReducerImp
import com.cj.bunnywallet.utils.SnackBarManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CustomCryptoViewModel @Inject constructor(
    private val repo: TokenRepository,
    private val snackBarManager: SnackBarManager,
    private val walletDS: WalletDataStore,
    private val navigator: AppNavigator,
) : ViewModel(), AppNavigator by navigator, Reducer<CustomCryptoState> by ReducerImp(
    CustomCryptoState()
) {

    fun handleEvent(e: CustomCryptoEvent) {

        when (e) {
            is CustomCryptoEvent.InputAddress -> {
                uiState = uiState.copy(address = e.addr, btnEnable = e.addr.isNotBlank())
            }
            is CustomCryptoEvent.InputSymbol -> {
                uiState = uiState.copy(symbol = e.symbol)
            }
            is CustomCryptoEvent.InputDecimal -> {
                uiState = uiState.copy(decimal = e.decimal)
            }
            CustomCryptoEvent.Confirm -> {
                getTokenMetadata()
            }
        }
    }

    private fun getTokenMetadata() {
        val params = TokenMetadataParams(
            params = listOf(uiState.address)
        )
        repo.getTokenMetadata(params)
            .onLoading { uiState = uiState.copy(isLoading = it) }
            .onEach {
                walletDS.updateAddedCrypto(crypto {
                    address = uiState.address
                    name = it.name.orEmpty()
                    symbol = it.symbol.orEmpty()
                    decimal = it.decimals ?: 0
                    logo = it.logo.orEmpty()
                })
                navigator.navigateTo(NavEvent.NavBack)
            }
            .catch { snackBarManager.showMessage(it.message) }
            .launchIn(viewModelScope)
    }
}