package com.adewan.listmaker.repositories

import com.adewan.listmaker.database.CoreList
import com.adewan.listmaker.database.GameListEntry
import com.adewan.listmaker.models.IGDBGame
import java.util.UUID
import kotlinx.coroutines.flow.Flow

interface GameRepository {
    fun addListEntry(gameListEntry: GameListEntry)

    fun getAllForId(parentListId: UUID): Flow<List<GameListEntry>>

    fun getAllStoredSlugs(): List<String>

    suspend fun getLatestGames(): List<IGDBGame>

    suspend fun searchForGame(game: String): List<IGDBGame>

    // region new

    suspend fun getAllGameLists(): Flow<List<CoreList>>

    suspend fun getGamesForList(id: UUID): List<GameListEntry>

    // endregion
}
