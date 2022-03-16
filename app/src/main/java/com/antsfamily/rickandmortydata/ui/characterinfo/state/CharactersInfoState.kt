package com.antsfamily.rickandmortydata.ui.characterinfo.state

import com.antsfamily.rickandmortydata.ui.characters.CharacterItem

sealed class CharactersInfoState {
    object LoadingState : CharactersInfoState()
    class DataState(val character: CharacterItem) : CharactersInfoState()
    object ErrorState : CharactersInfoState()
}
