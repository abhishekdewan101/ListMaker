@file:OptIn(ExperimentalSerializationApi::class)

package com.adewan.listmaker.services

import com.adewan.listmaker.data.BuildConfig
import com.adewan.listmaker.models.Authentication
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

@Singleton
class NetworkServices @Inject constructor(json: Json) : AuthenticationApi {

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BODY) }
        ).build()

    private val authenticationClient = Retrofit.Builder().baseUrl(BuildConfig.AuthenticationUrl)
        .client(okHttpClient)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()

    private val authenticationApi = authenticationClient.create(AuthenticationApi::class.java)

    override suspend fun getAuthenticationDataForUser(): Authentication {
        return authenticationApi.getAuthenticationDataForUser()
    }
}