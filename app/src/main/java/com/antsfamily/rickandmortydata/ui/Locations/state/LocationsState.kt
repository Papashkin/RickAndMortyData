package com.antsfamily.rickandmortydata.ui.Locations.state

import com.antsfamily.rickandmortydata.ui.Locations.LocationItem

sealed class LocationsState {
    object LoadingState : LocationsState()
    class DataState(val locations: List<LocationItem>) : LocationsState()
    object ErrorState : LocationsState()
}
