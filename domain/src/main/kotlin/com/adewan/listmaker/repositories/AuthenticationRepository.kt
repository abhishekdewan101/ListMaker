package com.adewan.listmaker.repositories

interface AuthenticationRepository {
    suspend fun getAuthenticationToken(): String
}