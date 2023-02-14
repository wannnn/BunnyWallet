package com.cj.bunnywallet.navigation

import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

interface AppNavigator {

    val destinationFlow: SharedFlow<NavEvent>

    fun setCurrentEntry(entry: NavBackStackEntry)

    fun navigateTo(navEvent: NavEvent)
}

@Singleton
class AppNavigatorImpl @Inject constructor() : AppNavigator {
    private val _destinationFlow = MutableSharedFlow<NavEvent>(
        extraBufferCapacity = BUFFER_CAPACITY,
        onBufferOverflow = BufferOverflow.SUSPEND,
    )
    override val destinationFlow = _destinationFlow.asSharedFlow()

    private var currentEntry: NavBackStackEntry? = null

    override fun setCurrentEntry(entry: NavBackStackEntry) {
        if (currentEntry == entry) return
        currentEntry = entry
        println("currentEntry: $currentEntry")
    }

    override fun navigateTo(navEvent: NavEvent) {
        if (currentEntry?.lifecycle?.currentState == Lifecycle.State.RESUMED) {
            val isSuccessNav = _destinationFlow.tryEmit(navEvent)
            println("Is success navigate: $isSuccessNav")
        }
    }

    private companion object {
        const val BUFFER_CAPACITY = 1
    }
}