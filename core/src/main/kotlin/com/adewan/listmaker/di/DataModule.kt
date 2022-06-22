package com.adewan.listmaker.di

import android.content.Context
import com.adewan.listmaker.db.AppList
import com.adewan.listmaker.db.ListMakerDB
import com.squareup.sqldelight.EnumColumnAdapter
import com.squareup.sqldelight.android.AndroidSqliteDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    fun providesDatabase(@ApplicationContext context: Context): ListMakerDB {
        val driver = AndroidSqliteDriver(ListMakerDB.Schema, context, "listmaker.db")
        return ListMakerDB(
            driver = driver, AppListAdapter = AppList.Adapter(
                EnumColumnAdapter()
            )
        )
    }
}