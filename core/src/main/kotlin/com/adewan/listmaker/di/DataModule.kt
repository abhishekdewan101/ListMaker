package com.adewan.listmaker.di

import android.content.Context
import androidx.room.Room
import com.adewan.listmaker.database.Database
import com.adewan.listmaker.repositories.AuthenticationRepository
import com.adewan.listmaker.repositories.CoreListRepository
import com.adewan.listmaker.repositories.GameRepository
import com.adewan.listmaker.repositories.implemenatation.AuthenticationRepositoryImpl
import com.adewan.listmaker.repositories.implemenatation.CoreListRepositoryImpl
import com.adewan.listmaker.repositories.implemenatation.GameRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext context: Context): Database {
        return Room.databaseBuilder(context, Database::class.java, "database-name").build()
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class DataBindingModule {
    @Binds
    abstract fun bindListRepository(listRepositoryImpl: CoreListRepositoryImpl): CoreListRepository

    @Binds abstract fun bindGameRepository(gameRepositoryImpl: GameRepositoryImpl): GameRepository

    @Binds
    abstract fun bindAuthenticationRepository(
        authenticationRepositoryImpl: AuthenticationRepositoryImpl
    ): AuthenticationRepository
}
