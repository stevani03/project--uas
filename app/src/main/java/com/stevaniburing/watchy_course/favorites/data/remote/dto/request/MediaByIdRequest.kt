package com.stevaniburing.watchy_course.favorites.data.remote.dto.request

data class MediaByIdRequest(
    val mediaId: Int,
    val email: String
)