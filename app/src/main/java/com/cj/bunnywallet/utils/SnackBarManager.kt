package com.cj.bunnywallet.utils

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SnackBarManager @Inject constructor() {

    private val _messages = MutableSharedFlow<String>(
        extraBufferCapacity = 3,
        onBufferOverflow = BufferOverflow.SUSPEND,
    )
    val messages = _messages.asSharedFlow()

    fun showMessage(message: String?) {
        if (!message.isNullOrBlank()) { _messages.tryEmit(message) }
    }
}