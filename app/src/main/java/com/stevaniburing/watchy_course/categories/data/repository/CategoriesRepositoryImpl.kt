package com.stevaniburing.watchy_course.categories.data.repository

import com.stevaniburing.watchy_course.categories.domain.repository.CategoriesRepository
import com.stevaniburing.watchy_course.main.data.local.MediaDatabase
import com.stevaniburing.watchy_course.main.data.mappers.toMedia
import com.stevaniburing.watchy_course.main.domain.model.Media
import com.stevaniburing.watchy_course.util.APIConstants.genres
import javax.inject.Inject

// Implementasi CategoriesRepository yang bertanggung jawab untuk mengambil data media berdasarkan kategori genre
class CategoriesRepositoryImpl @Inject constructor(
    mediaDatabase: MediaDatabase // Menggunakan MediaDatabase yang disuntikkan melalui konstruktor
) : CategoriesRepository {

    private val mediaDao = mediaDatabase.mediaDao // Mengakses DAO (Data Access Object) untuk media

    // Mendapatkan daftar media berdasarkan genre Action dan Adventure
    override suspend fun getActionAndAdventure(): List<Media> {
        val actionAndAdventureGenres = genres.filter { genre ->
            // Memfilter genre berdasarkan kategori Action, Adventure, dan genre terkait
            genre.genreName in listOf(
                "Adventure", "Horror", "Action", "Western", "Thriller", "Crime", "War"
            )
        }.map { it.genreId.toString() }

        // Mendapatkan media berdasarkan genre yang sudah difilter
        return getMediaListByGenreIds(actionAndAdventureGenres)
    }

    // Mendapatkan daftar media berdasarkan genre Drama
    override suspend fun getDrama(): List<Media> {
        val dramaGenres = genres.filter { genre ->
            // Memfilter genre berdasarkan kategori Drama dan genre terkait
            genre.genreName in listOf(
                "Drama", "Comedy", "Family", "Romance", "Music"
            )
        }.map { it.genreId.toString() }

        // Mendapatkan media berdasarkan genre yang sudah difilter
        return getMediaListByGenreIds(dramaGenres)
    }

    // Mendapatkan daftar media berdasarkan genre Comedy
    override suspend fun getComedy(): List<Media> {
        val comedyGenres = genres.filter { genre ->
            // Memfilter genre berdasarkan kategori Comedy dan genre terkait
            genre.genreName in listOf(
                "Comedy", "Family", "Romance"
            )
        }.map { it.genreId.toString() }

        // Mendapatkan media berdasarkan genre yang sudah difilter
        return getMediaListByGenreIds(comedyGenres)
    }

    // Mendapatkan daftar media berdasarkan genre Sci-Fi dan Fantasy
    override suspend fun getSciFiAndFantasy(): List<Media> {
        val sciFiAndFantasyGenres = genres.filter { genre ->
            // Memfilter genre berdasarkan kategori Sci-Fi dan Fantasy serta genre terkait
            genre.genreName in listOf(
                "Fantasy", "Horror", "Thriller", "Crime", "Documentary", "Science Fiction", "Mystery"
            )
        }.map { it.genreId.toString() }

        // Mendapatkan media berdasarkan genre yang sudah difilter
        return getMediaListByGenreIds(sciFiAndFantasyGenres)
    }

    // Mendapatkan daftar media berdasarkan genre Animation
    override suspend fun getAnimation(): List<Media> {
        val animationGenres = genres.filter { genre ->
            // Memfilter genre berdasarkan kategori Animation
            genre.genreName == "Animation"
        }.map { it.genreId.toString() }

        // Mendapatkan media berdasarkan genre yang sudah difilter
        return getMediaListByGenreIds(animationGenres)
    }

    // Fungsi untuk mendapatkan daftar media berdasarkan daftar genreId
    private suspend fun getMediaListByGenreIds(
        genreIds: List<String>
    ): List<Media> {
        // Mengambil semua media dari database lokal dan memetakannya menjadi objek Media
        val mediaList = mediaDao.getAllMediaList().map {
            it.toMedia() // Mengonversi entitas data menjadi model Media
        }

        // Memfilter media berdasarkan genreIds yang cocok dan mengacak hasilnya
        return mediaList.filter { media ->
            media.genreIds.any { genreId ->
                genreId in genreIds // Memeriksa apakah ada genreId yang cocok
            }
        }.shuffled() // Mengacak urutan hasil media
    }
}
