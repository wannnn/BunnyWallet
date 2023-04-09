package com.cj.bunnywallet.datasource

import androidx.datastore.core.DataStore
import com.cj.bunnywallet.proto.wallet.Wallet
import com.cj.bunnywallet.proto.wallet.copy
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WalletDataStore @Inject constructor(private val dataStore: DataStore<Wallet>) {

    val wallet: Flow<Wallet> = dataStore.data

    val walletName: Flow<String> = dataStore.data.map { w -> w.name }

    suspend fun updateWallet(newWallet: Wallet) {
        try {
            dataStore.updateData { newWallet }
        } catch (e: IOException) {
            Timber.d(message = "Failed to update wallet preferences: ${e.message}")
        }
    }

    suspend fun updateWalletName(newName: String) {
        try {
            dataStore.updateData {
                it.copy { name = newName }
            }
        } catch (e: IOException) {
            Timber.d(message = "Failed to update wallet preferences: ${e.message}")
        }
    }

}