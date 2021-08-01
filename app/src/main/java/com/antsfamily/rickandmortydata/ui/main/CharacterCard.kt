package com.antsfamily.rickandmortydata.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.antsfamily.rickandmortydata.data.Character
import com.antsfamily.rickandmortydata.ui.Elevation
import com.antsfamily.rickandmortydata.ui.ImageSize
import com.antsfamily.rickandmortydata.ui.Padding
import com.antsfamily.rickandmortydata.ui.Rounding

@Composable
fun CharacterCard(item: Character) {
    Card(
        modifier = Modifier
            .wrapContentHeight(unbounded = true),
        shape = RoundedCornerShape(Rounding.regular),
        elevation = Elevation.tiny
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = rememberImagePainter(
                    data = item.image,
                    builder = { transformations(RoundedCornersTransformation(16.0f)) }
                ),
                contentDescription = null,
                modifier = Modifier
                    .size(ImageSize.large)
                    .padding(Padding.regular)
            )
            Column(
                modifier = Modifier
                    .padding(Padding.regular)
                    .fillMaxWidth()
            ) {
                Text(text = item.name, style = typography.h6)
                Text(text = item.species, style = typography.body1)
                Spacer(Modifier.height(Padding.medium))
                Text(text = item.status, style = typography.caption)
            }
        }
    }
}
