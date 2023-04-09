package com.cj.bunnywallet.feature.managecrypto

import androidx.lifecycle.ViewModel
import com.cj.bunnywallet.navigation.AppNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ManageCryptoViewModel @Inject constructor(
    private val navigator: AppNavigator,
) : ViewModel(), AppNavigator by navigator {

    fun handleEvent() {
        // TODO
    }
}