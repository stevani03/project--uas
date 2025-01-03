package com.stevaniburing.watchy_course.auth.data.remote

// Mengimpor DTO yang digunakan untuk permintaan dan respons otentikasi
import com.stevaniburing.watchy_course.auth.data.remote.dto.AuthRequest
import com.stevaniburing.watchy_course.auth.data.remote.dto.AuthRespond

// Mengimpor konstanta yang digunakan untuk URL backend
import com.stevaniburing.watchy_course.util.BackendConstants

// Mengimpor Retrofit2 untuk menangani HTTP requests
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

// Interface untuk mendefinisikan API otentikasi dengan Retrofit
interface AuthApi {

    // Endpoint untuk pendaftaran pengguna baru
    @POST(BackendConstants.REGISTER)
    suspend fun register(
        // Menyertakan permintaan otentikasi yang berisi data pengguna
        @Body authRequest: AuthRequest
    )

    // Endpoint untuk login pengguna
    @POST(BackendConstants.LOGIN)
    suspend fun login(
        // Menyertakan permintaan otentikasi yang berisi data pengguna (email dan password)
        @Body authRequest: AuthRequest
    ): AuthRespond

    // Endpoint untuk memverifikasi token otentikasi pengguna
    @GET(BackendConstants.AUTHENTICATE)
    suspend fun authenticate(
        // Menyertakan token otentikasi di header Authorization
        @Header("Authorization") token: String
    )
}
