package com.antsfamily.rickandmortydata.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.antsfamily.rickandmortydata.R
import com.antsfamily.rickandmortydata.data.remote.Episode
import com.antsfamily.rickandmortydata.presentation.HomeViewModel
import com.antsfamily.rickandmortydata.ui.Elevation
import com.antsfamily.rickandmortydata.ui.ImageSize
import com.antsfamily.rickandmortydata.ui.Padding
import com.antsfamily.rickandmortydata.ui.Rounding
import com.antsfamily.rickandmortydata.ui.common.LoadingView

@Composable
fun EpisodeTabContent(viewModel: HomeViewModel, onItemClick: ((Int) -> Unit)? = null) {
    val episodes: List<Episode> by viewModel.episodes.observeAsState(emptyList())
    val isLoading: Boolean by viewModel.isEpisodesLoadingVisible.observeAsState(false)

    val scrollState = rememberLazyListState()
    LoadingView(isLoading)
    LazyColumn(
        contentPadding = PaddingValues(Padding.medium),
        verticalArrangement = Arrangement.spacedBy(Padding.regular),
        state = scrollState
    ) {
        itemsIndexed(episodes) { _, data ->
            EpisodeCard(item = data, onItemClick = onItemClick)
        }
    }
}

@Composable
fun EpisodeCard(item: Episode, onItemClick: ((Int) -> Unit)? = null) {
    Card(
        modifier = Modifier.wrapContentHeight(unbounded = true),
        shape = RoundedCornerShape(Rounding.regular),
        elevation = Elevation.tiny,
    ) {
        Row(
            modifier = Modifier.clickable { onItemClick?.invoke(item.id) },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_movie),
                contentDescription = null,
                modifier = Modifier.size(ImageSize.large).padding(Padding.medium)
            )
            Column(
                modifier = Modifier
                    .padding(horizontal = Padding.medium, vertical = Padding.small)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "${item.name} (${item.episode})" ,
                    maxLines = 3,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.h6
                )
                Text(
                    text = "When: ${item.air_date}",
                    style = MaterialTheme.typography.body2
                )
                Text(
                    modifier = Modifier.padding(top = Padding.medium),
                    text = "Characters: ${item.characters.size}",
                    style = MaterialTheme.typography.caption
                )
            }
        }
    }
}