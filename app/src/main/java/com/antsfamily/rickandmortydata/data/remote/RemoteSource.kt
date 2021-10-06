package com.antsfamily.rickandmortydata.data.remote

import com.antsfamily.rickandmortydata.domain.entity.Characters
import com.antsfamily.rickandmortydata.domain.entity.Episodes
import com.antsfamily.rickandmortydata.domain.entity.Locations
import com.antsfamily.rickandmortydata.domain.entity.Character

interface RemoteSource {
    suspend fun getCharacters(page: Int): Characters
    suspend fun getLocations(page: Int): Locations
    suspend fun getEpisodes(page: Int): Episodes
    suspend fun getCharacter(characterId: Int): Character
}
