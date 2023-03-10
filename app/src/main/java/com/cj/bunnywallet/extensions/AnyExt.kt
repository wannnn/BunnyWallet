package com.cj.bunnywallet.extensions

inline fun <reified T> Any?.asType(): T? = this as? T?