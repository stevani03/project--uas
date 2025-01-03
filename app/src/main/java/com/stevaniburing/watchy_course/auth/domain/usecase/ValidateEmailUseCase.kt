package com.stevaniburing.watchy_course.auth.domain.usecase

// Kelas untuk memvalidasi format email
class ValidateEmailUseCase {

    // Operator function yang akan dipanggil untuk memvalidasi email
    operator fun invoke(email: String): Boolean {
        // Menggunakan Patterns.EMAIL_ADDRESS untuk mencocokkan email dengan pola yang valid
        return Patterns.EMAIL_ADDRESS.matcher(
            email
        ).matches() // Mengembalikan true jika email sesuai dengan pola, false jika tidak
    }

}
