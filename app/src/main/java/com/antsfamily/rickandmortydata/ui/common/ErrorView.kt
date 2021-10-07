package com.antsfamily.rickandmortydata.ui.common

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.antsfamily.rickandmortydata.ui.Padding

@Composable
fun ErrorView() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(modifier = Modifier.align(Alignment.Center)) {
            Text(text = "Something went wrong :(")
        }
    }
}

@Composable
fun ErrorWithRetryView(onRetryClick: () -> Unit = {}) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(modifier = Modifier.align(Alignment.Center)) {
            Text(text = "Something went wrong :(")
            Column(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Row(
                    modifier = Modifier.padding(top = Padding.medium),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        modifier = Modifier.align(Alignment.CenterVertically),
                        onClick = onRetryClick) {
                        Text(text = "Retry")
                    }
                }
            }
        }
    }
}
