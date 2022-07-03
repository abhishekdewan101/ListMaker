package com.adewan.listmaker.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Authentication(val accessToken: String, val expiresIn: Long)

@Serializable
data class IGDBGame(
    @SerialName("slug") val slug: String,
    @SerialName("name") val name: String,
    @SerialName("cover") val coverImage: IGDBGameImage,
    @SerialName("aggregated_rating") val rating: Double? = null,
    @SerialName("first_release_date") val firstReleaseDate: Long? = null
)

@Serializable
data class IGDBGameImage(@SerialName("image_id") val imageId: String? = null) {
    val qualifiedUrl = "https://images.igdb.com/igdb/image/upload/t_720p/$imageId.png"
}