package com.example.week_4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.week_4.ui.theme.Week4Theme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {

            Week4Theme {

                var selectedTab by rememberSaveable {
                    mutableIntStateOf(0)
                }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),

                    bottomBar = {

                        NavigationBar {

                            NavigationBarItem(
                                selected = selectedTab == 0,
                                onClick = {
                                    selectedTab = 0
                                },
                                icon = {
                                    Text("⚖")
                                },
                                label = {
                                    Text("BMI")
                                }
                            )

                            NavigationBarItem(
                                selected = selectedTab == 1,
                                onClick = {
                                    selectedTab = 1
                                },
                                icon = {
                                    Text("℃")
                                },
                                label = {
                                    Text("Suhu")
                                }
                            )

                            NavigationBarItem(
                                selected = selectedTab == 2,
                                onClick = {
                                    selectedTab = 2
                                },
                                icon = {
                                    Text("👤")
                                },
                                label = {
                                    Text("Registrasi")
                                }
                            )
                        }
                    }

                ) { innerPadding ->

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {

                        when (selectedTab) {

                            0 -> BmiScreen()

                            1 -> SuhuScreen()

                            2 -> RegistrasiScreen()
                        }
                    }
                }
            }
        }
    }
}