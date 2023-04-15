package com.cj.bunnywallet.model.token

import kotlinx.serialization.Serializable

@Serializable
data class TokenMetadata(
    val jsonrpc: String = "",
    val id: Int = 0,
    val result: TokenMetadataResult = TokenMetadataResult()
) {
    @Serializable
    data class TokenMetadataResult(
        val decimals: Int? = 0,
        val logo: String? = "",
        val name: String? = "",
        val symbol: String? = "",
    )
}
