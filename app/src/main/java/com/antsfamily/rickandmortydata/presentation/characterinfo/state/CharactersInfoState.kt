package com.antsfamily.rickandmortydata.presentation.characterinfo.state

import com.antsfamily.rickandmortydata.presentation.characterinfo.model.CharacterInfoItem

sealed class CharactersInfoState {
    object LoadingState : CharactersInfoState()
    class DataState(
        val character: CharacterInfoItem,
        val isListExpanded: Boolean
    ) : CharactersInfoState()

    object ErrorState : CharactersInfoState()
}
