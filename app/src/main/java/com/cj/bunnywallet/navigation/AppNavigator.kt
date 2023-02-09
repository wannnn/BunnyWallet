package com.cj.bunnywallet.navigation

import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class AppNavigator {
    private val _destinationFlow = MutableSharedFlow<NavEvent>(
        extraBufferCapacity = BUFFER_CAPACITY,
        onBufferOverflow = BufferOverflow.SUSPEND,
    )
    val destinationFlow = _destinationFlow.asSharedFlow()

    fun navigateTo(entry: NavBackStackEntry, navEvent: NavEvent) {
        if (entry.lifecycle.currentState == Lifecycle.State.RESUMED) {
            val isSuccessNav = _destinationFlow.tryEmit(navEvent)
            println("Is success navigate: $isSuccessNav")
        }
    }

    private companion object {
        const val BUFFER_CAPACITY = 1
    }
}