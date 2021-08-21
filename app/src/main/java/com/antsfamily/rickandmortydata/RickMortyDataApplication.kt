package com.antsfamily.rickandmortydata

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class RickMortyDataApplication: Application()