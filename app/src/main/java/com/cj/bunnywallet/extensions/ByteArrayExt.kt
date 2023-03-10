package com.cj.bunnywallet.extensions

val ByteArray.toPreservedString: String
    get() = String(this, Charsets.ISO_8859_1)