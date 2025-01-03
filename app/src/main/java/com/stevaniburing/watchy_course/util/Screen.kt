package com.stevaniburing.watchy_course.util

// Kelas Screen digunakan untuk menyimpan rute yang digunakan dalam navigasi aplikasi
sealed class Screen(val route: String) {

    // Rute utama untuk aplikasi
    object Core : Screen("core")

    // Rute untuk halaman login dan registrasi
    object Login : Screen("login")
    object Register : Screen("register")

    // Rute untuk halaman utama, trending, tv, dan movie
    object Main : Screen("main")
    object Trending : Screen("trending")
    object Tv : Screen("tv")
    object Movies : Screen("movies")

    // Rute untuk halaman profile pengguna
    object Profile : Screen("profile")

    // Rute untuk halaman detail core dan media
    object CoreDetails : Screen("core_details")
    object Details : Screen("details")
    
    // Rute untuk halaman menonton video
    object WatchVideo : Screen("watch_video")
    
    // Rute untuk halaman yang menampilkan media yang mirip
    object Similar : Screen("similar")
    
    // Rute untuk halaman pencarian
    object Search : Screen("search")

    // Rute untuk halaman yang berkaitan dengan favorit media
    object CoreFavorites : Screen("core_favorites")
    object Favorites : Screen("favorites")
    object LikedList : Screen("liked_list")
    object Bookmarked : Screen("bookmarked")

    // Rute untuk halaman kategori media
    object CoreCategories : Screen("core_categories")
    object Categories : Screen("categories")
    object CategoriesList : Screen("categories_list")
}
