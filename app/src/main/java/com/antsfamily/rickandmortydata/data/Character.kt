package com.antsfamily.rickandmortydata.data

import com.google.gson.annotations.SerializedName

data class CharacterDTO(
    val info: Info,
    val results: List<Character>,
)

data class Info(
    val count: Int,
    val pages: Int,
    val next: String? = null,
    val prev: String? = null,
)

data class Character(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: Origin,
    val location: CharacterLocation,
    val image: String,
    @SerializedName("episode")
    val episodes: List<String>,
    val url: String,
    val created: String,
)

data class Origin(val name: String, val url: String)
data class CharacterLocation(val name: String, val url: String)