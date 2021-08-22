package com.antsfamily.rickandmortydata.domain.useCase

import com.antsfamily.rickandmortydata.data.remote.Character
import com.antsfamily.rickandmortydata.domain.BaseUseCase
import com.antsfamily.rickandmortydata.remote.RickMortyService
import javax.inject.Inject

class GetCharacterInfoUseCase @Inject constructor(
    private val service: RickMortyService
) : BaseUseCase<Int, Character>() {

    override suspend fun run(params: Int): Character = service.getCharacter(params)
}