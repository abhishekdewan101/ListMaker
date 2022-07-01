package com.adewan.listmaker.repositories.implemenatation

import com.adewan.listmaker.db.Game
import com.adewan.listmaker.db.ListMakerDB
import com.adewan.listmaker.models.ListMakerGame
import com.adewan.listmaker.repositories.AuthenticationRepository
import com.adewan.listmaker.repositories.GameRepository
import com.adewan.listmaker.services.NetworkServices
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.flow.Flow
import java.util.UUID
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor(
    private val database: ListMakerDB,
    private val networkServices: NetworkServices,
    private val authenticationRepository: AuthenticationRepository
) : GameRepository {
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

    override suspend fun getLatestGames(): List<ListMakerGame> {
        return networkServices.getLatestGames(
            authorization = authenticationRepository.authenticationState.value!!.accessToken,
            bodyQuery = "f slug,name,cover.id,cover.image_id,aggregated_rating;\n" +
                    "w hypes > 0 & first_release_date >= ${System.currentTimeMillis() / 1000L} & category = 0;\n" +
                    "s hypes desc;\n" +
                    "l 75;"
        )
    }
}