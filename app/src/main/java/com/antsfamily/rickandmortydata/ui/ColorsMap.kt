package com.antsfamily.rickandmortydata.ui

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

val White = Color(0xffffffff)
val Black = Color(0xff000000)
val Purple200 = Color(0xFFBB86FC)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)
val Teal700 = Color(0xFF018786)

val LightColors = lightColors(
    primary = Black,
    secondary = Teal200,
    primaryVariant = Teal700,
    onPrimary = White,
    background = White,
    surface = White
)

val DarkColors = darkColors(
    primary = White,
    secondary = Purple200,
    primaryVariant = Purple700,
    onPrimary = Black,
    background = Black,
    surface = Black
)