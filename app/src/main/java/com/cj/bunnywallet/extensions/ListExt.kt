package com.cj.bunnywallet.extensions

inline fun <T> List<T>.indexOfFirstOrNull(predicate: (T) -> Boolean): Int? {
    return this.indexOfFirst(predicate)
        .takeIf { it >= 0 }
}