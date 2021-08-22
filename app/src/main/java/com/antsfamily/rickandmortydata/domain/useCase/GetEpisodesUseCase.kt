package com.antsfamily.rickandmortydata.domain.useCase

import com.antsfamily.rickandmortydata.data.remote.Episodes
import com.antsfamily.rickandmortydata.domain.BaseUseCase
import com.antsfamily.rickandmortydata.remote.RickMortyService
import javax.inject.Inject

class GetEpisodesUseCase @Inject constructor(
    private val service: RickMortyService
) : BaseUseCase<Int, Episodes>() {
    override suspend fun run(params: Int): Episodes = service.getEpisodes(params)
}
