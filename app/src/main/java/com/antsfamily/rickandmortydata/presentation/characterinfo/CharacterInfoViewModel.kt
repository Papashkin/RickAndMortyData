package com.antsfamily.rickandmortydata.presentation.characterinfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.antsfamily.rickandmortydata.data.DataRepository
import com.antsfamily.rickandmortydata.domain.entity.Character
import com.antsfamily.rickandmortydata.presentation.characterinfo.state.CharactersInfoState
import com.antsfamily.rickandmortydata.presentation.home.model.CharacterItem
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
            _state.postValue(CharactersInfoState.DataState(character.mapToItem()))
        } catch (e: Exception) {
            _state.postValue(CharactersInfoState.ErrorState)
        }
    }

    private fun Character.mapToItem(): CharacterItem = with(this) {
        CharacterItem(id, name, status, species, origin.name, image)
    }
}
