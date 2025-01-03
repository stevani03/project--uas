package com.stevaniburing.watchy_course.auth.di

// Mengimpor berbagai dependensi yang diperlukan untuk penyuntikan dependensi
import android.app.Application
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.stevaniburing.watchy_course.auth.data.remote.AuthApi
import com.stevaniburing.watchy_course.auth.domain.usecase.FormValidatorUseCase
import com.stevaniburing.watchy_course.auth.domain.usecase.ValidateEmailUseCase
import com.stevaniburing.watchy_course.auth.domain.usecase.ValidateNameUseCase
import com.stevaniburing.watchy_course.auth.domain.usecase.ValidatePasswordUseCase
import com.stevaniburing.watchy_course.util.BackendConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

// Modul Dagger untuk menyediakan dependensi terkait otentikasi
@Module
@InstallIn(SingletonComponent::class) // Menyatakan bahwa dependensi ini hanya dibuat sekali untuk seluruh aplikasi
class AuthModule {

    // Menyediakan AuthApi menggunakan Retrofit untuk berkomunikasi dengan API backend
    @Provides
    @Singleton // Menggunakan Singleton agar instance ini hanya ada satu dalam aplikasi
    fun provideAuthApi(): AuthApi {
        return Retrofit.Builder()
            .baseUrl(BackendConstants.BACKEND_BASE_URL) // URL dasar untuk API backend
            .addConverterFactory(GsonConverterFactory.create()) // Menambahkan converter untuk mengubah data JSON ke objek Kotlin
            .build()
            .create() // Membuat instansi AuthApi dari Retrofit
    }

    // Menyediakan SharedPreferences yang ter-enkripsi untuk penyimpanan data pengguna yang aman
    @Provides
    @Singleton // Singleton agar hanya ada satu instance SharedPreferences yang digunakan di aplikasi
    fun provideEncryptedSharedPrefs(
        application: Application // Akses konteks aplikasi
    ): SharedPreferences {

        // Membuat MasterKey yang diperlukan untuk enkripsi
        val masterKey = MasterKey.Builder(application)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM) // Pengaturan skema kunci untuk enkripsi
            .build()

        // Membuat EncryptedSharedPreferences untuk penyimpanan data terenkripsi
        return EncryptedSharedPreferences.create(
            application,
            "watchy_course_app_prefs", // Nama file preferensi yang terenkripsi
            masterKey, // Master key yang digunakan untuk enkripsi
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV, // Skema enkripsi untuk kunci
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM // Skema enkripsi untuk nilai
        )
    }

    // Menyediakan FormValidatorUseCase yang memvalidasi formulir dengan menggunakan use case individual
    @Provides
    @Singleton // Singleton agar hanya ada satu instance dari FormValidatorUseCase
    fun provideFromValidatorUseCase(): FormValidatorUseCase {
        return FormValidatorUseCase(
            ValidateEmailUseCase(), // Memvalidasi email
            ValidatePasswordUseCase(), // Memvalidasi password
            ValidateNameUseCase() // Memvalidasi nama
        )
    }

}
