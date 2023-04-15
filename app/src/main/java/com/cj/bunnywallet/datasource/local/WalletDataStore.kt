package com.cj.bunnywallet.datasource.local

import androidx.datastore.core.DataStore
import com.cj.bunnywallet.proto.wallet.Account
import com.cj.bunnywallet.proto.wallet.Wallet
import com.cj.bunnywallet.proto.wallet.Wallets
import com.cj.bunnywallet.proto.wallet.copy
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.take
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WalletDataStore @Inject constructor(private val dataStore: DataStore<Wallets>) {

    val wallets: Flow<Wallets> = dataStore.data
        .catch { Timber.d(message = "Get wallets from datastore failed") }

    val currentAccount: Flow<Account> = dataStore.data
        .map {
            it.getWalletsOrDefault(it.currentWallet, Wallet.getDefaultInstance())
                .getAccountsOrDefault(it.currentAccount, Account.getDefaultInstance())
        }
        .catch { Timber.d(message = "Get Account from datastore failed") }

    val hasWallet: Flow<Boolean> = dataStore.data
        .map { it.currentWallet.isNotBlank() }
        .take(1)

    suspend fun createWallet(wallet: Wallet) {
        try {
            dataStore.updateData {
                it.copy {
                    wallets.put(wallet.id, wallet)
                    currentWallet = wallet.id
                    currentAccount = wallet.accountsMap.keys.firstOrNull().orEmpty()
                }
            }
        } catch (e: IOException) {
            Timber.d(message = "Failed to update wallet preferences: ${e.message}")
        }
    }

    suspend fun createAccount(walletId: String, acc: Account) {
        try {
            dataStore.updateData {
                it.copy {
                    val w = wallets[walletId] ?: throw Throwable("Not find wallet")
                    val newWallet = w.copy {
                        accounts.put(acc.address, acc)
                    }
                    wallets.put(walletId, newWallet)
                    currentWallet = walletId
                    currentAccount = acc.address
                }
            }
        } catch (e: IOException) {
            Timber.d(message = "Failed to update wallet preferences: ${e.message}")
        }
    }

    suspend fun updateCurrentAccount(address: String) {
        try {
            dataStore.updateData {
                it.copy { currentAccount = address }
            }
        } catch (e: IOException) {
            Timber.d(message = "Failed to update wallet preferences: ${e.message}")
        }
    }
}