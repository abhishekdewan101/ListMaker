package com.adewan.listmaker.repositories.implemenatation

import com.adewan.listmaker.db.Game
import com.adewan.listmaker.db.ListMakerDB
import com.adewan.listmaker.repositories.GameRepository
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import java.util.UUID
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GameRepositoryImpl @Inject constructor(private val database: ListMakerDB) : GameRepository {
    override fun addGameIntoList(
        id: UUID,
        name: String,
        posterUrl: String,
        rating: Int,
        parentList: String,
        releaseDate: Long
    ) {
        database.gameQueries.insertGame(
            id = id.toString(),
            name = name,
            posterUrl = posterUrl,
            rating = rating,
            parentList = parentList,
            releaseDate = releaseDate
        )
    }

    override fun getAllGamesForId(parentListId: String): Flow<List<Game>> {
        return database.gameQueries
            .getAllGamesForId(parentList = parentListId)
            .asFlow()
            .mapToList()
    }
}