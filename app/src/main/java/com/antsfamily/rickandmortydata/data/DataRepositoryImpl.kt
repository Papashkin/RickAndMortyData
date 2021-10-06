package com.antsfamily.rickandmortydata.data

import com.antsfamily.rickandmortydata.data.remote.RemoteSource
import com.antsfamily.rickandmortydata.domain.entity.Character
import com.antsfamily.rickandmortydata.domain.entity.Characters
import com.antsfamily.rickandmortydata.domain.entity.Episodes
import com.antsfamily.rickandmortydata.domain.entity.Locations
import javax.inject.Inject

class DataRepositoryImpl @Inject constructor(
    private val source: RemoteSource
) : DataRepository {
    override suspend fun getCharacters(page: Int): Characters = source.getCharacters(page)

    override suspend fun getLocations(page: Int): Locations = source.getLocations(page)

    override suspend fun getEpisodes(page: Int): Episodes = source.getEpisodes(page)

    override suspend fun getCharacter(characterId: Int): Character =
        source.getCharacter(characterId)

}
