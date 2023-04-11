package com.cj.bunnywallet.datasource

import androidx.datastore.core.DataStore
import com.cj.bunnywallet.proto.wallet.Wallet
import com.cj.bunnywallet.proto.wallet.Wallets
import com.cj.bunnywallet.proto.wallet.copy
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.take
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WalletDataStore @Inject constructor(private val dataStore: DataStore<Wallets>) {

    val wallets: Flow<Wallets> = dataStore.data

    val selectedWallet: Flow<Wallet> = dataStore.data
        .map { it.walletsMap.getOrDefault(it.selectedWallet, Wallet.getDefaultInstance()) }

    val hasWallet: Flow<Boolean> = dataStore.data
        .map { it.selectedWallet.isNotBlank() }
        .take(1)

    suspend fun createWallet(wallet: Wallet) {
        try {
            dataStore.updateData {
                it.copy {
                    wallets.put(wallet.id, wallet)
                    selectedWallet = wallet.id
                }
            }
        } catch (e: IOException) {
            Timber.d(message = "Failed to update wallet preferences: ${e.message}")
        }
    }
}