package com.antsfamily.rickandmortydata.domain.useCase

import com.antsfamily.rickandmortydata.data.CharacterDTO
import com.antsfamily.rickandmortydata.domain.BaseUseCase
import com.antsfamily.rickandmortydata.remote.RickMortyService
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val service: RickMortyService
) : BaseUseCase<GetCharactersUseCase.Params, CharacterDTO>() {

    override suspend fun run(params: Params): CharacterDTO = service.getCharacters(params.page)

    data class Params(val page: Int)
}