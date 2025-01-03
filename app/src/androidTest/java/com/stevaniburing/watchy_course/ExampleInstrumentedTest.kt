package com.stevaniburing.watchy_course

// Mengimpor kelas yang dibutuhkan untuk menjalankan pengujian instrumen pada aplikasi Android
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

// Mengimpor kelas dan anotasi untuk pengujian unit
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*

@RunWith(AndroidJUnit4::class) // Menentukan bahwa pengujian ini akan dijalankan menggunakan AndroidJUnit4
class ExampleInstrumentedTest {
    
    @Test // Menandai metode ini sebagai metode pengujian
    fun useAppContext() {
        // Mendapatkan konteks aplikasi yang sedang diuji
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        
        // Memastikan bahwa nama paket aplikasi yang sedang diuji sesuai dengan yang diharapkan
        assertEquals("com.stevaniburing.watchy_course", appContext.packageName)
    }
}
