package com.stevaniburing.watchy_course.auth.domain.usecase

// Mendefinisikan use case untuk memvalidasi data formulir (email, password, nama)
data class FormValidatorUseCase(
    val validateEmailUseCase: ValidateEmailUseCase, // Use case untuk validasi email
    val validatePasswordUseCase: ValidatePasswordUseCase, // Use case untuk validasi password
    val validateNameUseCase: ValidateNameUseCase // Use case untuk validasi nama
)
