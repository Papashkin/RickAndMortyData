package com.antsfamily.rickandmortydata.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Greetings(name = "Android!")
        }
    }

    @Composable
    fun Greetings(name: String) {
        MaterialTheme(colors = getThemeColors()) {
            Column(Modifier.padding(horizontal = 20.dp, vertical = 16.dp)) {
                Text(text ="Hello $name", style = MaterialTheme.typography.body1)
            }
        }
    }

    @Composable
    fun getThemeColors(): Colors = if (isSystemInDarkTheme()) DarkColors else LightColors
}
