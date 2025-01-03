package com.stevaniburing.watchy_course.auth.data.remote.dto

// Data class ini mendefinisikan struktur data untuk permintaan otentikasi (AuthRequest)
data class AuthRequest(
    // Nama pengguna yang dikirimkan dalam permintaan otentikasi, dengan nilai default sebagai string kosong
    val name: String = "", 
    
    // Alamat email pengguna yang diperlukan untuk otentikasi
    val email: String, 
    
    // Kata sandi pengguna yang digunakan untuk otentikasi, dengan nilai default sebagai string kosong
    val password: String = ""
)
