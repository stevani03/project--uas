package com.stevaniburing.watchy_course.main.data.remote.dto

data class MediaListDto(
    val page: Int? = null,
    val results: List<MediaDto>? = null
)