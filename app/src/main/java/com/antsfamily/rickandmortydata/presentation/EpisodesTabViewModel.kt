package com.antsfamily.rickandmortydata.presentation

import android.util.Log
import com.antsfamily.rickandmortydata.data.remote.Episode
import com.antsfamily.rickandmortydata.data.remote.Episodes
import com.antsfamily.rickandmortydata.domain.useCase.GetEpisodesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EpisodesTabViewModel @Inject constructor(
    private val getEpisodesUseCase: GetEpisodesUseCase
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

    private fun getEpisodes() {
        getEpisodesUseCase(
            params = CHARACTER_FIRST_PAGE,
            onResult = ::handleEpisodesSuccessResult,
            onError = ::handleEpisodesErrorResult
        )
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
        private const val CHARACTER_FIRST_PAGE = 1

        private val TAG = EpisodesTabViewModel::class.java.canonicalName
    }
}