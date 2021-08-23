package com.antsfamily.rickandmortydata.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import coil.compose.rememberImagePainter
import com.antsfamily.rickandmortydata.data.local.CharacterMainItem
import com.antsfamily.rickandmortydata.extensions.mapDistinct
import com.antsfamily.rickandmortydata.presentation.HomeViewModel
import com.antsfamily.rickandmortydata.ui.ImageSize
import com.antsfamily.rickandmortydata.ui.Padding
import com.antsfamily.rickandmortydata.ui.Rounding
import com.antsfamily.rickandmortydata.ui.common.LoadingView

@Composable
fun CharacterTabContent(viewModel: HomeViewModel, onItemClick: ((Int) -> Unit)? = null) {

    val characters: List<CharacterMainItem> by viewModel.state.mapDistinct { it.characters }
        .observeAsState(emptyList())
    val isLoading: Boolean by viewModel.state.mapDistinct { it.isCharactersLoading }
        .observeAsState(false)

    val scrollState = rememberLazyListState()
    LoadingView(isLoading)
    LazyColumn(
        contentPadding = PaddingValues(Padding.medium),
        verticalArrangement = Arrangement.spacedBy(Padding.regular),
        state = scrollState
    ) {
        itemsIndexed(characters) { _, data ->
            CharacterCard(item = data, onItemClick = onItemClick)
        }
    }
}

@Composable
fun CharacterCard(item: CharacterMainItem, onItemClick: ((Int) -> Unit)? = null) {
    Card(
        modifier = Modifier.wrapContentHeight(unbounded = true),
        shape = RoundedCornerShape(Rounding.regular),
    ) {
        Row(
            modifier = Modifier.clickable { onItemClick?.invoke(item.id) },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberImagePainter(data = item.image),
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier
                    .size(ImageSize.large)
                    .padding(Padding.regular)
                    .clip(CircleShape)
            )
            Column(
                modifier = Modifier
                    .padding(horizontal = Padding.medium, vertical = Padding.small)
                    .fillMaxWidth()
            ) {
                Text(
                    text = item.name,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.h6
                )
                Text(text = item.species, style = MaterialTheme.typography.body2)
                Text(
                    text = item.status,
                    modifier = Modifier.padding(top = Padding.medium),
                    style = MaterialTheme.typography.caption
                )
            }
        }
    }
}
