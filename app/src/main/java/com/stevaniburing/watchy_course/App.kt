package com.stevaniburing.watchy_course

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

// Kelas ini menginisialisasi aplikasi dan menyediakan dependensi melalui Hilt
@HiltAndroidApp
class App: Application() {

    // Fungsi ini dipanggil saat aplikasi pertama kali dibuat
    override fun onCreate() {
        super.onCreate()
        
        // Menanamkan pohon debug Timber untuk logging selama pengembangan
        Timber.plant(Timber.DebugTree())
    }
}
