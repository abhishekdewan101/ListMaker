package com.adewan.listmaker.repositories.implemenatation

import com.adewan.listmaker.database.Database
import com.adewan.listmaker.database.MovieListEntry
import com.adewan.listmaker.models.TMDBMovie
import com.adewan.listmaker.repositories.MovieListEntryRepository
import com.adewan.listmaker.services.MovieNetworkServices
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow

@Singleton
class MovieListEntryRepositoryImpl
@Inject
constructor(
    private val movieNetworkServices: MovieNetworkServices,
    private val database: Database
) : MovieListEntryRepository {
    override suspend fun searchForMoviesAndShows(searchString: String): List<TMDBMovie> {
        return movieNetworkServices.searchForMoviesAndShows(searchString = searchString)
    }

    override suspend fun getTrending(): List<TMDBMovie> {
        return movieNetworkServices.getTrending().data
    }

    override suspend fun getAllEntriesId(parentListId: UUID): Flow<List<MovieListEntry>> {
        return database.movieListEntryDao().getAllForList(parentListId = parentListId)
    }
}
