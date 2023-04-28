package com.cj.bunnywallet.network

import com.cj.bunnywallet.helper.ApiHostHelper
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.accept
import io.ktor.client.request.headers
import io.ktor.client.request.request
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.serialization.json.Json
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AlchemyClientManager @Inject constructor(
    scope: CoroutineScope,
    apiHostHelper: ApiHostHelper,
) {

    init {
        apiHostHelper.apiHosts
            .onEach { updateClient(it.alchemyBaseUrl) }
            .catch { Timber.d("Get API hosts failed: ${it.message}") }
            .launchIn(scope)
    }

    private var _client: HttpClient = HttpClient()
    val client = _client

    private fun updateClient(baseUrl: String) {
        _client = HttpClient(CIO) {
            install(ContentNegotiation) {
                json(
                    Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                        encodeDefaults = true
                    },
                )
            }

            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }

            expectSuccess = true

            defaultRequest {
                headers {
                    contentType(ContentType.Application.Json)
                    accept(ContentType.Application.Json)
                }
                url(baseUrl)
            }
        }
    }
}

