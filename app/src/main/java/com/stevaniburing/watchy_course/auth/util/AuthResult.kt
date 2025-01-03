package com.stevaniburing.watchy_course.auth.util

// Sealed class untuk menangani hasil otentikasi
sealed class AuthResult<T>(
    val data: T? = null, // Data terkait hasil otentikasi (optional)
    val message: String? = null // Pesan kesalahan (optional)
) {
    // Hasil otentikasi berhasil, dengan data (jika ada)
    class Authorized<T>(data: T? = null) : AuthResult<T>(data)

    // Hasil otentikasi gagal karena kredensial tidak sah
    class Unauthorized<T> : AuthResult<T>()

    // Hasil otentikasi gagal karena kesalahan yang tidak diketahui
    class UnknownError<T> : AuthResult<T>()

    // Hasil otentikasi gagal karena pengguna telah keluar
    class SingedOut<T> : AuthResult<T>()
}
