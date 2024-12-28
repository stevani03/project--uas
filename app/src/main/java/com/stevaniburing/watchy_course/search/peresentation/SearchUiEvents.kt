package com.stevaniburing.watchy_course.search.peresentation

import com.stevaniburing.watchy_course.main.domain.model.Media

/**
 * @author Ahmed Guedmioui
 */
sealed class SearchUiEvents {
    data class OnSearchQueryChange (
        val searchQuery: String
    ): SearchUiEvents()

    data class OnSearchItemClick(
        val media: Media
    ): SearchUiEvents()

    object OnPaginate: SearchUiEvents()
}






















