package com.adewan.listmaker.repositories

import com.adewan.listmaker.db.Game
import java.util.UUID
import kotlinx.coroutines.flow.Flow

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
}