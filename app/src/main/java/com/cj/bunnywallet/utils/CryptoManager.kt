package com.cj.bunnywallet.utils

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties.BLOCK_MODE_CBC
import android.security.keystore.KeyProperties.ENCRYPTION_PADDING_PKCS7
import android.security.keystore.KeyProperties.KEY_ALGORITHM_AES
import android.security.keystore.KeyProperties.PURPOSE_DECRYPT
import android.security.keystore.KeyProperties.PURPOSE_ENCRYPT
import android.util.Log
import com.cj.bunnywallet.extensions.asType
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.spec.IvParameterSpec

/**
 *  Example
 *  val msg = "test message"
 *
 *  // to encrypt msg and return a byteArray
 *  val encryptByteArray = encrypt(msg.encodeToByteArray())
 *
 *  // preserve encryptByteArray to string then store into DataStore
 *  val encryptString = encryptByteArray.toPreservedString
 *
 *  // preserve encryptString to ByteArray then ready to decrypt
 *  val decryptByteArray = encryptString.toPreservedByteArray
 *
 *  // decrypt ByteArray and decodeToString then get our sensitive message("test message")
 *  val decryptString = decrypt(decryptByteArray).decodeToString()
 */

class CryptoManager {

    private val keyStore = KeyStore.getInstance(KEY_STORE_TYPE).apply { load(null) }

    private fun getKey() =
        keyStore.getEntry(ALIAS, null)
            .asType<KeyStore.SecretKeyEntry>()
            ?.secretKey
            ?: createKey()

    private fun createKey() =
        KeyGenerator.getInstance(KEY_ALGORITHM_AES)
            .apply {
                init(
                    KeyGenParameterSpec.Builder(ALIAS, PURPOSE_ENCRYPT or PURPOSE_DECRYPT)
                        .setBlockModes(BLOCK_MODE_CBC)
                        .setEncryptionPaddings(ENCRYPTION_PADDING_PKCS7)
                        .setUserAuthenticationRequired(false)
                        .setRandomizedEncryptionRequired(true)
                        .build()
                )
            }
            .generateKey()

    fun encrypt(ba: ByteArray): ByteArray? =
        runCatching {
            Cipher.getInstance(TRANSFORMATION)
                .apply { init(Cipher.ENCRYPT_MODE, getKey()) }
                .run { iv + doFinal(ba) }
        }
            .onFailure { Log.d("CryptoManager", "Encrypt fail: $it") }
            .getOrNull()

    fun decrypt(ba: ByteArray): ByteArray? =
        runCatching {
            val iv = ba.copyOfRange(0, IV_BLOCK_SIZE)
            val decryptData = ba.copyOfRange(IV_BLOCK_SIZE, ba.size)
            Cipher.getInstance(TRANSFORMATION)
                .apply { init(Cipher.DECRYPT_MODE, getKey(), IvParameterSpec(iv)) }
                .run { doFinal(decryptData) }
        }
            .onFailure { Log.d("CryptoManager", "Decrypt fail: $it") }
            .getOrNull()

    private companion object {
        const val KEY_STORE_TYPE = "AndroidKeyStore"
        const val ALIAS = "CryptoBunny"
        const val TRANSFORMATION =
            "${KEY_ALGORITHM_AES}/${BLOCK_MODE_CBC}/${ENCRYPTION_PADDING_PKCS7}"

        const val IV_BLOCK_SIZE = 16
    }

}