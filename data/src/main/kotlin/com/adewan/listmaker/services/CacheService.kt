package com.adewan.listmaker.services

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class CacheService @Inject constructor(@ApplicationContext context: Context) {
    private val sharedPreferences =
        context.getSharedPreferences("ListMakerSharedPrefs", Context.MODE_PRIVATE)

    fun getStringForKey(key: String): String? {
        return sharedPreferences.getString(key, null)
    }

    fun setStringForKey(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }
}