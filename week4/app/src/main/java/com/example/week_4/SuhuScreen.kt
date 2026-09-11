package com.example.week_4

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

@Composable
fun SuhuScreen() {

    var celsius by rememberSaveable {
        mutableStateOf("")
    }

    // TODO 2a
    val celsiusValue by remember {

        derivedStateOf {

            celsius.toFloatOrNull()
        }
    }

    // TODO 2b
    val fahrenheit by remember {

        derivedStateOf {

            celsiusValue?.let {
                it * 9f / 5f + 32f
            }
        }
    }

    // TODO 2b
    val kelvin by remember {

        derivedStateOf {

            celsiusValue?.let {
                it + 273.15f
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Text(
            text = "Konversi Suhu",
            fontWeight = FontWeight.Bold
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        OutlinedTextField(
            value = celsius,

            onValueChange = {
                celsius = it
            },

            label = {
                Text("Celsius")
            },

            suffix = {
                Text("°C")
            },

            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal
            ),

            modifier =
                Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        TemperatureCard(
            title = "Fahrenheit",

            value = fahrenheit?.let {
                "%.2f °F".format(it)
            } ?: "-"
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        TemperatureCard(
            title = "Kelvin",

            value = kelvin?.let {
                "%.2f K".format(it)
            } ?: "-"
        )
    }
}


@Composable
fun TemperatureCard(
    title: String,
    value: String
) {

    Card(
        modifier =
            Modifier.fillMaxWidth()
    ) {

        Column(
            modifier =
                Modifier.padding(20.dp)
        ) {

            Text(
                text = title
            )

            Text(
                text = value,
                fontWeight = FontWeight.Bold
            )
        }
    }
}