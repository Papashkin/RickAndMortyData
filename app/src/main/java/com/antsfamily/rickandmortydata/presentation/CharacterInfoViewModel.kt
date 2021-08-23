package com.antsfamily.rickandmortydata.presentation

import com.antsfamily.rickandmortydata.data.remote.Character
import com.antsfamily.rickandmortydata.domain.useCase.GetCharacterInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterInfoViewModel @Inject constructor(
    private val getCharacterInfoUseCase: GetCharacterInfoUseCase
) : StatefulViewModel<CharacterInfoViewModel.State>(State()) {

    data class State(
        val character: Character? = null
    )

    fun getCharacter(id: Int) {
        getCharacterInfoUseCase(
            params = id,
            onResult = {
                changeState { state -> state.copy(character = it) }
            },
            onError = {
                // no-op
            }
        )
    }
}