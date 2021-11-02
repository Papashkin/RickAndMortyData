package com.antsfamily.rickandmortydata.data.remote

import com.antsfamily.rickandmortydata.domain.entity.*

interface RemoteSource {
    suspend fun getCharacters(page: Int): Characters
    suspend fun getLocations(page: Int): Locations
    suspend fun getEpisodes(page: Int): Episodes
    suspend fun getCharacter(characterId: Int): Character
    suspend fun getMultipleEpisodes(episodes: String): List<Episode>
}
