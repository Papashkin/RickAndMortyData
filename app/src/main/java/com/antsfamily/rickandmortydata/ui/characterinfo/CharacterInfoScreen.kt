package com.antsfamily.rickandmortydata.ui.characterinfo

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Accessibility
import androidx.compose.material.icons.rounded.PinDrop
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import coil.size.Scale
import com.antsfamily.rickandmortydata.R
import com.antsfamily.rickandmortydata.data.remote.Character
import com.antsfamily.rickandmortydata.extensions.mapDistinct
import com.antsfamily.rickandmortydata.presentation.CharacterInfoViewModel
import com.antsfamily.rickandmortydata.ui.ImageSize
import com.antsfamily.rickandmortydata.ui.Padding
import com.antsfamily.rickandmortydata.ui.Rounding

interface CharacterInfoScreen {
    companion object {
        @Composable
        fun Content(viewModel: CharacterInfoViewModel = hiltViewModel(), id: Int) {
            CharacterInfoView(viewModel, id)
        }
    }
}

@Composable
fun CharacterInfoView(viewModel: CharacterInfoViewModel, id: Int) {
    val character: Character? by viewModel.state.mapDistinct { it.character }.observeAsState()
    viewModel.getCharacter(id)
    character?.let {
        SetCharacterView(it)
    }
}

@Composable
fun SetCharacterView(character: Character) {
    ConstraintLayout {
        val (image, divider, card, deadIcon, unknownIcon) = createRefs()
        Image(
            modifier = Modifier
                .fillMaxSize()
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

        Divider(
            thickness = 0.dp,
            modifier = Modifier
                .padding(bottom = 80.dp)
                .constrainAs(divider) {
                    bottom.linkTo(image.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
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
                        text = character.origin.name,
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
                painter = painterResource(id = R.drawable.ic_dead),
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
