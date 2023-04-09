package com.cj.bunnywallet.extensions

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart

fun <T> Flow<T>.onLoading(action: (Boolean) -> Unit): Flow<T> =
    onStart { action.invoke(true) }.onCompletion { action.invoke(false) }