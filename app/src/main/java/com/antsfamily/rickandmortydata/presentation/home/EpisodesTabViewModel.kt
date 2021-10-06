package com.antsfamily.rickandmortydata.presentation.home

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.antsfamily.rickandmortydata.data.DataRepository
import com.antsfamily.rickandmortydata.domain.entity.Episode
import com.antsfamily.rickandmortydata.domain.entity.Episodes
import com.antsfamily.rickandmortydata.presentation.StatefulViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EpisodesTabViewModel @Inject constructor(
    private val repository: DataRepository,
) : StatefulViewModel<EpisodesTabViewModel.State>(State()) {

    data class State(
        val episodes: List<Episode> = emptyList(),
        val isEpisodesVisible: Boolean = false,
        val isEpisodesErrorVisible: Boolean = false,
        val isEpisodesLoading: Boolean = true,
    )

    fun showEpisodes() {
        if (state.value?.episodes?.isNullOrEmpty() == true) {
            getEpisodes()
        }
    }

    private fun getEpisodes() = viewModelScope.launch {
        try {
            val data = repository.getEpisodes(EPISODES_FIRST_PAGE)
            handleEpisodesSuccessResult(data)
        } catch (e: Exception) {
            handleEpisodesErrorResult(e)
        }
    }

    private fun handleEpisodesSuccessResult(data: Episodes) {
        changeState {
            it.copy(
                episodes = data.episodes,
                isEpisodesVisible = true,
                isEpisodesLoading = false,
            )
        }
    }

    private fun handleEpisodesErrorResult(e: Exception) {
        Log.e(TAG, e.message.orEmpty())
        changeState {
            it.copy(
                isEpisodesErrorVisible = true,
                isEpisodesVisible = false,
                isEpisodesLoading = false
            )
        }
    }

    companion object {
        private const val EPISODES_FIRST_PAGE = 1

        private val TAG = EpisodesTabViewModel::class.java.canonicalName
    }
}
