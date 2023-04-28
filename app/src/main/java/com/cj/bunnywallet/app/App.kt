package com.cj.bunnywallet.app

import android.app.Application
import com.cj.bunnywallet.BuildConfig
import com.cj.bunnywallet.datasource.local.BunnyPreferencesDataStore
import com.cj.bunnywallet.helper.ApiHostHelper
import com.cj.bunnywallet.utils.CustomDebugTree
import com.cj.bunnywallet.utils.Web3jManager
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(CustomDebugTree())
    }
}
