@file:OptIn(ExperimentalSerializationApi::class)

package com.adewan.listmaker.services

import com.adewan.listmaker.data.BuildConfig
import com.adewan.listmaker.models.Authentication
import com.adewan.listmaker.models.IGDBGame
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.takeFrom
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkServices @Inject constructor() {

    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    if (BuildConfig.DEBUG) println(message)
                }
            }
            level = if (BuildConfig.DEBUG) LogLevel.ALL else LogLevel.NONE
        }
    }

    suspend fun getAuthenticationDataForUser(): Authentication = client.get {
        url {
            takeFrom("https://px058nbguc.execute-api.us-east-1.amazonaws.com/default/authentication")
        }
    }.body()

    suspend fun getLatestGames(
        clientId: String = BuildConfig.ClientId,
        authorization: String,
        bodyQuery: String
    ): List<IGDBGame> {
        return client.post {
            headers {
                append("Client-ID", clientId)
                append("Authorization", "Bearer $authorization")
            }
            url {
                takeFrom("https://api.igdb.com/v4/games")
            }
            setBody(bodyQuery)
        }.body()
    }

    suspend fun searchForGame(
        clientId: String = BuildConfig.ClientId,
        authorization: String,
        gameQuery: String
    ): List<IGDBGame> {
        return client.post {
            headers {
                append("Client-ID", clientId)
                append("Authorization", "Bearer $authorization")
            }
            url {
                takeFrom("https://api.igdb.com/v4/games")
            }
            setBody(gameQuery)
        }.body()
    }
}