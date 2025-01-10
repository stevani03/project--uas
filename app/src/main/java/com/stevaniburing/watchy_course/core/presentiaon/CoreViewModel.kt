package com.stevaniburing.watchy_course.core.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stevaniburing.watchy_course.auth.domain.repository.AuthRepository
import com.stevaniburing.watchy_course.auth.util.AuthResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


 // ViewModel untuk modul inti aplikasi, bertanggung jawab untuk autentikasi pengguna.
 
 // @param authRepository Repository autentikasi yang digunakan untuk operasi login atau autentikasi.
 
@HiltViewModel
class CoreViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    // Channel untuk mengirim hasil autentikasi secara asinkron.
    private val _authResultChannel = Channel<AuthResult<Unit>>()
    val authResultChannel = _authResultChannel.receiveAsFlow() // Ekspos channel sebagai Flow.

    // StateFlow untuk menunjukkan status loading.
    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow() // Ekspos StateFlow sebagai read-only.

    // Blok inisialisasi untuk memulai proses autentikasi saat ViewModel dibuat.
    init {
        authenticate()
    }

    
     // Fungsi untuk menjalankan autentikasi pengguna.
     // Menggunakan viewModelScope untuk menjalankan operasi di dalam coroutine.
     // Mengatur state loading ke `true` sebelum memulai autentikasi.
     // Memanggil metode autentikasi dari `authRepository` dan mengirimkan hasilnya ke `_authResultChannel`.
     // Setelah selesai, mengatur state loading ke `false`.
    private fun authenticate() {
        viewModelScope.launch {
            _isLoading.update { true } // Mengatur status loading ke `true`.
            val result = authRepository.authenticate() // Memanggil autentikasi dari repository.
            _authResultChannel.send(result) // Mengirim hasil autentikasi melalui channel.
            _isLoading.update { false } // Mengatur status loading ke `false`.
        }
    }

}
