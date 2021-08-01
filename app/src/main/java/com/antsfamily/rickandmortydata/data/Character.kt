package com.antsfamily.rickandmortydata.data

import com.google.gson.annotations.SerializedName

data class CharacterDTO(
    @SerializedName("info")
    val info: Info,
    @SerializedName("results")
    val results: List<Character>,
)

data class Info(
    @SerializedName("count")
    val count: Int,
    @SerializedName("pages")
    val pages: Int,
    @SerializedName("next")
    val next: String? = null,
    @SerializedName("prev")
    val prev: String? = null,
)

data class Character(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("species")
    val species: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("origin")
    val origin: Origin,
    @SerializedName("location")
    val location: CharacterLocation,
    @SerializedName("image")
    val image: String,
    @SerializedName("episode")
    val episodes: List<String>,
    val url: String,
    val created: String,
)

data class Origin(val name: String, val url: String)
data class CharacterLocation(val name: String, val url: String)