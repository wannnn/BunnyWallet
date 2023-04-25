package com.cj.bunnywallet.feature.managewallet.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cj.bunnywallet.datasource.local.WalletDataStore
import com.cj.bunnywallet.feature.managewallet.edit.type.EditType
import com.cj.bunnywallet.model.managewallet.EditInfo
import com.cj.bunnywallet.model.managewallet.EditWalletDisplay
import com.cj.bunnywallet.navigation.AppNavigator
import com.cj.bunnywallet.reducer.Reducer
import com.cj.bunnywallet.reducer.ReducerImp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditWalletViewModel @Inject constructor(
    navigator: AppNavigator,
    private val walletDS: WalletDataStore,
) : ViewModel(), AppNavigator by navigator,
    Reducer<EditWalletState> by ReducerImp(EditWalletState()) {

    init {
        fetchWallets()
    }

    private fun fetchWallets() {
        walletDS.wallets.onEach { wallets ->
            val displayData = wallets.walletsMap.map { w ->
                val accounts = w.value.accountsMap.map { acc ->
                    EditWalletDisplay.EditAccountDisplay(
                        address = acc.key,
                        name = acc.value.name,
                    )
                }
                EditWalletDisplay(
                    id = w.key,
                    name = w.value.name,
                    accounts = accounts,
                )
            }
            uiState = uiState.copy(wallets = displayData)
        }.launchIn(viewModelScope)
    }

    fun handleEvent(e: EditWalletEvent) {
        when (e) {
            is EditWalletEvent.EditWalletName -> {
                val info = EditInfo(
                    walletId = e.walletId,
                    address = "",
                    name = e.walletName,
                    type = EditType.Wallet,
                )
                uiState = uiState.copy(editInfo = info)
            }

            is EditWalletEvent.EditAccountName -> {
                val info = EditInfo(
                    walletId = e.walletId,
                    address = e.address,
                    name = e.accountName,
                    type = EditType.Account,
                )
                uiState = uiState.copy(editInfo = info)
            }

            is EditWalletEvent.EditDone -> {
                when (e.editInfo.type) {
                    EditType.Wallet -> viewModelScope.launch {
                        walletDS.editWalletName(
                            walletId = e.editInfo.walletId,
                            newName = e.editInfo.name,
                        )
                    }

                    EditType.Account -> viewModelScope.launch {
                        walletDS.editAccountName(
                            walletId = e.editInfo.walletId,
                            address = e.editInfo.address,
                            newName = e.editInfo.name,
                        )
                    }
                }
                uiState = uiState.copy(editInfo = null)
            }

            is EditWalletEvent.DeleteWallet -> viewModelScope.launch {
                walletDS.deleteWallet(walletId = e.walletId)
            }

            EditWalletEvent.NoUpdate -> uiState = uiState.copy(editInfo = null)

        }
    }
}