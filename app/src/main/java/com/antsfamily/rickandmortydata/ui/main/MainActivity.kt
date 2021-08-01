package com.antsfamily.rickandmortydata.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.antsfamily.rickandmortydata.R
import com.antsfamily.rickandmortydata.data.local.CharacterMainItem
import com.antsfamily.rickandmortydata.presentation.MainViewModel
import com.antsfamily.rickandmortydata.presentation.ViewModelFactory
import com.antsfamily.rickandmortydata.presentation.withFactory
import com.antsfamily.rickandmortydata.ui.DarkColors
import com.antsfamily.rickandmortydata.ui.LightColors
import com.antsfamily.rickandmortydata.ui.Padding
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
            val characters: List<CharacterMainItem> by viewModel.characters.observeAsState(emptyList())
            val isLoadingVisible: Boolean by viewModel.isLoadingVisible.observeAsState(true)
            Scaffold {
                MaterialTheme(colors = getThemeColors()) {
                    Column(
                        Modifier.padding(horizontal = Padding.zero, vertical = Padding.regular)
                    ) {
                        SetTitleIcon()
                        SetLoading(isLoadingVisible)
                        Spacer(Modifier.height(Padding.medium))
                        ShowCharacters(characters)
                    }
                }
            }
        }
    }

    @Composable
    fun getThemeColors(): Colors = if (isSystemInDarkTheme()) DarkColors else LightColors

    @Composable
    fun SetTitleIcon() {
        Image(
            painter = painterResource(id = R.drawable.rick_morty_title_logo),
            modifier = Modifier.fillMaxWidth(),
            contentDescription = null,
            contentScale = ContentScale.FillWidth
        )
    }

    @Composable
    fun SetLoading(isVisible: Boolean) {
        if (isVisible) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator()
            }
        }
    }

    @Composable
    fun ShowCharacters(items: List<CharacterMainItem>) {
        val scrollState = rememberLazyListState()
        LazyColumn(
            contentPadding = PaddingValues(Padding.medium),
            verticalArrangement = Arrangement.spacedBy(Padding.regular),
            state = scrollState
        ) {
            itemsIndexed(items) { _, data ->
                CharacterCard(
                    item = data,
                    modifier = Modifier.clickable { viewModel.onItemClick(data.id) }
                )
            }
        }
    }
}
