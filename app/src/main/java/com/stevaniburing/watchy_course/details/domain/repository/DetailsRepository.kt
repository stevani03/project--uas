package com.stevaniburing.watchy_course.details.domain.repository

import com.stevaniburing.watchy_course.main.domain.model.Media
import com.stevaniburing.watchy_course.util.Resource
import kotlinx.coroutines.flow.Flow

/**
 * @author Ahmed Guedmioui
 */
interface DetailsRepository {
    suspend fun getDetails(
        id: Int,
        isRefreshing: Boolean
    ): Flow<Resource<Media>>
}
















