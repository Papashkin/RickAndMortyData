package com.antsfamily.rickandmortydata.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.antsfamily.rickandmortydata.data.Character
import com.antsfamily.rickandmortydata.domain.useCase.GetCharactersUseCase
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {

    private val _characters = MutableLiveData<List<Character>>()
    val characters: LiveData<List<Character>>
        get() = _characters

    private val _isLoadingVisible = MutableLiveData(true)
    val isLoadingVisible: LiveData<Boolean>
        get() = _isLoadingVisible

    init {
        _characters.postValue(emptyList())
        getCharacters()
    }

    fun onItemClick(item: Character) {

    }

    private fun getCharacters() {
        getCharactersUseCase.invoke(GetCharactersUseCase.Params(CHARACTER_FIRST_PAGE)) {
            _characters.postValue(it.results)
            _isLoadingVisible.postValue(false)
        }
    }

    companion object {
        private const val CHARACTER_FIRST_PAGE = 1
    }
}
