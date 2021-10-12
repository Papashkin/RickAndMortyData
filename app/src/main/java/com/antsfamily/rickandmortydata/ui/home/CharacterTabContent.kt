package com.antsfamily.rickandmortydata.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.antsfamily.rickandmortydata.presentation.home.CharactersTabViewModel
import com.antsfamily.rickandmortydata.presentation.home.model.CharacterItem
import com.antsfamily.rickandmortydata.presentation.home.state.CharactersState
import com.antsfamily.rickandmortydata.ui.Alpha
import com.antsfamily.rickandmortydata.ui.ImageSize
import com.antsfamily.rickandmortydata.ui.Padding
import com.antsfamily.rickandmortydata.ui.Rounding
import com.antsfamily.rickandmortydata.ui.common.ErrorWithRetryView
import com.antsfamily.rickandmortydata.ui.common.LoadingView

@Composable
fun CharacterTabContent(
    viewModel: CharactersTabViewModel = hiltViewModel(),
    onItemClick: ((Int) -> Unit)? = null
) {
    viewModel.state.observeAsState().value?.let { state ->
        when (state) {
            is CharactersState.DataState -> CharactersListView(
                characters = (state as? CharactersState.DataState)?.characters.orEmpty(),
                onItemClick = onItemClick
            )
            CharactersState.ErrorState -> ErrorWithRetryView(
                onRetryClick = { viewModel.onRetryClick() }
            )
            CharactersState.LoadingState -> LoadingView()
        }
    }
}

@Composable
fun CharactersListView(characters: List<CharacterItem>, onItemClick: ((Int) -> Unit)? = null) {
    val scrollState = rememberLazyListState()
    LazyColumn(state = scrollState) {
        itemsIndexed(characters) { _, data ->
            CharacterCard(item = data, onItemClick = onItemClick)
        }
    }
}

@Composable
fun CharacterCard(item: CharacterItem, onItemClick: ((Int) -> Unit)? = null) {
    ConstraintLayout(
        modifier = Modifier
            .wrapContentHeight(unbounded = true)
            .clickable { onItemClick?.invoke(item.id) },
    ) {
        val (mainIcon, title, subtitle, description, divider) = createRefs()

        Image(
            painter = rememberImagePainter(data = item.image),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier
                .size(ImageSize.large)
                .padding(Padding.medium)
                .clip(RoundedCornerShape(Rounding.medium))
                .constrainAs(mainIcon) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                }
        )
        Text(
            text = item.name,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.h6,
            modifier = Modifier.constrainAs(title) {
                top.linkTo(mainIcon.top, Padding.medium)
                start.linkTo(mainIcon.end, Padding.medium)
            }
        )
        Text(
            text = item.species,
            style = MaterialTheme.typography.body2,
            modifier = Modifier.constrainAs(subtitle) {
                top.linkTo(title.bottom, Padding.small)
                start.linkTo(title.start)
            }
        )
        Text(
            text = item.status,
            style = MaterialTheme.typography.caption,
            modifier = Modifier
                .padding(top = Padding.medium)
                .constrainAs(description) {
                    top.linkTo(subtitle.bottom, Padding.small)
                    start.linkTo(title.start)
                }
        )
        Divider(
            thickness = 1.dp,
            color = Color.Gray,
            modifier = Modifier
                .alpha( Alpha.alpha20)
                .constrainAs(divider) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(title.start)
                }
        )
    }
}
