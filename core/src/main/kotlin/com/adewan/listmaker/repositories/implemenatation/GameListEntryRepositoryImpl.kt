package com.adewan.listmaker.repositories.implemenatation

import com.adewan.listmaker.database.Database
import com.adewan.listmaker.database.GameListEntry
import com.adewan.listmaker.models.IGDBGame
import com.adewan.listmaker.repositories.AuthenticationRepository
import com.adewan.listmaker.repositories.GameListEntryRepository
import com.adewan.listmaker.services.NetworkServices
import java.util.UUID
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GameListEntryRepositoryImpl
@Inject
constructor(
    private val database: Database,
    private val networkServices: NetworkServices,
    private val authenticationRepository: AuthenticationRepository
) : GameListEntryRepository {
    override fun addListEntry(gameListEntry: GameListEntry) {
        database.gameListEntryDao().insert(gameListEntry)
    }

    override fun getAllForId(parentListId: UUID): Flow<List<GameListEntry>> {
        return database.gameListEntryDao().getAllForList(parentListId)
    }

    override suspend fun getLatestGames(): List<IGDBGame> {
        return networkServices
            .getLatestGames(
                authorization = authenticationRepository.authenticationState.value!!.accessToken,
                bodyQuery =
                    "f slug,name,cover.id,cover.image_id, summary;" +
                        "w hypes > 0 & first_release_date >= ${System.currentTimeMillis() / 1000L} & category = 0;\n" +
                        "s hypes desc;\n" +
                        "l 75;"
            )
            .filter { it.coverImage != null }
    }

    override suspend fun searchForGame(game: String): List<IGDBGame> {
        return networkServices
            .searchForGame(
                authorization = authenticationRepository.authenticationState.value!!.accessToken,
                gameQuery =
                    "f slug,name,cover.id,cover.image_id,aggregated_rating, first_release_date, summary;w category = 0; search \"$game\"; l 100;"
            )
            .filter { it.coverImage != null }
    }
}
