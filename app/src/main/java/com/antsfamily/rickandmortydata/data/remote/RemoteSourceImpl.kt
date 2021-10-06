package com.antsfamily.rickandmortydata.data.remote

import com.antsfamily.rickandmortydata.domain.entity.Characters
import com.antsfamily.rickandmortydata.domain.entity.Locations
import com.antsfamily.rickandmortydata.domain.entity.Episodes
import com.antsfamily.rickandmortydata.domain.entity.Character
import javax.inject.Inject

class RemoteSourceImpl @Inject constructor(
    private val service: RickMortyService
) : RemoteSource {
    override suspend fun getCharacters(page: Int): Characters = service.getCharacters(page)

    override suspend fun getLocations(page: Int): Locations = service.getLocations(page)

    override suspend fun getEpisodes(page: Int): Episodes = service.getEpisodes(page)

    override suspend fun getCharacter(characterId: Int): Character =
        service.getCharacter(characterId)
}
