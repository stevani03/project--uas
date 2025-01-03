package com.stevaniburing.watchy_course.auth.data.remote.dto

// Data class ini mendefinisikan struktur data untuk respons otentikasi (AuthRespond)
data class AuthRespond(
    // Nama pengguna yang diterima sebagai bagian dari respons otentikasi
    val name: String, 
    
    // Token otentikasi yang diberikan setelah pengguna berhasil melakukan otentikasi
    val token: String
)
