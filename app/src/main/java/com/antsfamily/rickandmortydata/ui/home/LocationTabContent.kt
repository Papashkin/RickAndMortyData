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
import androidx.hilt.navigation.compose.hiltViewModel
import com.antsfamily.rickandmortydata.R
import com.antsfamily.rickandmortydata.data.remote.Location
import com.antsfamily.rickandmortydata.extensions.mapDistinct
import com.antsfamily.rickandmortydata.presentation.LocationsTabViewModel
import com.antsfamily.rickandmortydata.ui.ImageSize
import com.antsfamily.rickandmortydata.ui.Padding
import com.antsfamily.rickandmortydata.ui.Rounding
import com.antsfamily.rickandmortydata.ui.common.ErrorView
import com.antsfamily.rickandmortydata.ui.common.LoadingView

@Composable
fun LocationTabContent(
    viewModel: LocationsTabViewModel = hiltViewModel(),
    onItemClick: ((Int) -> Unit)? = null
) {
    val locations: List<Location> by viewModel.state.mapDistinct { it.locations }
        .observeAsState(emptyList())
    val isLocationsVisible: Boolean by viewModel.state.mapDistinct { it.isLocationsVisible }
        .observeAsState(false)
    val isErrorVisible: Boolean by viewModel.state.mapDistinct { it.isLocationsErrorVisible }
        .observeAsState(false)
    val isLoading: Boolean by viewModel.state.mapDistinct { it.isLocationsLoading }
        .observeAsState(true)

    viewModel.showLocations()

    if (isLoading) LoadingView()
    if (isLocationsVisible) LocationsListView(locations, onItemClick)
    if (isErrorVisible) ErrorView()
}

@Composable
fun LocationsListView(locations: List<Location>, onItemClick: ((Int) -> Unit)? = null) {
    val scrollState = rememberLazyListState()
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
        modifier = Modifier.wrapContentHeight(unbounded = true).fillMaxWidth(),
        shape = RoundedCornerShape(Rounding.regular),
    ) {
        Column(
            modifier = Modifier.clickable { onItemClick?.invoke(item.id) },
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier.padding(horizontal = Padding.medium, vertical = Padding.regular),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_planet),
                    contentDescription = null,
                    modifier = Modifier
                        .size(ImageSize.regular)
                        .padding(end = Padding.small)
                )
                Text(
                    text = item.name,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.h6
                )
            }
            Text(
                modifier = Modifier.padding(vertical = Padding.tiny, horizontal = Padding.medium),
                text = item.type,
                style = MaterialTheme.typography.body2
            )
            Text(
                text = item.dimension,
                modifier = Modifier.padding(
                    start = Padding.medium,
                    end = Padding.medium,
                    top = Padding.medium
                ),
                style = MaterialTheme.typography.caption
            )
            Text(
                modifier = Modifier.padding(
                    start = Padding.medium,
                    end = Padding.medium,
                    bottom = Padding.medium
                ),
                text = "Residents: ${item.residents.size}",
                style = MaterialTheme.typography.caption
            )
        }
    }
}
