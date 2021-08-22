package com.antsfamily.rickandmortydata.data.remote

import com.google.gson.annotations.SerializedName

data class Locations(
    val info: Info,
    @SerializedName("results")
    val locations: List<Location>
)

data class Location(
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String,
    val residents: List<String>,
    val url: String,
    val created: String,
)