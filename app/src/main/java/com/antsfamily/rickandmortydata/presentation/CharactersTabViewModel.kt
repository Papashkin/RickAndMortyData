package com.antsfamily.rickandmortydata.presentation

import android.util.Log
import com.antsfamily.rickandmortydata.data.local.CharacterMainItem
import com.antsfamily.rickandmortydata.data.local.ItemType
import com.antsfamily.rickandmortydata.data.remote.Character
import com.antsfamily.rickandmortydata.data.remote.Characters
import com.antsfamily.rickandmortydata.domain.useCase.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharactersTabViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase
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

    private fun getCharacters() {
        getCharactersUseCase(
            params = CHARACTER_FIRST_PAGE,
            onResult = ::handleCharactersSuccessResult,
            onError = ::handleCharactersErrorResult
        )
    }

    private fun handleCharactersSuccessResult(data: Characters) {
        changeState {
            it.copy(
                characters = data.characters
                    .mapToItem()
                    .plus(CharacterMainItem(-1, "", "", "", "", ItemType.SHOW_MORE_VIEW)),
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
            CharacterMainItem(id, name, status, species, image, ItemType.CONTENT)
        }
    }

    companion object {
        private const val CHARACTER_FIRST_PAGE = 1

        private val TAG = CharactersTabViewModel::class.java.canonicalName
    }
}
