package com.antsfamily.rickandmortydata.ui.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.antsfamily.rickandmortydata.ui.Padding

@Composable
fun TextButton(text: String, onClick: (() -> Unit) = {}) {
    TextButton(
        onClick = {
            onClick()
        },
        Modifier
            .fillMaxWidth()
            .padding(vertical = Padding.medium, horizontal = Padding.large)
    ) {
        Text(text = text, Modifier.align(Alignment.CenterVertically))
    }
}
