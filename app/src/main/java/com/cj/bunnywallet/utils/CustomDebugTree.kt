package com.cj.bunnywallet.utils

import timber.log.Timber

class CustomDebugTree : Timber.DebugTree() {

    override fun createStackElementTag(element: StackTraceElement): String =
        "${element.fileName}:${element.lineNumber}"

}