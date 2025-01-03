package com.stevaniburing.watchy_course.auth.data.repository

// Mengimpor berbagai dependensi yang diperlukan untuk repositori ini
import android.content.SharedPreferences
import com.stevaniburing.watchy_course.auth.data.remote.AuthApi
import com.stevaniburing.watchy_course.auth.data.remote.dto.AuthRequest
import com.stevaniburing.watchy_course.auth.domain.repository.AuthRepository
import com.stevaniburing.watchy_course.auth.util.AuthResult
import com.stevaniburing.watchy_course.favorites.domain.repository.FavoritesRepository
import com.stevaniburing.watchy_course.main.domain.repository.MainRepository
import retrofit2.HttpException
import javax.inject.Inject

// Implementasi dari AuthRepository menggunakan dependensi yang diinjeksikan
class AuthRepositoryImpl @Inject constructor(
    private val authApi: AuthApi,  // API untuk otentikasi
    private val mainRepository: MainRepository,  // Repositori untuk operasi utama aplikasi
    private val favoritesRepository: FavoritesRepository,  // Repositori untuk operasi terkait favorit
    private val prefs: SharedPreferences  // SharedPreferences untuk menyimpan data pengguna secara lokal
) : AuthRepository {

    // Fungsi untuk registrasi pengguna baru
    override suspend fun register(
        name: String, email: String, password: String
    ): AuthResult<Unit> {

        return try {
            // Mengirim permintaan registrasi ke API
            authApi.register(
                AuthRequest(
                    name = name,
                    email = email,
                    password = password
                )
            )

            // Setelah registrasi berhasil, langsung login
            login(email, password)

        } catch (e: HttpException) {
            e.printStackTrace()
            // Menangani kesalahan HTTP (misalnya status 401 Unauthorized)
            if (e.code() == 401) {
                AuthResult.Unauthorized()
            } else {
                AuthResult.UnknownError()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            // Menangani kesalahan umum lainnya
            AuthResult.UnknownError()
        }
    }

    // Fungsi untuk login pengguna
    override suspend fun login(
        email: String, password: String
    ): AuthResult<Unit> {
        return try {
            // Mengirim permintaan login ke API
            val authRespond = authApi.login(
                authRequest = AuthRequest(
                    email = email,
                    password = password
                )
            )

            // Menyimpan data pengguna dan token ke SharedPreferences
            prefs.edit().putString("email", email).apply()
            prefs.edit().putString("name", authRespond.name).apply()
            prefs.edit().putString("token", authRespond.token).apply()

            // Mengembalikan status login berhasil
            AuthResult.Authorized()

        } catch (e: HttpException) {
            e.printStackTrace()
            // Menangani kesalahan HTTP
            if (e.code() == 401) {
                AuthResult.Unauthorized()
            } else {
                AuthResult.Authorized()  // Sebagai fallback jika ada error lainnya
            }
        } catch (e: Exception) {
            e.printStackTrace()
            // Menangani kesalahan umum lainnya
            AuthResult.Authorized()
        }
    }

    // Fungsi untuk memverifikasi token otentikasi pengguna
    override suspend fun authenticate(): AuthResult<Unit> {
        return try {
            // Mengambil token dari SharedPreferences
            val token = prefs.getString("token", null)
                ?: return AuthResult.Unauthorized()

            // Mengirim permintaan otentikasi ke API dengan token
            authApi.authenticate("Bearer $token")

            // Mengembalikan status otentikasi berhasil
            AuthResult.Authorized()

        } catch (e: HttpException) {
            e.printStackTrace()
            // Menangani kesalahan HTTP
            if (e.code() == 401) {
                AuthResult.Unauthorized()
            } else {
                AuthResult.Authorized()  // Sebagai fallback jika ada error lainnya
            }
        } catch (e: Exception) {
            e.printStackTrace()
            // Menangani kesalahan umum lainnya
            AuthResult.Authorized()
        }
    }

    // Fungsi untuk logout pengguna
    override suspend fun logout(): AuthResult<Unit> {
        // Menghapus data pengguna dan token dari SharedPreferences
        prefs.edit().putString("email", null).apply()
        prefs.edit().putString("name", null).apply()
        prefs.edit().putString("token", null).apply()

        // Membersihkan data aplikasi terkait pengguna
        mainRepository.cleareMainDb()
        favoritesRepository.clearMainDb()

        // Mengembalikan status signed out
        return AuthResult.SingedOut()
    }
}
