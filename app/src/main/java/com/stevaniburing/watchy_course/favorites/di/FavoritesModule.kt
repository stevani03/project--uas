package com.stevaniburing.watchy_course.favorites.di

import android.app.Application
import androidx.room.Room
import com.stevaniburing.watchy_course.favorites.data.loca.FavoriteMediaDatabase
import com.stevaniburing.watchy_course.favorites.data.remote.api.BackendMediaApi
import com.stevaniburing.watchy_course.util.BackendConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

/**
 * @author Ahmed Guedmioui
 */
@Module
@InstallIn(SingletonComponent::class)
object FavoritesModule {

    @Provides
    @Singleton
    fun providesMediaDatabase(application: Application): FavoriteMediaDatabase {
        return Room.databaseBuilder(
            application,
            FavoriteMediaDatabase::class.java,
            "favorites-db.db"
        ).build()
    }

    @Provides
    @Singleton
    fun providesMediaApi() : BackendMediaApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BackendConstants.BACKEND_BASE_URL)
            .build()
            .create()
    }

}
















