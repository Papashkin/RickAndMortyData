package com.antsfamily.rickandmortydata.presentation.characterinfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.antsfamily.rickandmortydata.data.DataRepository
import com.antsfamily.rickandmortydata.domain.entity.Character
import com.antsfamily.rickandmortydata.domain.entity.Episode
import com.antsfamily.rickandmortydata.presentation.characterinfo.model.CharacterInfoItem
import com.antsfamily.rickandmortydata.presentation.characterinfo.state.CharactersInfoState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterInfoViewModel @Inject constructor(
    private val repository: DataRepository
) : ViewModel() {

    private val _state = MutableLiveData<CharactersInfoState>(CharactersInfoState.LoadingState)
    val state: LiveData<CharactersInfoState>
        get() = _state

    fun getCharacter(id: Int) = viewModelScope.launch {
        try {
            val character = repository.getCharacter(id)
            val idsOfEpisodes = character.episodes
                .map { it.split(DELIMITER).last().toIntOrNull() ?: 0 }
                .joinToString(SEPARATOR)
            val episodes = repository.getMultipleEpisodes(idsOfEpisodes)
            _state.postValue(CharactersInfoState.DataState(character.mapToItem(episodes), false))
        } catch (e: Exception) {
            _state.postValue(CharactersInfoState.ErrorState)
        }
    }

    private fun Character.mapToItem(episodes: List<Episode>): CharacterInfoItem = with(this) {
        CharacterInfoItem(
            id,
            name,
            status,
            species,
            type,
            gender,
            origin.name,
            origin.url,
            location.name,
            location.url,
            image,
            episodes = episodes.map { it.episode + ". " + it.name }
        )
    }

    companion object {
        private const val SEPARATOR = ","
        private const val DELIMITER = "/"
    }
}
