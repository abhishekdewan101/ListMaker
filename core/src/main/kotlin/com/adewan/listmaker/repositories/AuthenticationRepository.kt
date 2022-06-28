package com.adewan.listmaker.repositories

import com.adewan.listmaker.models.Authentication
import kotlinx.coroutines.flow.StateFlow

interface AuthenticationRepository {
    val authenticationState: StateFlow<Authentication?>
    suspend fun authenticateUser()
}