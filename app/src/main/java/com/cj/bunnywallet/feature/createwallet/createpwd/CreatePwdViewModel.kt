package com.cj.bunnywallet.feature.createwallet.createpwd

import androidx.lifecycle.ViewModel
import com.cj.bunnywallet.navigation.AppNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreatePwdViewModel @Inject constructor(
    appNavigator: AppNavigator,
) : ViewModel(), AppNavigator by appNavigator