package com.antsfamily.rickandmortydata.remote

import retrofit2.http.GET

interface RickMortyService {

    @GET("/locations")
    fun getLocations()

    companion object {
        const val BASE_URL = "https://rickandmortyapi.com/api"
    }
}