package com.example.week_4

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun BmiScreen() {

    var berat by rememberSaveable {
        mutableStateOf("60")
    }

    var tinggi by rememberSaveable {
        mutableStateOf("165")
    }

    var showResult by rememberSaveable {
        mutableStateOf(false)
    }

    var snackbarCategory by rememberSaveable {
        mutableStateOf<String?>(null)
    }

    val beratValue by remember {
        derivedStateOf {
            berat.toFloatOrNull() ?: 0f
        }
    }

    val tinggiValue by remember {
        derivedStateOf {
            tinggi.toFloatOrNull() ?: 0f
        }
    }

    val bmi by remember {
        derivedStateOf {

            if (tinggiValue > 0f) {

                val tinggiMeter = tinggiValue / 100f

                beratValue / (tinggiMeter * tinggiMeter)

            } else {
                0f
            }
        }
    }

    val kategori by remember {

        derivedStateOf {

            when {

                bmi <= 0f ->
                    ""

                bmi < 18.5f ->
                    "Berat Badan Kurus"

                bmi < 25f ->
                    "Berat Badan Normal"

                bmi < 30f ->
                    "Berat Badan Gemuk"

                else ->
                    "Obesitas"
            }
        }
    }

    val snackbarHostState = remember {
        SnackbarHostState()
    }

    // TODO 4:
    // Snackbar dipicu oleh perubahan kategori,
    // bukan showSnackbar langsung di onClick.
    LaunchedEffect(snackbarCategory) {

        val message = snackbarCategory
            ?: return@LaunchedEffect

        val snackbarJob = launch {

            snackbarHostState.showSnackbar(
                message = message,
                duration = SnackbarDuration.Indefinite
            )
        }

        delay(3000)

        snackbarHostState
            .currentSnackbarData
            ?.dismiss()

        snackbarJob.cancel()
    }

    Scaffold(

        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState
            )
        }

    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(20.dp)
        ) {

            Text(
                text = "Kalkulator BMI",
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            OutlinedTextField(
                value = berat,

                onValueChange = {
                    berat = it
                    showResult = false
                },

                label = {
                    Text("Berat badan (kg)")
                },

                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal
                ),

                modifier = Modifier.fillMaxWidth()
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            OutlinedTextField(
                value = tinggi,

                onValueChange = {
                    tinggi = it
                    showResult = false
                },

                label = {
                    Text("Tinggi badan (cm)")
                },

                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal
                ),

                modifier = Modifier.fillMaxWidth()
            )

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            Row(
                horizontalArrangement =
                    Arrangement.spacedBy(12.dp)
            ) {

                Button(

                    onClick = {

                        if (
                            beratValue > 0f &&
                            tinggiValue > 0f
                        ) {

                            showResult = true

                            snackbarCategory = kategori
                        }
                    }

                ) {

                    Text("Hitung BMI")
                }

                // TODO 1b
                Button(

                    onClick = {

                        berat = "60"

                        tinggi = "165"

                        showResult = false

                        snackbarCategory = null
                    }

                ) {

                    Text("Reset")
                }
            }

            if (showResult) {

                Spacer(
                    modifier = Modifier.height(24.dp)
                )

                Card(
                    modifier =
                        Modifier.fillMaxWidth()
                ) {

                    Column(
                        modifier =
                            Modifier.padding(20.dp)
                    ) {

                        Text(
                            text = "BMI Anda"
                        )

                        Text(
                            text =
                                "%.2f".format(bmi),
                            fontWeight =
                                FontWeight.Bold
                        )

                        Text(
                            text = kategori
                        )
                    }
                }

                Spacer(
                    modifier =
                        Modifier.height(24.dp)
                )

                // TODO 1a
                InterpretasiBmiTable()
            }
        }
    }
}


// TODO 1a
@Composable
fun InterpretasiBmiTable() {

    Column(
        modifier =
            Modifier.fillMaxWidth()
    ) {

        Text(
            text = "Interpretasi BMI",
            fontWeight = FontWeight.Bold
        )

        Spacer(
            modifier =
                Modifier.height(8.dp)
        )

        BmiTableRow(
            kategori = "Kurus",
            rentang = "< 18.5"
        )

        HorizontalDivider()

        BmiTableRow(
            kategori = "Normal",
            rentang = "18.5 - 24.9"
        )

        HorizontalDivider()

        BmiTableRow(
            kategori = "Gemuk",
            rentang = "25.0 - 29.9"
        )

        HorizontalDivider()

        BmiTableRow(
            kategori = "Obesitas",
            rentang = "≥ 30"
        )
    }
}


@Composable
fun BmiTableRow(
    kategori: String,
    rentang: String
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),

        horizontalArrangement =
            Arrangement.SpaceBetween
    ) {

        Text(kategori)

        Text(rentang)
    }
}