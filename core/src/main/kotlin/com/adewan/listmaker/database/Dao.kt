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

    @Query("SELECT * FROM CoreList where list_type = :type")
    fun getAllForType(type: CoreListType): Flow<List<CoreList>>
}

@Dao
interface GameListEntryDao {
    @Query("SELECT * FROM GameListEntry where parent_list_id = :id")
    fun getAllForList(id: UUID): Flow<List<GameListEntry>>

    @Query("SELECT * FROM GameListEntry where parent_list_id= :id limit 6")
    suspend fun getGamesFromList(id: UUID): List<GameListEntry>

    @Insert fun insert(gameListEntry: GameListEntry)

    @Query("SELECT slug FROM GameListEntry") fun getAllSlugs(): List<String>
}

@Dao
interface MovieListEntryDao {
    @Query("SELECT * FROM MovieListEntry where parent_list_id = :parentListId")
    fun getAllForList(parentListId: UUID): Flow<List<MovieListEntry>>

    @Query("SELECT * FROM MovieListEntry where parent_list_id = :id limit 6")
    suspend fun getMoviesFromList(id: UUID): List<MovieListEntry>

    @Insert fun insert(movieListEntry: MovieListEntry)

    @Query("SELECT id FROM MovieListEntry") fun getAllIds(): List<Int>
}
