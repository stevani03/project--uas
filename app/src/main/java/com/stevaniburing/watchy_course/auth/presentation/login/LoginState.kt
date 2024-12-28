package com.stevaniburing.watchy_course.auth.presentation.login

/**
 * @author Ahmed Guedmioui
 */
data class LoginState(

    val isLoading: Boolean = false,

    val email: String = "stevani@example.com",
    val password: String = "Movie12#"

)
