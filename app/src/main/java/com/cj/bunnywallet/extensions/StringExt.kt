package com.cj.bunnywallet.extensions

private const val PwdMaxLength = 8
fun String.isPasswordValid(): Boolean = isNotBlank() && length >= PwdMaxLength