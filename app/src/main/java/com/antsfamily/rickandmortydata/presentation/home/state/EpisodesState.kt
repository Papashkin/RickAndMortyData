package com.antsfamily.rickandmortydata.presentation.home.state

import com.antsfamily.rickandmortydata.presentation.home.model.EpisodeItem

sealed class EpisodesState {
    object LoadingState : EpisodesState()
    class DataState(val episodes: List<EpisodeItem>) : EpisodesState()
    object ErrorState : EpisodesState()
}
