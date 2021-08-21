package com.antsfamily.rickandmortydata.remote

import com.antsfamily.rickandmortydata.data.Character
import com.antsfamily.rickandmortydata.data.CharacterDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickMortyService {

    @GET("character/")
    suspend fun getCharacters(@Query("page") page: Int): CharacterDTO

    @GET("character/{id}")
    suspend fun getCharacter(@Path("id") characterId: Int): Character

    companion object {
        const val BASE_URL = "https://rickandmortyapi.com/api/"
    }
}