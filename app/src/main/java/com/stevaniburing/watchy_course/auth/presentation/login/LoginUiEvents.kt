package com.stevaniburing.watchy_course.auth.presentation.login

/**
 * @author Ahmed Guedmioui
 */
sealed class LoginUiEvents {

    data class OnEmailChanged(
        val newEmail: String
    ): LoginUiEvents()

    data class OnPasswordChanged(
        val newPassword: String
    ): LoginUiEvents()

    object Login: LoginUiEvents()

}
















