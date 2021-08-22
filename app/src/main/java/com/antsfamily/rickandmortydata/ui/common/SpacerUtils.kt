package com.antsfamily.rickandmortydata.ui.common

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.antsfamily.rickandmortydata.ui.Padding

@Composable
fun SpacerRegular() {
    Spacer(Modifier.height(Padding.regular))
}

@Composable
fun SpacerMedium() {
    Spacer(Modifier.height(Padding.medium))
}
