package com.cj.bunnywallet.app

import android.app.Application
import com.cj.bunnywallet.BuildConfig
import com.cj.bunnywallet.utils.CustomDebugTree
import com.cj.bunnywallet.utils.Web3jManager
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidApp
class App : Application() {

    @Inject
    lateinit var web3jManager: Web3jManager

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(CustomDebugTree())

        web3jManager.initWeb3j()
    }
}
