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
import com.antsfamily.rickandmortydata.data.remote.Location
import com.antsfamily.rickandmortydata.presentation.HomeViewModel
import com.antsfamily.rickandmortydata.ui.Elevation
import com.antsfamily.rickandmortydata.ui.ImageSize
import com.antsfamily.rickandmortydata.ui.Padding
import com.antsfamily.rickandmortydata.ui.Rounding
import com.antsfamily.rickandmortydata.ui.common.LoadingView
import com.antsfamily.rickandmortydata.ui.common.SpacerMedium

@Composable
fun LocationTabContent(viewModel: HomeViewModel, onItemClick: ((Int) -> Unit)? = null) {
    val locations: List<Location> by viewModel.locations.observeAsState(emptyList())
    val isLoading: Boolean by viewModel.isLocationsLoadingVisible.observeAsState(false)

    val scrollState = rememberLazyListState()
    LoadingView(isLoading)
    LazyColumn(
        contentPadding = PaddingValues(Padding.medium),
        verticalArrangement = Arrangement.spacedBy(Padding.regular),
        state = scrollState
    ) {
        itemsIndexed(locations) { _, data ->
            LocationCard(item = data, onItemClick = onItemClick)
        }
    }
}

@Composable
fun LocationCard(item: Location, onItemClick: ((Int) -> Unit)? = null) {
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
                painter = painterResource(id = R.drawable.ic_planet),
                contentDescription = null,
                modifier = Modifier
                    .size(ImageSize.large)
                    .padding(Padding.medium)
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
                Text(
                    text = item.type,
                    style = MaterialTheme.typography.body2
                )
                SpacerMedium()
                Text(
                    text = item.dimension,
                    style = MaterialTheme.typography.caption
                )
                Text(
                    text = "Residents: ${item.residents.size}",
                    style = MaterialTheme.typography.caption
                )
            }
        }
    }
}