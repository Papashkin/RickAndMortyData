package com.antsfamily.rickandmortydata.domain.useCase

import com.antsfamily.rickandmortydata.data.remote.Locations
import com.antsfamily.rickandmortydata.domain.BaseUseCase
import com.antsfamily.rickandmortydata.remote.RickMortyService
import javax.inject.Inject

class GetLocationsUseCase @Inject constructor(
    private val service: RickMortyService
) : BaseUseCase<Int, Locations>() {

    override suspend fun run(params: Int): Locations = service.getLocations(params)
}