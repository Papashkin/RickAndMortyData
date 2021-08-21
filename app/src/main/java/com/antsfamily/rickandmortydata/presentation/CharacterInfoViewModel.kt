package com.antsfamily.rickandmortydata.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.antsfamily.rickandmortydata.data.Character
import com.antsfamily.rickandmortydata.domain.useCase.GetCharacterInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterInfoViewModel @Inject constructor(
    private val getCharacterInfoUseCase: GetCharacterInfoUseCase
) : ViewModel() {

    private val _character = MutableLiveData<Character>()
    val character: LiveData<Character>
        get() = _character

    fun getCharacter(id: Int) {
        getCharacterInfoUseCase.invoke(id) { character ->
            _character.postValue(character)
        }
    }
}