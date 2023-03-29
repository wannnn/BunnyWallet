package com.cj.bunnywallet.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cj.bunnywallet.KEY_MNEMONIC
import com.cj.bunnywallet.datasource.BunnyDataStore
import com.cj.bunnywallet.navigation.AppNavigator
import com.cj.bunnywallet.reducer.Reducer
import com.cj.bunnywallet.reducer.ReducerImp
import com.cj.bunnywallet.utils.CryptoManager
import com.cj.bunnywallet.utils.Web3jManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.web3j.crypto.Bip44WalletUtils
import org.web3j.protocol.core.DefaultBlockParameterName
import org.web3j.utils.Convert
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val cryptoManager: CryptoManager,
    private val web3jManager: Web3jManager,
    private val dataStore: BunnyDataStore,
    private val navigator: AppNavigator
) : ViewModel(), AppNavigator by navigator, Reducer<HomeState> by ReducerImp(HomeState()) {

    init {
        getAccountBalance()
    }

    private fun getAccountBalance() = viewModelScope.launch(Dispatchers.IO) {
        dataStore.getString(KEY_MNEMONIC)
            .collect {
                val mnemonic = cryptoManager.decrypt(it) ?: return@collect
                val credentials = Bip44WalletUtils.loadBip44Credentials("", mnemonic)
                val address = credentials.address

                val balanceWei = web3jManager.getWeb3j().ethGetBalance(
                    address,
                    DefaultBlockParameterName.LATEST
                ).send()

                println(balanceWei.balance)

                val balanceInEther = Convert.fromWei(balanceWei.balance.toString(), Convert.Unit.ETHER)

                println(balanceInEther)

                uiState = uiState.copy(balance = balanceInEther.toString())
            }
    }
}