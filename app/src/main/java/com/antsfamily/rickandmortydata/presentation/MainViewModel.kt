package com.antsfamily.rickandmortydata.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.antsfamily.rickandmortydata.data.Character
import com.antsfamily.rickandmortydata.data.local.CharacterMainItem
import com.antsfamily.rickandmortydata.domain.useCase.GetCharactersUseCase
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {

    private val _characters = MutableLiveData<List<CharacterMainItem>>(emptyList())
    val characters: LiveData<List<CharacterMainItem>>
        get() = _characters

    private val _isLoadingVisible = MutableLiveData(true)
    val isLoadingVisible: LiveData<Boolean>
        get() = _isLoadingVisible

    init {
        getCharacters()
    }

    private fun getCharacters() {
        getCharactersUseCase.invoke(GetCharactersUseCase.Params(CHARACTER_FIRST_PAGE)) { response ->
            _characters.postValue(response.results.map { it.mapToItem() })
            _isLoadingVisible.postValue(false)
        }
    }

    private fun Character.mapToItem(): CharacterMainItem =
        CharacterMainItem(id, name, status, species, image)

    companion object {
        private const val CHARACTER_FIRST_PAGE = 1
    }
}
