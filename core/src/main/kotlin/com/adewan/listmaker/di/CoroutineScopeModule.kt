package com.adewan.listmaker.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object CoroutineScopeModule {
    @Provides
    @Named("io")
    fun provideIoContext(): CoroutineScope {
        return CoroutineScope(Dispatchers.IO)
    }

    @Provides
    @Named("main")
    fun provideMainContext(): CoroutineScope {
        return CoroutineScope(Dispatchers.Main)
    }
}
