package com.stevaniburing.watchy_course.auth.domain.usecase

// Kelas untuk memvalidasi panjang nama
class ValidateNameUseCase {

    // Operator function yang akan dipanggil untuk memvalidasi nama
    operator fun invoke(name: String): Boolean {
        // Memeriksa apakah panjang nama berada dalam rentang 4 hingga 50 karakter
        return name.length in 4..50
    }

}
