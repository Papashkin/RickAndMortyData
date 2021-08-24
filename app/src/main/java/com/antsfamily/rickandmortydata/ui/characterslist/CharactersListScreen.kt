package com.antsfamily.rickandmortydata.ui.characterslist

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.antsfamily.rickandmortydata.R
import com.antsfamily.rickandmortydata.ui.Padding
import com.antsfamily.rickandmortydata.ui.common.BackButton

interface CharactersListScreen {

    companion object {
        @Composable
        fun Content(navController: NavController) {
            ConstraintLayout {
                val (backButton, title) = createRefs()

                BackButton(
                    onClick = { navController.navigateUp() },
                    Modifier.constrainAs(backButton) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    }
                )

                ContentView(
                    Modifier
                        .constrainAs(title) {
                            top.linkTo(backButton.bottom, Padding.regular)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                )

            }

        }
    }
}

@Composable
fun ContentView(modifier: Modifier) {
    Row(modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Padding.medium),
            text = stringResource(R.string.characters_list_title),
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.h4
        )
    }
}
