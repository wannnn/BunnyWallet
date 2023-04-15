package com.cj.bunnywallet.helper

import com.cj.bunnywallet.proto.wallet.Account
import com.cj.bunnywallet.proto.wallet.Wallet
import com.cj.bunnywallet.proto.wallet.account
import com.cj.bunnywallet.proto.wallet.wallet
import com.cj.bunnywallet.utils.CryptoManager
import org.web3j.crypto.Bip32ECKeyPair
import org.web3j.crypto.Bip44WalletUtils
import org.web3j.crypto.Credentials
import org.web3j.crypto.MnemonicUtils
import timber.log.Timber
import java.security.SecureRandom

interface WalletHelper {
    fun generateMnemonicList(): List<String>

    fun mnemonicList2String(mnemonicList: List<String>): String

    fun mnemonicString2List(mnemonic: String): List<String>

    fun validateMnemonic(mnemonic: String): Boolean

    fun loadCredentials(mnemonic: String): Credentials

    fun createWallet(mnemonic: String): Wallet?

    fun deriveAccount(mnemonic: String, childNumber: Int): Account
}

class WalletHelperImpl(private val cryptoManager: CryptoManager) : WalletHelper {
    override fun generateMnemonicList(): List<String> = ByteArray(BYTE_SIZE).let {
        SecureRandom().nextBytes(it)
        MnemonicUtils.generateMnemonic(it).split(" ")
    }

    override fun mnemonicList2String(mnemonicList: List<String>): String {
        return mnemonicList.joinToString(separator = " ")
    }

    override fun mnemonicString2List(mnemonic: String): List<String> {
        return mnemonic.split(" ")
    }

    override fun validateMnemonic(mnemonic: String): Boolean {
        return MnemonicUtils.validateMnemonic(mnemonic)
    }

    override fun loadCredentials(mnemonic: String): Credentials =
        Bip44WalletUtils.loadBip44Credentials("", mnemonic)

    override fun createWallet(mnemonic: String): Wallet? = runCatching {
        val credentials = loadCredentials(mnemonic)
        val encryptedMnemonic = cryptoManager.encrypt(mnemonic)
            ?: throw ArithmeticException("Mnemonic encrypted fail")

        wallet {
            id = encryptedMnemonic
            name = FIRST_WALLET
            accounts.put(
                credentials.address,
                account {
                    address = credentials.address
                    name = FIRST_ACCOUNT
                },
            )
        }
    }
        .onFailure { Timber.d(message = "Create wallet fail: ${it.message}") }
        .getOrNull()

    /**
     *  Mnemonic Code Converter
     *  https://iancoleman.io/bip39/#english
     */
    override fun deriveAccount(mnemonic: String, childNumber: Int): Account {
        val seed = MnemonicUtils.generateSeed(mnemonic, "")
        val masterKeypair = Bip32ECKeyPair.generateKeyPair(seed)
        val path = intArrayOf(
            BIP44 or Bip32ECKeyPair.HARDENED_BIT,
            COIN_ETH or Bip32ECKeyPair.HARDENED_BIT,
            0 or Bip32ECKeyPair.HARDENED_BIT,
            0,
            childNumber,
        )
        val childKeypair = Bip32ECKeyPair.deriveKeyPair(masterKeypair, path)
        val credentials = Credentials.create(childKeypair)

        return account {
            address = credentials.address
            name = "$ACCOUNT ${childNumber + 1}"
        }
    }

    private companion object {
        const val FIRST_WALLET = "Wallet 1"
        const val ACCOUNT = "Account"
        const val FIRST_ACCOUNT = "$ACCOUNT 1"

        const val BYTE_SIZE = 16
        const val BIP44 = 44
        const val COIN_ETH = 60
    }
}