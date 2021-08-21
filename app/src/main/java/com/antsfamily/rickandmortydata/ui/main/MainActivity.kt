package com.antsfamily.rickandmortydata.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.antsfamily.rickandmortydata.presentation.MainViewModel
import com.antsfamily.rickandmortydata.presentation.ViewModelFactory
import com.antsfamily.rickandmortydata.presentation.withFactory
import com.antsfamily.rickandmortydata.ui.navigation.SetupNavigation
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: MainViewModel by viewModels { withFactory(viewModelFactory) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SetupNavigation(viewModel)
        }
    }
}
