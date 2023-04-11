package com.cj.bunnywallet.model.wallet

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.cj.bunnywallet.proto.wallet.Wallets
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject

class WalletSerializer @Inject constructor() : Serializer<Wallets> {
    override val defaultValue: Wallets = Wallets.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): Wallets {
        try {
            return Wallets.parseFrom(input)
        } catch (e: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", e)
        }
    }

    override suspend fun writeTo(t: Wallets, output: OutputStream) {
        t.writeTo(output)
    }

}