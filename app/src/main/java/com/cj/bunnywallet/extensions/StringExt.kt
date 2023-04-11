package com.cj.bunnywallet.extensions

private const val PwdMaxLength = 8
private const val START_INDEX = 6
private const val END_INDEX = 4
private const val REPLACEMENT_DOT = "...."

fun String.isPasswordValid(): Boolean = isNotBlank() && length >= PwdMaxLength

val String.shortAddress: String
    get() = this.replaceRange(
        startIndex = START_INDEX,
        endIndex = length - END_INDEX,
        replacement = REPLACEMENT_DOT,
    )
