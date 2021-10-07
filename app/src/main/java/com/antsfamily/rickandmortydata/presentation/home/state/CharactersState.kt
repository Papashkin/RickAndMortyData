package com.antsfamily.rickandmortydata.presentation.home.state

import com.antsfamily.rickandmortydata.presentation.home.model.CharacterItem

sealed class CharactersState {
    object LoadingState : CharactersState()
    class DataState(val characters: List<CharacterItem>) : CharactersState()
    object ErrorState : CharactersState()
}
