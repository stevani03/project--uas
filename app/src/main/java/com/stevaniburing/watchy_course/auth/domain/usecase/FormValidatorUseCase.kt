package com.stevaniburing.watchy_course.auth.domain.usecase

/**
 * @author Ahmed Guedmioui
 */
data class FormValidatorUseCase(
    val validateEmailUseCase: ValidateEmailUseCase,
    val validatePasswordUseCase: ValidatePasswordUseCase,
    val validateNameUseCase: ValidateNameUseCase
)
