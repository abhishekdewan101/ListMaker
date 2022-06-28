package com.adewan.listmaker.repositories.implemenatation

import com.adewan.listmaker.models.Authentication
import com.adewan.listmaker.repositories.AuthenticationRepository
import com.adewan.listmaker.services.CacheServices
import com.adewan.listmaker.services.NetworkServices
import java.util.Calendar
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@Singleton
class AuthenticationRepositoryImpl @Inject constructor(
    private val networkServices: NetworkServices,
    private val cacheServices: CacheServices
) : AuthenticationRepository {

    private val _authenticationState = MutableStateFlow<Authentication?>(null)
    override val authenticationState = _authenticationState.asStateFlow()

    override suspend fun authenticateUser() {
        val savedAuthentication = cacheServices.getAuthenticationData()
        if (savedAuthentication != null && verifyAuthenticationValidity(savedAuthentication)) {
            _authenticationState.value = savedAuthentication
        } else {
            println("Authentication wasn't saved so getting from network")
            val authentication = networkServices.getAuthenticationDataForUser()
            val now = Calendar.getInstance().timeInMillis
            val authenticationCopy = authentication.copy(expiresIn = authentication.expiresIn + now)
            cacheServices.saveAuthenticationData(authentication = authenticationCopy)
            _authenticationState.value = authenticationCopy
        }
    }

    private fun verifyAuthenticationValidity(savedAuthentication: Authentication): Boolean {
        val now = Calendar.getInstance().timeInMillis
        return now < savedAuthentication.expiresIn
    }
}