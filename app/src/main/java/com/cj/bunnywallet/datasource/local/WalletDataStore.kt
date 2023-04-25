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
            Timber.d(message = "Create wallet failed: ${e.message}")
        }
    }

    suspend fun deleteWallet(walletId: String) {
        try {
            dataStore.updateData {
                it.copy {
                    wallets.remove(walletId)
                    if (currentWallet == walletId) {
                        val firstWallet = wallets.values.firstOrNull()
                        currentWallet = firstWallet?.id.orEmpty()
                        currentAccount = firstWallet?.accountsMap?.keys?.firstOrNull().orEmpty()
                    }
                }
            }
        } catch (e: IOException) {
            Timber.d(message = "Delete wallet failed: ${e.message}")
        }
    }

    suspend fun createAccount(walletId: String, acc: Account) {
        try {
            dataStore.updateData {
                it.copy {
                    val w = wallets[walletId] ?: throw Throwable("Wallet not found")
                    val newWallet = w.copy { accounts.put(acc.address, acc) }
                    wallets.put(walletId, newWallet)
                    currentWallet = walletId
                    currentAccount = acc.address
                }
            }
        } catch (e: IOException) {
            Timber.d(message = "Create account failed: ${e.message}")
        }
    }

    suspend fun updateCurrentAccount(walletId: String, address: String) {
        try {
            dataStore.updateData {
                it.copy {
                    currentWallet = walletId
                    currentAccount = address
                }
            }
        } catch (e: IOException) {
            Timber.d(message = "Update current account failed: ${e.message}")
        }
    }

    suspend fun editWalletName(walletId: String, newName: String) {
        try {
            dataStore.updateData {
                it.copy {
                    val w = wallets[walletId] ?: throw Throwable("Wallet not found")
                    val newWallet = w.copy { name = newName }
                    wallets.put(walletId, newWallet)
                }
            }
        } catch (e: IOException) {
            Timber.d(message = "Edit wallet name failed: ${e.message}")
        }
    }

    suspend fun editAccountName(walletId: String, address: String, newName: String) {
        try {
            dataStore.updateData {
                it.copy {
                    val w = wallets[walletId] ?: throw Throwable("wallet not found")
                    val acc = w.accountsMap[address] ?: throw Throwable("Account not found")
                    val newAccount = acc.copy { name = newName }
                    val newWallet = w.copy { accounts.put(address, newAccount) }
                    wallets.put(walletId, newWallet)
                }
            }
        } catch (e: IOException) {
            Timber.d(message = "Edit account name failed: ${e.message}")
        }
    }
}