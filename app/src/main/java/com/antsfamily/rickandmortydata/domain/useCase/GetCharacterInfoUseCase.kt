package com.antsfamily.rickandmortydata.domain.useCase

import com.antsfamily.rickandmortydata.data.DataRepository
import com.antsfamily.rickandmortydata.domain.BaseUseCase
import com.antsfamily.rickandmortydata.domain.entity.Character
import javax.inject.Inject

class GetCharacterInfoUseCase @Inject constructor(
    private val repository: DataRepository
) : BaseUseCase<Int, Character>() {

    override suspend fun run(params: Int): Character = repository.getCharacter(params)
}
