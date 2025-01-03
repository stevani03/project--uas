package com.stevaniburing.watchy_course.auth.di

// Mengimpor dependensi yang diperlukan untuk penyuntikan dependensi
import com.stevaniburing.watchy_course.auth.domain.repository.AuthRepository
import com.stevaniburing.watchy_course.auth.data.repository.AuthRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// Modul Dagger untuk menyediakan AuthRepository
@Module
@InstallIn(SingletonComponent::class) // Menyatakan bahwa dependensi ini akan hidup sepanjang siklus hidup aplikasi (singleton)
abstract class AuthRepositoryModule {

    // Fungsi ini akan mengikat implementasi AuthRepositoryImpl ke antarmuka AuthRepository
    @Binds
    @Singleton // Menyatakan bahwa hanya ada satu instance dari AuthRepository yang digunakan dalam aplikasi
    abstract fun bindAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl // Implementasi yang akan disuntikkan
    ): AuthRepository // Antarmuka yang digunakan oleh kelas lain
}
