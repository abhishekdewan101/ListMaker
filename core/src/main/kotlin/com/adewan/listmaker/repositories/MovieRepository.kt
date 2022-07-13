package com.adewan.listmaker.repositories

import com.adewan.listmaker.database.CoreList
import com.adewan.listmaker.database.MovieListEntry
import com.adewan.listmaker.models.TMDBMovie
import java.util.UUID
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun searchForMoviesAndShows(searchString: String): List<TMDBMovie>
    suspend fun getTrending(): List<TMDBMovie>
    suspend fun getAllEntriesId(parentListId: UUID): Flow<List<MovieListEntry>>
    suspend fun getAllStoredIds(): List<Int>
    suspend fun insert(movie: MovieListEntry)

    // region new

    suspend fun getAllMovieLists(): Flow<List<CoreList>>

    suspend fun getMoviesForList(id: UUID): List<MovieListEntry>

    // endregion
}
