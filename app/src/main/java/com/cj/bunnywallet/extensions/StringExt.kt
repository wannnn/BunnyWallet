package com.cj.bunnywallet.extensions

val String.toPreservedByteArray: ByteArray
    get() = toByteArray(Charsets.ISO_8859_1)