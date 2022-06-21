package com.adewan.listmaker.repositories

import com.adewan.listmaker.services.AuthenticationService
import com.adewan.listmaker.services.CacheService
import javax.inject.Inject

class LocalAuthenticationRepository @Inject constructor(
    private val cacheService: CacheService,
    private val authenticationService: AuthenticationService
) : AuthenticationRepository {
    private val authenticationKey = "LOCAL_AUTH_KEY"
    override suspend fun getAuthenticationToken(): String {
        return ""
    }
}