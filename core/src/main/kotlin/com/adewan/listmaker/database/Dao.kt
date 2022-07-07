package com.adewan.listmaker.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import java.util.UUID
import kotlinx.coroutines.flow.Flow

@Dao
interface CoreListDao {
    @Insert fun insert(list: CoreList)

    @Query("SELECT * FROM CoreList") fun getAll(): Flow<List<CoreList>>

    @Query("SELECT * FROM CoreList where id = :id") fun getCoreListForId(id: UUID): CoreList?
}

@Dao
interface GameListEntryDao {
    @Query("SELECT * FROM GameListEntry where parent_list_id = :parentListId")
    fun getAllForList(parentListId: UUID): Flow<List<GameListEntry>>

    @Insert fun insert(gameListEntry: GameListEntry)

    @Query("SELECT slug FROM GameListEntry") fun getAllSlugs(): List<String>
}
