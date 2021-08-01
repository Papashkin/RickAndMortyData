package com.antsfamily.rickandmortydata.remote

import com.antsfamily.rickandmortydata.data.Character
import com.antsfamily.rickandmortydata.data.CharacterDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RickMortyService {

    @GET("character/")
    suspend fun getCharacters(@Query("page") page: Int): CharacterDTO

    companion object {
        const val BASE_URL = "https://rickandmortyapi.com/api/"
    }
}