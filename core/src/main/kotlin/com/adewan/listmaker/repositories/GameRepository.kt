package com.adewan.listmaker.repositories

import com.adewan.listmaker.db.Game
import com.adewan.listmaker.models.ListMakerGame
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface GameRepository {
    fun addGameIntoList(
        id: UUID,
        name: String,
        posterUrl: String,
        rating: Int,
        parentList: String,
        releaseDate: Long
    )

    fun getAllGamesForId(parentListId: String): Flow<List<Game>>

    suspend fun getLatestGames(): List<ListMakerGame>

    suspend fun searchForGame(game: String): List<ListMakerGame>
}