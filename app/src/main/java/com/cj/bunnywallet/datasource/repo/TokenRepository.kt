package com.cj.bunnywallet.datasource.repo

import com.cj.bunnywallet.model.token.TokenMetadata
import com.cj.bunnywallet.model.token.TokenMetadataParams
import com.cj.bunnywallet.network.AlchemyClientManager
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class TokenRepository @Inject constructor(
    private val client: AlchemyClientManager
) {
    fun getTokenMetadata(params: TokenMetadataParams) = flow {
        val response: TokenMetadata = client.client.post {
            setBody(params)
        }.body()
        emit(response.result)
    }.flowOn(Dispatchers.IO)
}