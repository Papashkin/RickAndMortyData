package com.antsfamily.rickandmortydata.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.antsfamily.rickandmortydata.data.local.CharacterMainItem
import javax.inject.Inject

class CharacterInfoViewModel @Inject constructor() : ViewModel() {

    private val _characterInfo = MutableLiveData<CharacterMainItem>()
    val characterInfo: LiveData<CharacterMainItem>
        get() = _characterInfo
}