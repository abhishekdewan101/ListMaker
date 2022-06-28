package com.adewan.listmaker.services

import com.adewan.listmaker.models.Authentication
import retrofit2.http.GET

interface AuthenticationApi {
    @GET("default/authentication")
    suspend fun getAuthenticationDataForUser(): Authentication
}