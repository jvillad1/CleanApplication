package com.test.clean.domain.model

data class Post(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String,
    var read: Boolean = false,
    var favorite: Boolean = false
)
