package com.antsfamily.rickandmortydata.data.local

data class CharacterMainItem(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val image: String,
    val type: ItemType,
)
