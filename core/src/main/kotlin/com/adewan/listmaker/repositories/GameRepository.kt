package com.adewan.listmaker.repositories

import com.adewan.listmaker.database.GameListEntry
import com.adewan.listmaker.models.IGDBGame
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface GameRepository {
    fun addGameIntoList(
        id: UUID,
        name: String,
        posterUrl: String,
        rating: Double?,
        parentList: String,
        releaseDate: Long?,
        summary: String?
    )

    fun getAllGamesForId(parentListId: String): Flow<List<GameListEntry>>

    suspend fun getLatestGames(): List<IGDBGame>

    suspend fun searchForGame(game: String): List<IGDBGame>
}