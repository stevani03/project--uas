package com.stevaniburing.watchy_course.auth.domain.usecase

// Kelas untuk memvalidasi password
class ValidatePasswordUseCase {

    // Operator function yang akan dipanggil untuk memvalidasi password
    operator fun invoke(password: String): Boolean {
        // Memeriksa apakah password memenuhi kriteria:
        // 1. Panjang password minimal 8 karakter
        // 2. Password mengandung huruf kapital
        // 3. Password mengandung angka
        return password.length >= 8 &&
                password.any { it.isUpperCase() } && // Memeriksa adanya huruf kapital
                password.any { it.isDigit() } // Memeriksa adanya angka
    }

}
