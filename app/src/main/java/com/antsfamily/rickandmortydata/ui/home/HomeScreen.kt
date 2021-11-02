package com.antsfamily.rickandmortydata.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.antsfamily.rickandmortydata.R
import com.antsfamily.rickandmortydata.presentation.home.model.TabItem
import com.antsfamily.rickandmortydata.ui.DarkColors
import com.antsfamily.rickandmortydata.ui.LightColors
import com.antsfamily.rickandmortydata.ui.Padding
import com.antsfamily.rickandmortydata.ui.common.SpacerRegular
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

interface HomeScreen {
    companion object {
        @ExperimentalPagerApi
        @Composable
        fun Content(
            onLocationClick: (Int) -> Unit,
            onEpisodeClick: (Int) -> Unit,
            navController: NavController,
        ) {
            HomeView(onLocationClick, onEpisodeClick, navController)
        }
    }
}

@ExperimentalPagerApi
@Composable
fun HomeView(
    onLocationClick: (Int) -> Unit,
    onEpisodeClick: (Int) -> Unit,
    navController: NavController
) {
    val pages = TabItem.values().toList()
    val pagerState = rememberPagerState(pageCount = pages.size)

    Column(
        Modifier.padding(horizontal = Padding.zero, vertical = Padding.regular)
    ) {
        SetTitleIcon()
        SpacerRegular()
        CharacterTab(pages, pagerState)
        TabContent(pagerState, onLocationClick, onEpisodeClick, navController)
    }
}

@Composable
fun getThemeColors(): Colors = if (isSystemInDarkTheme()) DarkColors else LightColors

@Composable
fun SetTitleIcon() {
    Image(
        painter = painterResource(id = R.drawable.ic_rick_and_morty),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Padding.huge),
        contentDescription = null,
        alignment = Alignment.Center
    )
}

@ExperimentalPagerApi
@Composable
fun CharacterTab(pages: List<TabItem>, pagerState: PagerState) {
    val scope = rememberCoroutineScope()
    Column {
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            contentColor = getThemeColors().onBackground,
            backgroundColor = getThemeColors().background,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(Modifier.pagerTabIndicatorOffset(pagerState, tabPositions))
            },
        ) {
            pages.forEachIndexed { index, page ->
                Tab(
                    text = { Text(text = page.title) },
                    selected = pagerState.currentPage == index,
                    onClick = { scope.launch { pagerState.animateScrollToPage(index) } }
                )
            }
        }
    }
}

@ExperimentalPagerApi
@Composable
fun TabContent(
    pagerState: PagerState,
    onLocationClick: (Int) -> Unit,
    onEpisodeClick: (Int) -> Unit,
    navController: NavController
) {
    when (TabItem.values()[pagerState.currentPage]) {
        TabItem.CHARACTERS -> CharacterTabContent(onItemClick = { id ->
                navController.navigate("character/$id")
        })
        TabItem.LOCATIONS -> LocationTabContent(onItemClick = onLocationClick)
        TabItem.EPISODES -> EpisodeTabContent(onItemClick = onEpisodeClick)
    }
}
