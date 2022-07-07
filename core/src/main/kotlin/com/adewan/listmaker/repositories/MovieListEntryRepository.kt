package com.adewan.listmaker.repositories

import com.adewan.listmaker.models.TMDBMovie

interface MovieListEntryRepository {
    suspend fun searchForMoviesAndShows(searchString: String): List<TMDBMovie>
}
