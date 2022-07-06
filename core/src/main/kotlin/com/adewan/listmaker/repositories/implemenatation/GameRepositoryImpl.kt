package com.adewan.listmaker.repositories.implemenatation

import com.adewan.listmaker.database.Database
import com.adewan.listmaker.database.GameListEntry
import com.adewan.listmaker.models.IGDBGame
import com.adewan.listmaker.repositories.AuthenticationRepository
import com.adewan.listmaker.repositories.GameRepository
import com.adewan.listmaker.services.NetworkServices
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import java.util.UUID
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor(
    private val database: Database,
    private val networkServices: NetworkServices,
    private val authenticationRepository: AuthenticationRepository
) : GameRepository {
    override fun addGameIntoList(
        id: UUID,
        name: String,
        posterUrl: String,
        rating: Double?,
        parentList: String,
        releaseDate: Long?,
        summary: String?
    ) {
//        database.gameCollectionEntriesQueries.insertGame(
//            slug = id.toString(),
//            name = name,
//            posterUrl = posterUrl,
//            rating = rating,
//            parentList = parentList,
//            releaseDate = releaseDate,
//            summary = summary
//        )
    }

    override fun getAllGamesForId(parentListId: String): Flow<List<GameListEntry>> {
        return emptyFlow()
//        return database.gameCollectionEntriesQueries
//            .getGamesForList(parentList = parentListId)
//            .asFlow()
//            .mapToList()
    }

    override suspend fun getLatestGames(): List<IGDBGame> {
        return networkServices.getLatestGames(
            authorization = authenticationRepository.authenticationState.value!!.accessToken,
            bodyQuery = "f slug,name,cover.id,cover.image_id,aggregated_rating, first_release_date, summary;" +
                    "w hypes > 0 & first_release_date >= ${System.currentTimeMillis() / 1000L} & category = 0;\n" +
                    "s hypes desc;\n" +
                    "l 75;"
        )
    }

    override suspend fun searchForGame(game: String): List<IGDBGame> {
        return networkServices.searchForGame(
            authorization = authenticationRepository.authenticationState.value!!.accessToken,
            gameQuery = "f slug,name,cover.id,cover.image_id,aggregated_rating, first_release_date, summary;w category = 0; search \"$game\"; l 100;"
        )
    }
}