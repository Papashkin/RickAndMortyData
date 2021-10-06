package com.antsfamily.rickandmortydata.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Videocam
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import com.antsfamily.rickandmortydata.presentation.home.EpisodesTabViewModel
import com.antsfamily.rickandmortydata.presentation.home.model.EpisodeItem
import com.antsfamily.rickandmortydata.presentation.home.state.EpisodesState
import com.antsfamily.rickandmortydata.ui.ImageSize
import com.antsfamily.rickandmortydata.ui.Padding
import com.antsfamily.rickandmortydata.ui.Rounding
import com.antsfamily.rickandmortydata.ui.common.ErrorWithRetryView
import com.antsfamily.rickandmortydata.ui.common.LoadingView

@Composable
fun EpisodeTabContent(
    viewModel: EpisodesTabViewModel = hiltViewModel(),
    onItemClick: ((Int) -> Unit)? = null
) {
    viewModel.state.observeAsState().value?.let { state ->
        when (state) {
            is EpisodesState.DataState -> EpisodesListView(
                episodes = (state as? EpisodesState.DataState)?.episodes.orEmpty(),
                onItemClick = onItemClick
            )
            EpisodesState.ErrorState -> ErrorWithRetryView(
                onRetryClick = { viewModel.onRetryClick() }
            )
            EpisodesState.LoadingState -> LoadingView()
        }
    }
}

@Composable
fun EpisodesListView(episodes: List<EpisodeItem>, onItemClick: ((Int) -> Unit)? = null) {
    val scrollState = rememberLazyListState()
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
fun EpisodeCard(item: EpisodeItem, onItemClick: ((Int) -> Unit)? = null) {
    Card(
        modifier = Modifier.wrapContentHeight(unbounded = true),
        shape = RoundedCornerShape(Rounding.regular),
    ) {
        Row(
            modifier = Modifier.clickable { onItemClick?.invoke(item.id) },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                Icons.Outlined.Videocam,
                tint = getThemeColors().onSurface,
                contentDescription = null,
                modifier = Modifier
                    .size(ImageSize.medium)
                    .padding(Padding.regular)
            )
            Column(
                modifier = Modifier
                    .padding(horizontal = Padding.medium, vertical = Padding.small)
                    .fillMaxWidth()
            ) {
                Text(
                    text = item.name,
                    maxLines = 2,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.h6
                )
                Text(
                    text = "(${item.episode})",
                    style = MaterialTheme.typography.body1
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
