package com.antsfamily.rickandmortydata.presentation.home.state

import com.antsfamily.rickandmortydata.presentation.home.model.LocationItem

sealed class LocationsState {
    object LoadingState : LocationsState()
    class DataState(val locations: List<LocationItem>) : LocationsState()
    object ErrorState : LocationsState()
}
