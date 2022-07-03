package com.adewan.listmaker.repositories

import com.adewan.listmaker.db.GameCollectionEntries
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
        releaseDate: Long?
    )

    fun getAllGamesForId(parentListId: String): Flow<List<GameCollectionEntries>>

    suspend fun getLatestGames(): List<IGDBGame>

    suspend fun searchForGame(game: String): List<IGDBGame>
}