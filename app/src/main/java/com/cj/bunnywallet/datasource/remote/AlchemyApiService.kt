package com.cj.bunnywallet.datasource.remote

import com.cj.bunnywallet.model.token.TokenMetadata
import com.cj.bunnywallet.model.token.TokenMetadataParams

interface AlchemyApiService {

    suspend fun getTokenMetadata(params: TokenMetadataParams): TokenMetadata
}