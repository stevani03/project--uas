package com.stevaniburing.watchy_course.search.peresentation

import com.stevaniburing.watchy_course.main.domain.model.Media

/**
 * @author Ahmed Guedmioui
 */
data class SearchState (
    val isLoading: Boolean = false,
    val searchPage: Int = 1,
    val searchQuery: String = "",
    val searchList: List<Media> = emptyList()
)





















