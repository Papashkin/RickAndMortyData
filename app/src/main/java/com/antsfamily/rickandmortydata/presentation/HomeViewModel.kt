package com.antsfamily.rickandmortydata.presentation

import com.antsfamily.rickandmortydata.data.local.CharacterMainItem
import com.antsfamily.rickandmortydata.data.local.TabItem
import com.antsfamily.rickandmortydata.data.remote.Character
import com.antsfamily.rickandmortydata.data.remote.Episode
import com.antsfamily.rickandmortydata.data.remote.Location
import com.antsfamily.rickandmortydata.domain.useCase.GetCharactersUseCase
import com.antsfamily.rickandmortydata.domain.useCase.GetEpisodesUseCase
import com.antsfamily.rickandmortydata.domain.useCase.GetLocationsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val getLocationsUseCase: GetLocationsUseCase,
    private val getEpisodesUseCase: GetEpisodesUseCase
) : StatefulViewModel<HomeViewModel.State>(State()) {

    data class State(
        val characters: List<CharacterMainItem> = emptyList(),
        val locations: List<Location> = emptyList(),
        val episodes: List<Episode> = emptyList(),
        val isCharactersLoading: Boolean = false,
        val isLocationsLoading: Boolean = false,
        val isEpisodesLoading: Boolean = false,
    )

    fun onPageSelected(tab: TabItem) {
        when (tab) {
            TabItem.CHARACTERS -> showCharacters()
            TabItem.LOCATIONS -> showLocations()
            TabItem.EPISODES -> showEpisodes()
        }
    }

    private fun showCharacters() {
        if (state.value?.characters?.isNullOrEmpty() == true) {
            getCharacters()
        }
    }

    private fun getCharacters() {
        showCharactersLoading()
        getCharactersUseCase.invoke(CHARACTER_FIRST_PAGE) { response ->
            changeState { it.copy(characters = response.characters.mapToItem()) }
            hideCharactersLoading()
        }
    }

    private fun showLocations() {
        if (state.value?.locations?.isNullOrEmpty() == true) {
            getLocations()
        }
    }

    private fun getLocations() {
        showLocationsLoading()
        getLocationsUseCase.invoke(CHARACTER_FIRST_PAGE) { response ->
            changeState { it.copy(locations = response.locations) }
            hideLocationsLoading()
        }
    }

    private fun showEpisodes() {
        if (state.value?.episodes?.isNullOrEmpty() == true) {
            getEpisodes()
        }
    }

    private fun getEpisodes() {
        showEpisodesLoading()
        getEpisodesUseCase.invoke(CHARACTER_FIRST_PAGE) { response ->
            changeState { it.copy(episodes = response.episodes) }
            hideEpisodesLoading()
        }
    }

    private fun showCharactersLoading() {
        changeState { it.copy(isCharactersLoading = true) }
    }

    private fun hideCharactersLoading() {
        changeState { it.copy(isCharactersLoading = false) }
    }

    private fun showLocationsLoading() {
        changeState { it.copy(isLocationsLoading = true) }
    }

    private fun hideLocationsLoading() {
        changeState { it.copy(isLocationsLoading = false) }
    }

    private fun showEpisodesLoading() {
        changeState { it.copy(isEpisodesLoading = true) }
    }

    private fun hideEpisodesLoading() {
        changeState { it.copy(isEpisodesLoading= false) }
    }

    private fun List<Character>.mapToItem(): List<CharacterMainItem> = this.map { character ->
        with(character) {
            CharacterMainItem(id, name, status, species, image)
        }
    }

    companion object {
        private const val CHARACTER_FIRST_PAGE = 1
    }
}
