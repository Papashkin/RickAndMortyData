package com.antsfamily.rickandmortydata.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.antsfamily.rickandmortydata.R
import com.antsfamily.rickandmortydata.data.local.CharacterMainItem
import com.antsfamily.rickandmortydata.presentation.MainViewModel
import com.antsfamily.rickandmortydata.ui.*

@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel(),
    onItemClick: (Int) -> Unit
) {
    val characters: List<CharacterMainItem> by viewModel.characters.observeAsState(emptyList())
    val isLoadingVisible: Boolean by viewModel.isLoadingVisible.observeAsState(true)

    MaterialTheme(colors = getThemeColors()) {
        Scaffold {
            Column(
                Modifier.padding(horizontal = Padding.zero, vertical = Padding.regular)
            ) {
                SetTitleIcon()
                SetLoading(isLoadingVisible)
                Spacer(Modifier.height(Padding.regular))
                ShowCharacters(characters, onItemClick)
            }
        }
    }
}

@Composable
fun getThemeColors(): Colors = if (isSystemInDarkTheme()) DarkColors else LightColors

@Composable
fun SetTitleIcon() {
    Image(
        painter = painterResource(id = R.drawable.ic_rick_and_morty),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Padding.huge),
        contentDescription = null,
        alignment = Alignment.Center
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
fun ShowCharacters(items: List<CharacterMainItem>, onItemClick: (Int) -> Unit) {
    val scrollState = rememberLazyListState()
    LazyColumn(
        contentPadding = PaddingValues(Padding.medium),
        verticalArrangement = Arrangement.spacedBy(Padding.regular),
        state = scrollState
    ) {
        itemsIndexed(items) { _, data -> CharacterCard(item = data, onItemClick = onItemClick) }
    }
}

@Composable
fun CharacterCard(item: CharacterMainItem, onItemClick: (Int) -> Unit) {
    Card(
        modifier = Modifier.wrapContentHeight(unbounded = true),
        shape = RoundedCornerShape(Rounding.regular),
        elevation = Elevation.tiny,
    ) {
        Row(
            modifier = Modifier.clickable { onItemClick.invoke(item.id) },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberImagePainter(
                    data = item.image,
                    builder = { transformations(getLeftRoundedCornersTransformation()) }
                ),
                contentDescription = null,
                modifier = Modifier.size(ImageSize.large)
            )
            Column(
                modifier = Modifier
                    .padding(horizontal = Padding.medium, vertical = Padding.small)
                    .fillMaxWidth()
            ) {
                Text(text = item.name, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.h6)
                Text(text = item.species, style = MaterialTheme.typography.body2)
                Spacer(Modifier.height(Padding.medium))
                Text(text = item.status, style = MaterialTheme.typography.caption)
            }
        }
    }
}

fun getLeftRoundedCornersTransformation() = RoundedCornersTransformation(16.0f, 0.0f, 16.0f, 0.0f)
