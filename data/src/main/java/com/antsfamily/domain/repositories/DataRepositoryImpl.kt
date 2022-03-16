package com.antsfamily.domain.repositories

import com.antsfamily.domain.entity.Character
import com.antsfamily.domain.entity.Characters
import com.antsfamily.domain.entity.Episodes
import com.antsfamily.domain.entity.Locations
import com.antsfamily.domain.remote.RemoteSource
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
