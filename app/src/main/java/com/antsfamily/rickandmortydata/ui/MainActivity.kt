package com.antsfamily.rickandmortydata.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.antsfamily.rickandmortydata.R

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(colors = getThemeColors()) {
                Column(Modifier.padding(horizontal = Padding.medium, vertical = Padding.large)) {
                    SetTitleIcon()
                    Spacer(Modifier.height(Padding.medium))
                    SetTitle()
                }
            }
        }
    }

    @Composable
    fun SetTitleIcon() {
        Image(
            painter = painterResource(id = R.drawable.rick_morty_title_logo),
            modifier = Modifier.fillMaxWidth(),
            contentDescription = null,
            contentScale = ContentScale.FillWidth
        )
    }

    @Composable
    fun SetTitle() {
        Text(
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.main_data),
            style = MaterialTheme.typography.h5,
        )
    }

    @Composable
    fun getThemeColors(): Colors = if (isSystemInDarkTheme()) LightColors else DarkColors
}
