package com.adewan.listmaker.models

import kotlinx.serialization.Serializable

@Serializable
data class Authentication(val accessToken: String, val expiresIn: Long)