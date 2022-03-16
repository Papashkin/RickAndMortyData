package com.antsfamily.domain.repositories

import com.antsfamily.domain.entity.Character
import com.antsfamily.domain.entity.Characters
import com.antsfamily.domain.entity.Episodes
import com.antsfamily.domain.entity.Locations

interface DataRepository {
    suspend fun getCharacters(page: Int): Characters
    suspend fun getLocations(page: Int): Locations
    suspend fun getEpisodes(page: Int): Episodes
    suspend fun getCharacter(characterId: Int): Character
}
