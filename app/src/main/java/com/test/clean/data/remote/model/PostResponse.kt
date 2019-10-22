package com.test.clean.data.remote.model

import com.squareup.moshi.Json

data class PostResponse(
    @field:Json(name = "userId")
    val userId: Int,
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "title")
    val title: String,
    @field:Json(name = "body")
    val body: String
)
