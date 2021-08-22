package com.antsfamily.rickandmortydata.domain.useCase

import com.antsfamily.rickandmortydata.data.remote.Characters
import com.antsfamily.rickandmortydata.domain.BaseUseCase
import com.antsfamily.rickandmortydata.remote.RickMortyService
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val service: RickMortyService
) : BaseUseCase<Int, Characters>() {

    override suspend fun run(params: Int): Characters = service.getCharacters(params)
}