package com.antsfamily.rickandmortydata.presentation.home

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.antsfamily.rickandmortydata.data.DataRepository
import com.antsfamily.rickandmortydata.domain.entity.Character
import com.antsfamily.rickandmortydata.domain.entity.Characters
import com.antsfamily.rickandmortydata.presentation.StatefulViewModel
import com.antsfamily.rickandmortydata.presentation.home.model.CharacterMainItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersTabViewModel @Inject constructor(
    private val repository: DataRepository,
) : StatefulViewModel<CharactersTabViewModel.State>(State()) {

    data class State(
        val characters: List<CharacterMainItem> = emptyList(),
        val isCharactersVisible: Boolean = false,
        val isCharactersErrorVisible: Boolean = false,
        val isCharactersLoading: Boolean = true,
    )

    fun showCharacters() {
        if (state.value?.characters?.isNullOrEmpty() == true) {
            getCharacters()
        }
    }

    private fun getCharacters() = viewModelScope.launch {
        try{
            val data = repository.getCharacters(CHARACTERS_FIRST_PAGE)
            handleCharactersSuccessResult(data)
        } catch (e: Exception) {
            handleCharactersErrorResult(e)
        }
    }

    private fun handleCharactersSuccessResult(data: Characters) {
        changeState {
            it.copy(
                characters = data.characters.mapToItem(),
                isCharactersVisible = true,
                isCharactersErrorVisible = false,
                isCharactersLoading = false,
            )
        }
    }

    private fun handleCharactersErrorResult(e: Exception) {
        Log.e(TAG, e.message.orEmpty())
        changeState {
            it.copy(
                isCharactersVisible = false,
                isCharactersErrorVisible = true,
                isCharactersLoading = false,
            )
        }
    }

    private fun List<Character>.mapToItem(): List<CharacterMainItem> = this.map { character ->
        with(character) {
            CharacterMainItem(id, name, status, species, image)
        }
    }

    companion object {
        private const val CHARACTERS_FIRST_PAGE = 1

        private val TAG = CharactersTabViewModel::class.java.canonicalName
    }
}
