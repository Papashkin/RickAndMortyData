package com.antsfamily.rickandmortydata.domain.entity

import com.google.gson.annotations.SerializedName

data class Characters(
    val info: Info,
    @SerializedName("results")
    val characters: List<Character>,
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
