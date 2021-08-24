package com.antsfamily.rickandmortydata.ui

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

val Main = Color(0xFF11B0C8)
val White = Color(0xFFFFFFFF)
val Black = Color(0xFF000000)
val Grey = Color(0xFFD6D6D6)
val LightCyan = Color(0xFFF0F9FA)
val DarkCyan = Color(0xFF2F3738)

val LightColors = lightColors(
    primary = Black,
    onPrimary = LightCyan,
    secondary = Main,
    onSecondary = Grey,
    background = LightCyan,
    onBackground = Black,
    surface = White,
    onSurface = Black
)

val DarkColors = darkColors(
    primary = White,
    onPrimary = DarkCyan,
    secondary = Main,
    onSecondary = Grey,
    background = DarkCyan,
    onBackground = White,
    surface = Black,
    onSurface = White
)