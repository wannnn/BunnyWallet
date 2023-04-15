package com.cj.bunnywallet.model.token

import kotlinx.serialization.Serializable

@Serializable
data class TokenMetadataParams(
    val id: Int = 1,
    val jsonrpc: String = "2.0",
    val method: String = "alchemy_getTokenMetadata",
    val params: List<String> = listOf()
)
