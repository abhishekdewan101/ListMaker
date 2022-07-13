package com.adewan.listmaker.repositories.implemenatation

import com.adewan.listmaker.database.CoreList
import com.adewan.listmaker.database.CoreListType
import com.adewan.listmaker.database.Database
import com.adewan.listmaker.database.MovieListEntry
import com.adewan.listmaker.models.TMDBMovie
import com.adewan.listmaker.repositories.MovieRepository
import com.adewan.listmaker.services.MovieNetworkServices
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow

@Singleton
class MovieRepositoryImpl
@Inject
constructor(
    private val movieNetworkServices: MovieNetworkServices,
    private val database: Database
) : MovieRepository {
    override suspend fun searchForMoviesAndShows(searchString: String): List<TMDBMovie> {
        return movieNetworkServices.searchForMoviesAndShows(searchString = searchString)
    }

    override suspend fun getTrending(): List<TMDBMovie> {
        return movieNetworkServices.getTrending().data
    }

    override suspend fun getAllEntriesId(parentListId: UUID): Flow<List<MovieListEntry>> {
        return database.movieListEntryDao().getAllForList(parentListId = parentListId)
    }

    override suspend fun getAllStoredIds(): List<Int> {
        return database.movieListEntryDao().getAllIds()
    }

    override suspend fun insert(movie: MovieListEntry) {
        database.movieListEntryDao().insert(movie)
    }

    override suspend fun getAllMovieLists(): Flow<List<CoreList>> {
        return database.coreListDao().getAllForType(type = CoreListType.MOVIES)
    }

    override suspend fun getMoviesForList(id: UUID): List<MovieListEntry> {
        return database.movieListEntryDao().getMoviesFromList(id = id)
    }
}
