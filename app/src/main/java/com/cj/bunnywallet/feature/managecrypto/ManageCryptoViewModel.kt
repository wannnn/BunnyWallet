package com.cj.bunnywallet.feature.managecrypto

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cj.bunnywallet.datasource.local.WalletDataStore
import com.cj.bunnywallet.navigation.AppNavigator
import com.cj.bunnywallet.proto.wallet.crypto
import com.cj.bunnywallet.reducer.Reducer
import com.cj.bunnywallet.reducer.ReducerImp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ManageCryptoViewModel @Inject constructor(
    private val walletDS: WalletDataStore,
    private val navigator: AppNavigator,
) : ViewModel(), AppNavigator by navigator,
    Reducer<ManageCryptoState> by ReducerImp(ManageCryptoState()) {

    init {
        walletDS.currentAccount.onEach {
            uiState = uiState.copy(added = it.cryptosList)
        }.launchIn(viewModelScope)
        addFakeData()
    }

    private fun addFakeData() {
        val tokens = listOf(
            crypto {
                decimal = DECIMAL_6
                logo = "https://static.alchemyapi.io/images/assets/825.png"
                name = "Tether"
                symbol = "USDT"
            },
            crypto {
                decimal = DECIMAL_6
                logo = "https://static.alchemyapi.io/images/assets/3408.png"
                name = "USD Coin"
                symbol = "USDC"
            },
            crypto {
                decimal = DECIMAL_18
                logo = "https://static.alchemyapi.io/images/assets/5994.png"
                name = "Shiba Inu"
                symbol = "SHIB"
            },
            crypto {
                decimal = DECIMAL_18
                logo = "https://static.alchemyapi.io/images/assets/4705.png"
                name = "PAX Gold"
                symbol = "PAXG"
            },
            crypto {
                decimal = DECIMAL_18
                logo = "https://static.alchemyapi.io/images/assets/18876.png"
                name = "ApeCoin"
                symbol = "APE"
            },
            crypto {
                decimal = DECIMAL_18
                logo = "https://static.alchemyapi.io/images/assets/3890.png"
                name = "Polygon"
                symbol = "MATIC"
            }
        )

        uiState = uiState.copy(popular = tokens)
    }

    fun handleEvent() {
        // TODO
    }
}

private const val DECIMAL_6 = 6
private const val DECIMAL_18 = 18