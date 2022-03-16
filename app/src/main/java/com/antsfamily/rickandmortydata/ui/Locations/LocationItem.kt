package com.antsfamily.rickandmortydata.ui.Locations

data class LocationItem(
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String,
    val residents: List<String>,
    val url: String,
    val created: String,
)
