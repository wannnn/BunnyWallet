package com.cj.bunnywallet.helper

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class StateFlowDelegate<T>(initValue: T) : ReadWriteProperty<Any, T> {

    private val mutableStateFlow = MutableStateFlow(initValue)
    val stateFlow = mutableStateFlow.asStateFlow()

    override fun getValue(thisRef: Any, property: KProperty<*>): T {
        return mutableStateFlow.value
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
        mutableStateFlow.update { value }
    }
}