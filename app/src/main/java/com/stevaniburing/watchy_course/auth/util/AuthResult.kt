package com.stevaniburing.watchy_course.auth.util

sealed class AuthResult<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Authorized<T>(data: T? = null) : AuthResult<T>(data)
    class Unauthorized<T> : AuthResult<T>()
    class UnknownError<T> : AuthResult<T>()
    class SingedOut<T> : AuthResult<T>()
}