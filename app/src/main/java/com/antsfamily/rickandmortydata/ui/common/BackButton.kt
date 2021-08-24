package com.antsfamily.rickandmortydata.ui.common

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIos
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.antsfamily.rickandmortydata.ui.Padding
import com.antsfamily.rickandmortydata.ui.home.getThemeColors

@Composable
fun BackButton(onClick: () -> Unit, modifier: Modifier) {
    IconButton(
        onClick = { onClick() },
        modifier.padding(Padding.regular)
    ) {
        Icon(
            Icons.Rounded.ArrowBackIos,
            contentDescription = null,
            tint = getThemeColors().onBackground
        )
    }
}
