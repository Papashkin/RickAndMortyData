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
import androidx.compose.ui.text.font.FontWeight
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.antsfamily.rickandmortydata.data.local.CharacterMainItem
import com.antsfamily.rickandmortydata.ui.Elevation
import com.antsfamily.rickandmortydata.ui.ImageSize
import com.antsfamily.rickandmortydata.ui.Padding
import com.antsfamily.rickandmortydata.ui.Rounding

@Composable
fun CharacterCard(item: CharacterMainItem, modifier: Modifier) {
    Card(
        modifier = Modifier.wrapContentHeight(unbounded = true),
        shape = RoundedCornerShape(Rounding.regular),
        elevation = Elevation.tiny,
    ) {
        Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
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
                Text(text = item.name, fontWeight = FontWeight.Bold, style = typography.h6)
                Text(text = item.species, style = typography.body2)
                Spacer(Modifier.height(Padding.medium))
                Text(text = item.status, style = typography.caption)
            }
        }
    }
}

fun getLeftRoundedCornersTransformation() = RoundedCornersTransformation(16.0f, 0.0f, 16.0f, 0.0f)