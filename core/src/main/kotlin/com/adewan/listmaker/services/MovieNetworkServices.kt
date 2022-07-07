package com.adewan.listmaker.services

import com.adewan.listmaker.data.BuildConfig
import com.adewan.listmaker.models.TMDBMovie
import com.adewan.listmaker.models.TMDBMovieList
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.http.takeFrom
import io.ktor.serialization.kotlinx.json.json
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.serialization.json.Json

@Singleton
class MovieNetworkServices @Inject constructor() {
    private val client =
        HttpClient(CIO) {
            install(ContentNegotiation) {
                json(
                    Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                    }
                )
            }
            install(Logging) {
                logger =
                    object : Logger {
                        override fun log(message: String) {
                            if (BuildConfig.DEBUG) println(message)
                        }
                    }
                level = if (BuildConfig.DEBUG) LogLevel.ALL else LogLevel.NONE
            }
        }

    suspend fun searchForMoviesAndShows(
        tmdbToken: String = BuildConfig.TMDBToken,
        searchString: String
    ): List<TMDBMovie> {
        val results = mutableListOf<TMDBMovie>()

        val movies =
            client
                .get {
                    url {
                        takeFrom(
                            "https://api.themoviedb.org/3/search/movie?api_key=$tmdbToken&language=en-US&page=1&include_adult=false&query=$searchString"
                        )
                    }
                }
                .body<TMDBMovieList>()

        val tvShows =
            client
                .get {
                    url {
                        takeFrom(
                            "https://api.themoviedb.org/3/search/tv?api_key=$tmdbToken&language=en-US&page=1&include_adult=false&query=$searchString"
                        )
                    }
                }
                .body<TMDBMovieList>()

        results.addAll(movies.data)
        results.addAll(tvShows.data)
        return results
    }

    suspend fun getTrending(
        tmdbToken: String = BuildConfig.TMDBToken,
    ): TMDBMovieList {
        return client
            .get {
                url { takeFrom("https://api.themoviedb.org/3/trending/all/day?api_key=$tmdbToken") }
            }
            .body()
    }
}
