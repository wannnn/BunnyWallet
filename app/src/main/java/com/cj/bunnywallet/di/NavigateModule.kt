package com.cj.bunnywallet.di

import com.cj.bunnywallet.navigation.AppNavigator
import com.cj.bunnywallet.navigation.AppNavigatorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface NavigateModule {

    @Binds
    fun bindsAppNavigator(appNavigator: AppNavigatorImpl): AppNavigator

}