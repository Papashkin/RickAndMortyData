package com.antsfamily.rickandmortydata.ui.episodes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.antsfamily.domain.repositories.DataRepository
import com.antsfamily.domain.entity.Episode
import com.antsfamily.domain.entity.Episodes
import com.antsfamily.rickandmortydata.ui.episodes.state.EpisodesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EpisodesTabViewModel @Inject constructor(
    private val repository: DataRepository,
) : ViewModel() {

    private val _state = MutableLiveData<EpisodesState>(EpisodesState.LoadingState)
    val state: LiveData<EpisodesState>
        get() = _state

    init {
        getEpisodes()
    }

    fun onRetryClick() {
        _state.postValue(EpisodesState.LoadingState)
        getEpisodes()
    }

    private fun getEpisodes() = viewModelScope.launch {
        try {
            val data = repository.getEpisodes(EPISODES_FIRST_PAGE)
            handleEpisodesSuccessResult(data)
        } catch (e: Exception) {
            handleEpisodesErrorResult()
        }
    }

    private fun handleEpisodesSuccessResult(data: Episodes) {
        _state.postValue(EpisodesState.DataState(data.episodes.mapToItems()))
    }

    private fun handleEpisodesErrorResult() {
        _state.postValue(EpisodesState.ErrorState)
    }

    private fun List<Episode>.mapToItems(): List<EpisodeItem> = this.map { episode ->
        with(episode) {
            EpisodeItem(id, name, air_date, this.episode, characters, url, created)
        }
    }

    companion object {
        private const val EPISODES_FIRST_PAGE = 1
    }
}
