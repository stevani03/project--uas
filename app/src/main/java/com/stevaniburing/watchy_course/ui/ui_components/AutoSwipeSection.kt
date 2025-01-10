package com.stevaniburing.watchy_course.ui.ui_components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.stevaniburing.watchy_course.R
import com.stevaniburing.watchy_course.main.domain.model.Media

// Komponen UI yang menampilkan bagian AutoSwipe dengan judul dan opsi navigasi "See All".
// title Judul untuk bagian ini.
// route Rute navigasi yang digunakan ketika "See All" diklik.
// showSeeAll Indikator apakah teks "See All" harus ditampilkan.
// mainNavController Kontroler navigasi utama untuk item media.
// navController Kontroler navigasi tambahan untuk rute lain (opsional).
// mediaList Daftar objek media yang akan ditampilkan dalam komponen swipe.
@Composable
fun AutoSwipeSection(
    title: String,
    route: String = "",
    showSeeAll: Boolean = false,
    mainNavController: NavController,
    navController: NavController? = null,
    mediaList: List<Media>
) {
    // Kolom untuk mengatur elemen dalam tata letak vertikal.
    Column {
        // Baris atas dengan judul dan opsi "See All".
        Row(
            modifier = Modifier
                .fillMaxWidth() // Memenuhi lebar layar.
                .padding(horizontal = 32.dp, vertical = 16.dp), // Memberikan padding.
            verticalAlignment = Alignment.CenterVertically, // Menyatukan item secara vertikal.
            horizontalArrangement = Arrangement.SpaceBetween // Memberikan ruang di antara item.
        ) {
            // Menampilkan teks judul.
            Text(
                text = title,
                color = MaterialTheme.colorScheme.onBackground, // Menggunakan warna tema.
                fontSize = 20.sp // Ukuran font.
            )

            // Menampilkan teks "See All" jika diaktifkan.
            if (showSeeAll) {
                Text(
                    modifier = Modifier.clickable {
                        navController?.navigate(route) // Navigasi ke rute jika diklik.
                    },
                    text = stringResource(id = R.string.see_all), // Teks "See All" dari resources.
                    color = MaterialTheme.colorScheme.onBackground.copy(0.7f), // Warna teks dengan transparansi.
                    fontSize = 14.sp // Ukuran font.
                )
            }
        }

        // Menampilkan komponen swipe untuk daftar media.
        AutoImageSwipePager(
            mainNavController = mainNavController, // Kontroler navigasi utama.
            mediaList = mediaList // Daftar media.
        )
    }
}
