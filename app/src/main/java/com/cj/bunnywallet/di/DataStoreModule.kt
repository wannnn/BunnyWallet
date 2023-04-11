package com.cj.bunnywallet.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.cj.bunnywallet.model.wallet.WalletSerializer
import com.cj.bunnywallet.proto.wallet.Wallets
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    private const val PREFERENCE_DATA_STORE = "PREFERENCE_DATA_STORE"
    private const val DS_WALLET_TEST = "wallets.pb"

    @Singleton
    @Provides
    fun providePreferencesDataStore(
        @ApplicationContext appContext: Context,
    ): DataStore<Preferences> =
        PreferenceDataStoreFactory.create(
            produceFile = { appContext.preferencesDataStoreFile(PREFERENCE_DATA_STORE) }
        )

    @Singleton
    @Provides
    fun providesWalletDataStore(
        @ApplicationContext context: Context,
        walletSerializer: WalletSerializer,
    ): DataStore<Wallets> =
        DataStoreFactory.create(
            serializer = walletSerializer,
            produceFile = { context.dataStoreFile(DS_WALLET_TEST) },
        )
}