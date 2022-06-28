package com.adewan.listmaker.services

import android.content.Context
import com.adewan.listmaker.models.Authentication
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Singleton
class CacheServices @Inject constructor(
    @ApplicationContext context: Context,
    private val json: Json
) {
    private val sharedPreferences =
        context.getSharedPreferences("list_maker_prefs", Context.MODE_PRIVATE)
    private val authenticationKey = "CacheServices.authenticationKey"

    fun saveAuthenticationData(authentication: Authentication) {
        val jsonPayload = json.encodeToString(authentication)
        sharedPreferences.edit().putString(authenticationKey, jsonPayload).apply()
    }

    fun getAuthenticationData(): Authentication? {
        val jsonPayload = sharedPreferences.getString(authenticationKey, null) ?: return null
        return json.decodeFromString(jsonPayload)
    }
}