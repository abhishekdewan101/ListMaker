package com.adewan.listmaker.repositories.implemenatation

import com.adewan.listmaker.models.TMDBMovie
import com.adewan.listmaker.repositories.MovieListEntryRepository
import com.adewan.listmaker.services.MovieNetworkServices
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieListEntryRepositoryImpl
@Inject
constructor(private val movieNetworkServices: MovieNetworkServices) : MovieListEntryRepository {
    override suspend fun searchForMoviesAndShows(searchString: String): List<TMDBMovie> {
        return movieNetworkServices.searchForMoviesAndShows(searchString = searchString)
    }
}
