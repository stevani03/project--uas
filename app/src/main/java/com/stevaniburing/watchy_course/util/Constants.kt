package com.stevaniburing.watchy_course.util

import com.stevaniburing.watchy_course.main.domain.model.Genre

// Kelas Constants yang menyimpan beberapa konstanta yang digunakan di aplikasi
object Constants {
    // Nama-nama kategori untuk media yang akan ditampilkan
    const val actionAndAdventureList = "Action And Adventure"
    const val dramaList = "Drama"
    const val comedyList = "Comedy"
    const val sciFiAndFantasyList = "Sci-Fi And Fantasy"
    const val animationList = "Animation"
}

// Kelas BackendConstants yang menyimpan URL dasar dan endpoint-endpoint untuk API backend
object BackendConstants {

    // URL dasar untuk API backend
    const val BACKEND_BASE_URL = "http://192.168.10.162:8081/"

    // Endpoint untuk berbagai operasi autentikasi
    const val REGISTER = "register"
    const val LOGIN = "login"
    const val AUTHENTICATE = "authenticate"

    // Endpoint untuk mengambil dan memanipulasi data media yang disukai dan dibookmark
    const val GET_LIKED_MEDIA_LIST = "get-liked-media-list/{email}"
    const val GET_BOOKMARKED_MEDIA_LIST = "get-bookmarked-media-list/{email}"
    const val GET_MEDIA_BY_ID = "get-media-by-id/{mediaId}/{email}"
    const val UPSERT_MEDIA_TO_USER = "upsert-media-to-user"
    const val DELETE_MEDIA_FROM_USER = "delete-media-from-user"
}

// Kelas APIConstants yang menyimpan query dan informasi terkait API dari TMDB
object APIConstants {

    // Konstanta untuk query yang digunakan pada pemanggilan API
    const val POPULAR = "popular"
    const val TRENDING = "trending"
    const val TRENDING_TIME = "day"
    const val ALL = "all"
    const val MOVIE = "movie"
    const val TV = "tv"

    // Tag untuk debugging API requests
    const val GET_TAG = "--> GET https://api.themoviedb.org"

    // Daftar genre yang digunakan untuk mengelompokkan media
    val genres = listOf(
        Genre(12, "Adventure"),
        Genre(14, "Fantasy"),
        Genre(16, "Animation"),
        Genre(18, "Drama"),
        Genre(27, "Horror"),
        Genre(28, "Action"),
        Genre(35, "Comedy"),
        Genre(36, "History"),
        Genre(37, "Western"),
        Genre(53, "Thriller"),
        Genre(80, "Crime"),
        Genre(99, "Documentary"),
        Genre(878, "Science Fiction"),
        Genre(9648, "Mystery"),
        Genre(10402, "Music"),
        Genre(10749, "Romance"),
        Genre(10751, "Family"),
        Genre(10752, "War"),
        Genre(10759, "Action & Adventure"),
        Genre(10762, "Kids"),
        Genre(10763, "News"),
        Genre(10764, "Reality"),
        Genre(10765, "Sci-Fi & Fantasy"),
        Genre(10766, "Soap"),
        Genre(10767, "Talk"),
        Genre(10768, "War & Politics"),
        Genre(10770, "TV Movie")
    )
}
