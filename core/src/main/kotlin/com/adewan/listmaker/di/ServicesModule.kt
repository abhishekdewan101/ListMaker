package com.adewan.listmaker.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServicesModule {
    @Provides
    @Singleton
    fun providesNetworkJson(): Json {
        return Json {
            ignoreUnknownKeys = true
            isLenient = true
        }
    }
}