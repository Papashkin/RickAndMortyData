package com.antsfamily.rickandmortydata.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.antsfamily.rickandmortydata.data.DataRepository
import com.antsfamily.rickandmortydata.domain.entity.Character
import com.antsfamily.rickandmortydata.domain.entity.Characters
import com.antsfamily.rickandmortydata.presentation.home.model.CharacterItem
import com.antsfamily.rickandmortydata.presentation.home.state.CharactersState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersTabViewModel @Inject constructor(
    private val repository: DataRepository,
) : ViewModel() {

    private val _state = MutableLiveData<CharactersState>(CharactersState.LoadingState)
    val state: LiveData<CharactersState>
        get() = _state

    init {
        getCharacters()
    }

    fun onRetryClick() {
        _state.postValue(CharactersState.LoadingState)
        getCharacters()
    }

    private fun getCharacters() = viewModelScope.launch {
        try{
            val data = repository.getCharacters(CHARACTERS_FIRST_PAGE)
            handleCharactersSuccessResult(data)
        } catch (e: Exception) {
            handleCharactersErrorResult()
        }
    }

    private fun handleCharactersSuccessResult(data: Characters) {
        _state.postValue(CharactersState.DataState(data.characters.mapToItems()))
    }

    private fun handleCharactersErrorResult() {
        _state.postValue(CharactersState.ErrorState)
    }

    private fun List<Character>.mapToItems(): List<CharacterItem> = this.map { character ->
        with(character) {
            CharacterItem(id, name, status, species, origin.name, image)
        }
    }

    companion object {
        private const val CHARACTERS_FIRST_PAGE = 1
    }
}
