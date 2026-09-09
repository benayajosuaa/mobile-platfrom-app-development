package com.example.week_3

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.week_3.ui.theme.Week3Theme

data class Mahasiswa(
    val nama: String,
    val nim: String,
    val ipk: Double
)

val dummyMahasiswa = listOf(
    Mahasiswa("Indah Lestari", "22009", 3.65),
    Mahasiswa("Joko Pratama", "22010", 3.20),
    Mahasiswa("Kevin Wijaya", "22011", 3.78),
    Mahasiswa("Lina Hartono", "22012", 3.46),
    Mahasiswa("Michael Tamba", "22013", 3.91),
    Mahasiswa("Nadia Putri", "22014", 3.22),
    Mahasiswa("Oscar Halim", "22015", 3.67),
    Mahasiswa("Putri Anggraini", "22016", 3.84),
    Mahasiswa("Rafael Gunawan", "22017", 3.15),
    Mahasiswa("Sarah Natalia", "22018", 3.73),
    Mahasiswa("Thomas Wijaya", "22019", 3.55),
    Mahasiswa("Vania Lestari", "22020", 3.96),
    Mahasiswa("William Tan", "22021", 3.82),
    Mahasiswa("Xena Olivia", "22022", 3.40),
    Mahasiswa("Yosua Pratama", "22023", 3.89),
    Mahasiswa("Zahra Amalia", "22024", 3.60),
    Mahasiswa("Aditya Putra", "22025", 2.90),
    Mahasiswa("Bella Stephanie", "22026", 3.70),
    Mahasiswa("Christian Agus", "22027", 3.52),
    Mahasiswa("Dionisius Roy", "22028", 3.18),
    Mahasiswa("Evelyn Rose", "22029", 3.95),
    Mahasiswa("Farel Prayoga", "22030", 3.33)
)

@Composable
fun StudentListScreen() {
    DaftarMahasiswa(mahasiswaList = dummyMahasiswa)
}

@Composable
fun DaftarMahasiswa(
    mahasiswaList: List<Mahasiswa>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        // Header
        item {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Daftar Mahasiswa Informatika",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.primary
                )

                Text(
                    text = "Total mahasiswa: ${mahasiswaList.size}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                CategoryRow()
            }
        }

        // Daftar mahasiswa
        items(
            items = mahasiswaList,
            key = { it.nim }
        ) { mahasiswa ->
            MahasiswaCard(mahasiswa)
        }

        // Footer
        item {
            Text(
                text = "Akhir daftar mahasiswa",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun MahasiswaCard(mahasiswa: Mahasiswa) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Column {
                Text(
                    text = mahasiswa.nama,
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = "NIM: ${mahasiswa.nim}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Text(
                text = "IPK ${mahasiswa.ipk}",
                style = MaterialTheme.typography.labelLarge,
                color = if (mahasiswa.ipk >= 3.5) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.onSurface
                }
            )
        }
    }
}

@Composable
fun CategoryRow() {
    val categories = listOf(
        "Semua",
        "IPK >= 3.5",
        "IPK < 3.5"
    )

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(categories) { category ->
            Card(
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 1.dp
                )
            ) {
                Text(
                    text = category,
                    modifier = Modifier.padding(
                        horizontal = 16.dp,
                        vertical = 8.dp
                    ),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun StudentListScreenPreview() {
    Week3Theme {
        StudentListScreen()
    }
}