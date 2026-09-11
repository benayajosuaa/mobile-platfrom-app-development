package com.example.week_4

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun RegistrasiScreen() {

    var nama by rememberSaveable {
        mutableStateOf("")
    }

    var email by rememberSaveable {
        mutableStateOf("")
    }

    var password by rememberSaveable {
        mutableStateOf("")
    }

    var konfirmasiPassword by rememberSaveable {
        mutableStateOf("")
    }

    var berhasilDaftar by rememberSaveable {
        mutableStateOf(false)
    }

    // TODO 3a
    val isNamaValid =
        nama.isNotBlank()

    // TODO 3b
    val isEmailValid =
        email.contains("@")

    // TODO 3c
    val isPasswordValid =
        password.length >= 8

    // TODO 3d
    val isKonfirmasiValid =
        konfirmasiPassword == password &&
                konfirmasiPassword.isNotEmpty()

    // TODO 3e
    val isFormValid by remember {

        derivedStateOf {

            nama.isNotBlank() &&
                    email.contains("@") &&
                    password.length >= 8 &&
                    konfirmasiPassword == password &&
                    konfirmasiPassword.isNotEmpty()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Text(
            text = "Form Registrasi",
            fontWeight = FontWeight.Bold
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        OutlinedTextField(
            value = nama,

            onValueChange = {
                nama = it
                berhasilDaftar = false
            },

            label = {
                Text("Nama")
            },

            isError =
                nama.isNotEmpty() &&
                        !isNamaValid,

            supportingText = {

                if (
                    nama.isNotEmpty() &&
                    !isNamaValid
                ) {

                    Text(
                        "Nama tidak boleh kosong"
                    )
                }
            },

            modifier =
                Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        OutlinedTextField(
            value = email,

            onValueChange = {
                email = it
                berhasilDaftar = false
            },

            label = {
                Text("Email")
            },

            keyboardOptions =
                KeyboardOptions(
                    keyboardType =
                        KeyboardType.Email
                ),

            isError =
                email.isNotEmpty() &&
                        !isEmailValid,

            supportingText = {

                if (
                    email.isNotEmpty() &&
                    !isEmailValid
                ) {

                    Text(
                        "Email harus mengandung @"
                    )
                }
            },

            modifier =
                Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        OutlinedTextField(
            value = password,

            onValueChange = {
                password = it
                berhasilDaftar = false
            },

            label = {
                Text("Password")
            },

            visualTransformation =
                PasswordVisualTransformation(),

            keyboardOptions =
                KeyboardOptions(
                    keyboardType =
                        KeyboardType.Password
                ),

            isError =
                password.isNotEmpty() &&
                        !isPasswordValid,

            supportingText = {

                if (
                    password.isNotEmpty() &&
                    !isPasswordValid
                ) {

                    Text(
                        "Password minimal 8 karakter"
                    )
                }
            },

            modifier =
                Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        OutlinedTextField(
            value = konfirmasiPassword,

            onValueChange = {
                konfirmasiPassword = it
                berhasilDaftar = false
            },

            label = {
                Text("Konfirmasi Password")
            },

            visualTransformation =
                PasswordVisualTransformation(),

            keyboardOptions =
                KeyboardOptions(
                    keyboardType =
                        KeyboardType.Password
                ),

            isError =
                konfirmasiPassword.isNotEmpty() &&
                        !isKonfirmasiValid,

            supportingText = {

                if (
                    konfirmasiPassword.isNotEmpty() &&
                    !isKonfirmasiValid
                ) {

                    Text(
                        "Password tidak sama"
                    )
                }
            },

            modifier =
                Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        Button(

            onClick = {
                berhasilDaftar = true
            },

            // TODO 3e
            enabled = isFormValid,

            modifier =
                Modifier.fillMaxWidth()

        ) {

            Text("Daftar")
        }

        if (berhasilDaftar) {

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Text(
                text =
                    "Registrasi berhasil!"
            )
        }
    }
}