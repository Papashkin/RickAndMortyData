package com.antsfamily.rickandmortydata.presentation.characterinfo.state

import com.antsfamily.rickandmortydata.presentation.home.model.CharacterItem

sealed class CharactersInfoState {
    object LoadingState : CharactersInfoState()
    class DataState(val character: CharacterItem) : CharactersInfoState()
    object ErrorState : CharactersInfoState()
}
