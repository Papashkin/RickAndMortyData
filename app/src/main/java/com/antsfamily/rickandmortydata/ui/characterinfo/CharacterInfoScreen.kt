package com.antsfamily.rickandmortydata.ui.characterinfo

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Accessibility
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.PinDrop
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import coil.size.Scale
import com.antsfamily.rickandmortydata.R
import com.antsfamily.rickandmortydata.presentation.characterinfo.CharacterInfoViewModel
import com.antsfamily.rickandmortydata.presentation.characterinfo.state.CharactersInfoState
import com.antsfamily.rickandmortydata.presentation.home.model.CharacterItem
import com.antsfamily.rickandmortydata.ui.Alpha
import com.antsfamily.rickandmortydata.ui.ImageSize
import com.antsfamily.rickandmortydata.ui.Padding
import com.antsfamily.rickandmortydata.ui.Rounding
import com.antsfamily.rickandmortydata.ui.common.ErrorView
import com.antsfamily.rickandmortydata.ui.home.getThemeColors

interface CharacterInfoScreen {
    companion object {
        @Composable
        fun Content(
            viewModel: CharacterInfoViewModel = hiltViewModel(),
            id: Int,
            onBackButtonClick: () -> Unit
        ) {
            CharacterInfoView(viewModel, id, onBackButtonClick)
        }
    }
}

@Composable
fun CharacterInfoView(viewModel: CharacterInfoViewModel, id: Int, onBackButtonClick: () -> Unit) {
    viewModel.state.observeAsState().value?.let { state ->
        when (state) {
            is CharactersInfoState.DataState -> SetCharacterView(state.character, onBackButtonClick)
            CharactersInfoState.ErrorState -> ErrorView()
            CharactersInfoState.LoadingState -> {
                // no-op
            }
        }

    }
    viewModel.getCharacter(id)
}

@Composable
fun SetCharacterView(character: CharacterItem, onBackButtonClick: () -> Unit) {
    ConstraintLayout {
        val (backButton, image, divider, card, deadIcon, unknownIcon) = createRefs()

        IconButton(
            onClick = { onBackButtonClick.invoke() },
            modifier = Modifier
                .background(color = Color.White, shape = CircleShape)
                .constrainAs(backButton) {
                    top.linkTo(parent.top, Padding.small)
                    start.linkTo(parent.start, Padding.small)
                },
        ) {
            Icon(Icons.Rounded.Close,
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier.background(Color.White)
            )
        }

        Image(
            modifier = Modifier
                .fillMaxSize()
                .alpha(Alpha.alpha60)
                .constrainAs(image) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            painter = rememberImagePainter(character.image) {
                scale(Scale.FIT)
                crossfade(200)
            },
            contentDescription = null,
            alignment = Alignment.TopCenter
        )

        Spacer(modifier = Modifier
            .padding(bottom = 90.dp)
            .constrainAs(divider) {
                bottom.linkTo(image.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )

        Card(
            modifier = Modifier
                .fillMaxSize()
                .constrainAs(card) {
                    top.linkTo(divider.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            shape = RoundedCornerShape(topStart = Rounding.xlarge, topEnd = Rounding.xlarge),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Padding.regular)
            ) {
                Text(
                    modifier = Modifier.padding(start = Padding.small, top = Padding.huge),
                    text = character.name,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.h4,
                )
                Row(
                    modifier = Modifier.padding(top = Padding.medium),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Rounded.Accessibility,
                        contentDescription = null,
                        tint = getThemeColors().onSurface,
                        modifier = Modifier.size(ImageSize.regular)
                    )
                    Text(
                        modifier = Modifier.padding(start = Padding.regular),
                        text = character.species,
                        style = MaterialTheme.typography.body1,
                    )
                }
                Row(
                    modifier = Modifier.padding(top = Padding.medium),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Rounded.PinDrop,
                        contentDescription = null,
                        tint = getThemeColors().onSurface,
                        modifier = Modifier.size(ImageSize.regular)
                    )
                    Text(
                        modifier = Modifier.padding(start = Padding.regular),
                        text = character.origin,
                        style = MaterialTheme.typography.body1,
                    )
                }
            }
        }

        if (character.status == "Dead") {
            Icon(
                painter = painterResource(id = R.drawable.ic_dead),
                contentDescription = null,
                tint = getThemeColors().onSurface,
                modifier = Modifier
                    .constrainAs(deadIcon) {
                        top.linkTo(card.top, margin = Padding.regular)
                        end.linkTo(card.end, margin = Padding.regular)
                    },
            )
        }

        if (character.status == "unknown") {
            Icon(
                painter = painterResource(id = R.drawable.ic_unknown),
                contentDescription = null,
                tint = getThemeColors().onSurface,
                modifier = Modifier
                    .constrainAs(unknownIcon) {
                        top.linkTo(card.top, margin = Padding.regular)
                        end.linkTo(card.end, margin = Padding.regular)
                    },
            )
        }
    }
}
