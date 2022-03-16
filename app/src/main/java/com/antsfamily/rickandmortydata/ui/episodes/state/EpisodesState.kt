package com.antsfamily.rickandmortydata.ui.episodes.state

import com.antsfamily.rickandmortydata.ui.episodes.EpisodeItem

sealed class EpisodesState {
    object LoadingState : EpisodesState()
    class DataState(val episodes: List<EpisodeItem>) : EpisodesState()
    object ErrorState : EpisodesState()
}
