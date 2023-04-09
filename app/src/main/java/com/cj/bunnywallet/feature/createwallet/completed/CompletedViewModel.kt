package com.cj.bunnywallet.feature.createwallet.completed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavOptions
import com.cj.bunnywallet.KEY_HINT
import com.cj.bunnywallet.datasource.BunnyPreferencesDataStore
import com.cj.bunnywallet.navigation.AppNavigator
import com.cj.bunnywallet.navigation.NavEvent
import com.cj.bunnywallet.navigation.route.CreateWalletRoute
import com.cj.bunnywallet.navigation.route.MainRoute
import com.cj.bunnywallet.reducer.Reducer
import com.cj.bunnywallet.reducer.ReducerImp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompletedViewModel @Inject constructor(
    appNavigator: AppNavigator,
    private val dataStore: BunnyPreferencesDataStore,
) : ViewModel(), AppNavigator by appNavigator,
    Reducer<CompletedState> by ReducerImp(CompletedState()) {

    fun handleEvent(e: CompletedEvent) {
        when (e) {
            is CompletedEvent.HandleDialog -> uiState = uiState.copy(showDialog = e.show)

            is CompletedEvent.SaveHint -> {
                if (e.hint.isNotBlank()) {
                    viewModelScope.launch { dataStore.putString(KEY_HINT, e.hint) }
                }
                uiState = uiState.copy(showDialog = false)
            }

            CompletedEvent.Done -> {
                val destination = NavEvent.NavTo(
                    route = MainRoute.Home.route,
                    navOptions = NavOptions.Builder().setPopUpTo(
                        route = CreateWalletRoute.Completed.route,
                        inclusive = true,
                    ).build()
                )
                navigateTo(destination)
            }
        }
    }
}