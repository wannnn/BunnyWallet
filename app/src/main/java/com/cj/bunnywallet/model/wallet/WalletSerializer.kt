package com.cj.bunnywallet.model.wallet

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.cj.bunnywallet.proto.wallet.Wallet
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject

class WalletSerializer @Inject constructor() : Serializer<Wallet> {
    override val defaultValue: Wallet = Wallet.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): Wallet {
        try {
            return Wallet.parseFrom(input)
        } catch (e: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", e)
        }
    }

    override suspend fun writeTo(t: Wallet, output: OutputStream) {
        t.writeTo(output)
    }

}