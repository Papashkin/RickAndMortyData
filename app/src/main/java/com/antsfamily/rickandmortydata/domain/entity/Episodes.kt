package com.antsfamily.rickandmortydata.domain.entity

import com.google.gson.annotations.SerializedName

data class Episodes(
    val info: Info,
    @SerializedName("results")
    val episodes: List<Episode>
)

data class Episode(
    val id: Int,
    val name: String,
    val air_date: String,
    val episode: String,
    val characters: List<String>,
    val url: String,
    val created: String
)
