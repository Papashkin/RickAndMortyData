package com.antsfamily.rickandmortydata.ui.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import com.antsfamily.rickandmortydata.R
import com.antsfamily.rickandmortydata.ui.Padding
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onComplete: () -> Unit) {
    val scale = remember { Animatable(0f) }
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.8f,
            animationSpec = tween(1000) {
                OvershootInterpolator(2f).getInterpolation(it)
            }
        )
        delay(2000)
        onComplete.invoke()
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = Padding.medium)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_rick_and_morty),
            contentDescription = null,
            Modifier.scale(scale.value)
        )
    }
}