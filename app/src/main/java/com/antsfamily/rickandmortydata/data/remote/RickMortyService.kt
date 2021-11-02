package com.antsfamily.rickandmortydata.data.remote

import com.antsfamily.rickandmortydata.domain.entity.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickMortyService {

    @GET("character/")
    suspend fun getCharacters(@Query("page") page: Int): Characters

    @GET("location/")
    suspend fun getLocations(@Query("page") page: Int): Locations

    @GET("episode/")
    suspend fun getEpisodes(@Query("page") page: Int): Episodes

    @GET("character/{id}")
    suspend fun getCharacter(@Path("id") characterId: Int): Character

    @GET("episode/{id}")
    suspend fun getMultipleEpisodes(@Path("id") idsOfEpisode: String): List<Episode>

    companion object {
        const val BASE_URL = "https://rickandmortyapi.com/api/"
    }
}
