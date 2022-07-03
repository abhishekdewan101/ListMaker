package com.adewan.listmaker.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Authentication(val accessToken: String, val expiresIn: Long)

@Serializable
data class ListMakerGame(
    @SerialName("slug") val slug: String,
    @SerialName("name") val name: String,
    @SerialName("cover") val coverImage: ListMakerGameImage? = null,
    @SerialName("aggregated_rating") val rating: Double? = null,
)

@Serializable
data class ListMakerGameImage(
    @SerialName("id") val id: Int? = null,
    @SerialName("image_id") val imageId: String? = null
) {
    val qualifiedUrl = "https://images.igdb.com/igdb/image/upload/t_720p/$imageId.png"
}