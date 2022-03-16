package com.antsfamily.domain.remote

import com.antsfamily.domain.entity.Characters
import com.antsfamily.domain.entity.Episodes
import com.antsfamily.domain.entity.Locations
import com.antsfamily.domain.entity.Character

interface RemoteSource {
    suspend fun getCharacters(page: Int): Characters
    suspend fun getLocations(page: Int): Locations
    suspend fun getEpisodes(page: Int): Episodes
    suspend fun getCharacter(characterId: Int): Character
}
