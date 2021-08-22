package com.antsfamily.rickandmortydata.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.antsfamily.rickandmortydata.data.remote.Character
import com.antsfamily.rickandmortydata.data.local.CharacterMainItem
import com.antsfamily.rickandmortydata.data.local.TabItem
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
) : ViewModel() {

    private val _characters = MutableLiveData<List<CharacterMainItem>>(emptyList())
    val characters: LiveData<List<CharacterMainItem>>
        get() = _characters

    private val _isCharactersLoadingVisible = MutableLiveData(false)
    val isCharactersLoadingVisible: LiveData<Boolean>
        get() = _isCharactersLoadingVisible

    private val _locations = MutableLiveData<List<Location>>(emptyList())
    val locations: LiveData<List<Location>>
        get() = _locations

    private val _isLocationsLoadingVisible = MutableLiveData(false)
    val isLocationsLoadingVisible: LiveData<Boolean>
        get() = _isLocationsLoadingVisible

    private val _episodes = MutableLiveData<List<Episode>>(emptyList())
    val episodes: LiveData<List<Episode>>
        get() = _episodes

    private val _isEpisodesLoadingVisible = MutableLiveData(false)
    val isEpisodesLoadingVisible: LiveData<Boolean>
        get() = _isEpisodesLoadingVisible

    fun onPageSelected(tab: TabItem) {
        when (tab) {
            TabItem.CHARACTERS -> showCharacters()
            TabItem.LOCATIONS -> showLocations()
            TabItem.EPISODES -> showEpisodes()
        }
    }

    private fun showCharacters() {
        val characters = _characters.value
        if (characters?.isNullOrEmpty() == true) {
            getCharacters()
        } else {
            _characters.postValue(characters)
        }
    }

    private fun getCharacters() {
        showCharactersLoading()
        getCharactersUseCase.invoke(CHARACTER_FIRST_PAGE) { response ->
            _characters.postValue(response.characters.mapToItem())
            hideCharactersLoading()
        }
    }

    private fun showLocations() {
        val locations = _locations.value
        if (locations?.isNullOrEmpty() == true) {
            getLocations()
        } else {
            _locations.postValue(locations)
        }
    }

    private fun getLocations() {
        showLocationsLoading()
        getLocationsUseCase.invoke(CHARACTER_FIRST_PAGE) { response ->
            _locations.postValue(response.locations)
            hideLocationsLoading()
        }
    }

    private fun showEpisodes() {
        val episodes = _episodes.value
        if (episodes?.isNullOrEmpty() == true) {
            getEpisodes()
        } else {
            _episodes.postValue(episodes)
        }
    }

    private fun getEpisodes() {
        showEpisodesLoading()
        getEpisodesUseCase.invoke(CHARACTER_FIRST_PAGE) { response ->
            _episodes.postValue(response.episodes)
            hideEpisodesLoading()
        }
    }

    private fun showCharactersLoading() {
        _isCharactersLoadingVisible.postValue(true)
    }

    private fun hideCharactersLoading() {
        _isCharactersLoadingVisible.postValue(false)
    }

    private fun showLocationsLoading() {
        _isLocationsLoadingVisible.postValue(true)
    }

    private fun hideLocationsLoading() {
        _isLocationsLoadingVisible.postValue(false)
    }

    private fun showEpisodesLoading() {
        _isEpisodesLoadingVisible.postValue(true)
    }

    private fun hideEpisodesLoading() {
        _isEpisodesLoadingVisible.postValue(false)
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
