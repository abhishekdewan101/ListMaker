package com.adewan.listmaker.di

import android.content.Context
import com.adewan.listmaker.db.Collection
import com.adewan.listmaker.db.ListMakerDB
import com.adewan.listmaker.repositories.AuthenticationRepository
import com.adewan.listmaker.repositories.GameRepository
import com.adewan.listmaker.repositories.ListRepository
import com.adewan.listmaker.repositories.implemenatation.AuthenticationRepositoryImpl
import com.adewan.listmaker.repositories.implemenatation.GameRepositoryImpl
import com.adewan.listmaker.repositories.implemenatation.ListRepositoryImpl
import com.squareup.sqldelight.EnumColumnAdapter
import com.squareup.sqldelight.android.AndroidSqliteDriver
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
    fun providesDatabase(@ApplicationContext context: Context): ListMakerDB {
        val driver = AndroidSqliteDriver(ListMakerDB.Schema, context, "listmaker.db")
        return ListMakerDB(
            driver = driver, CollectionAdapter = Collection.Adapter(
                EnumColumnAdapter()
            )
        )
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class DataBindingModule {
    @Binds
    abstract fun bindListRepository(listRepositoryImpl: ListRepositoryImpl): ListRepository

    @Binds
    abstract fun bindGameRepository(gameRepositoryImpl: GameRepositoryImpl): GameRepository

    @Binds
    abstract fun bindAuthenticationRepository(authenticationRepositoryImpl: AuthenticationRepositoryImpl): AuthenticationRepository
}