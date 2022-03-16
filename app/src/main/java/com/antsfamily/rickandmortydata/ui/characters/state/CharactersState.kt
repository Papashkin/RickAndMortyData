package com.antsfamily.rickandmortydata.ui.characters.state

import com.antsfamily.rickandmortydata.ui.characters.CharacterItem

sealed class CharactersState {
    object LoadingState : CharactersState()
    class DataState(val characters: List<CharacterItem>) : CharactersState()
    object ErrorState : CharactersState()
}
