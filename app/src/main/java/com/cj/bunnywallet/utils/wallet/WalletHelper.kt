package com.cj.bunnywallet.utils.wallet

import com.cj.bunnywallet.utils.Web3jManager
import org.web3j.crypto.Bip32ECKeyPair
import org.web3j.crypto.Credentials
import org.web3j.crypto.MnemonicUtils
import timber.log.Timber
import java.security.SecureRandom

interface WalletHelper {
    fun generateMnemonicList(): List<String>

    fun mnemonicList2String(mnemonic: List<String>): String

    fun mnemonicString2List(mnemonic: String): List<String>

    fun deriveChild(mnemonic: String, childNumber: Int)
}

class WalletHelperImpl : WalletHelper {
    override fun generateMnemonicList(): List<String> = ByteArray(BYTE_SIZE).let {
        SecureRandom().nextBytes(it)
        MnemonicUtils.generateMnemonic(it).split(" ")
    }

    override fun mnemonicList2String(mnemonic: List<String>): String {
        return mnemonic.joinToString(separator = " ")
    }

    override fun mnemonicString2List(mnemonic: String): List<String> {
        return mnemonic.split(" ")
    }

    /**
     *  Mnemonic Code Converter
     *  https://iancoleman.io/bip39/#english
     */
    override fun deriveChild(mnemonic: String, childNumber: Int) {
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
        Credentials.create(childKeypair).let { c ->
            Timber.d(message = "credentials address: ${c.address}")
        }
    }

    private companion object {
        const val BYTE_SIZE = 16
        const val BIP44 = 44
        const val COIN_ETH = 60
    }
}