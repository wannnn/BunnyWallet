package com.cj.bunnywallet.app

import android.app.Application
import com.cj.bunnywallet.BuildConfig
import com.cj.bunnywallet.utils.CustomDebugTree
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(CustomDebugTree())
    }
}
