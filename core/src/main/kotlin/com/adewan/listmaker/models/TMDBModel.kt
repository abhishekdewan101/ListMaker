package com.adewan.listmaker.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable data class TMDBMovieList(@SerialName("results") val data: List<TMDBMovie>)

@Serializable
data class TMDBMovie(
    @SerialName("original_title") val title: String? = null,
    @SerialName("original_name") val name: String? = null,
    @SerialName("poster_path") val posterUrl: String? = null,
    @SerialName("backdrop_path") val backdropUrl: String? = null,
    @SerialName("id") val id: Int,
    @SerialName("overview") val summary: String
) {
    val qualifiedPosterUrl = "https://image.tmdb.org/t/p/w500/$posterUrl"
    val qualifiedBackdropUrl = "https://image.tmdb.org/t/p/w500/$backdropUrl"
}
