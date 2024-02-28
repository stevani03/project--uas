package com.ahmed_apps.watchy_course.main.domain.repository

import com.ahmed_apps.watchy_course.main.data.local.MediaEntity
import com.ahmed_apps.watchy_course.main.domain.model.Media
import com.ahmed_apps.watchy_course.util.Resource
import kotlinx.coroutines.flow.Flow

/**
 * @author Ahmed Guedmioui
 */
interface MainRepository {

    suspend fun upsertMediaList(mediaEntities: List<MediaEntity>)

    suspend fun upsertMediaItem(mediaEntity: MediaEntity)

    suspend fun getMediaListByCategory(
        category: String
    ): List<MediaEntity>

    suspend fun getTrending(
        forceFetchFromRemote: Boolean,
        isRefresh: Boolean,
        type: String,
        time: String,
        page: Int
    ): Flow<Resource<List<Media>>>

    suspend fun getMoviesAndTv(
        forceFetchFromRemote: Boolean,
        isRefresh: Boolean,
        type: String,
        category: String,
        page: Int
    ): Flow<Resource<List<Media>>>

}



















