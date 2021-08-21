package com.antsfamily.rickandmortydata.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.antsfamily.rickandmortydata.ui.navigation.SetupNavigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RickMortyDataActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SetupNavigation()
        }
    }
}