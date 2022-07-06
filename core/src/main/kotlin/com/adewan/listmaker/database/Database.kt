package com.adewan.listmaker.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CoreList::class, GameListEntry::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract fun coreListDao(): CoreListDao
    abstract fun gameListEntryDao(): GameListEntryDao
}