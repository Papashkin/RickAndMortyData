package com.antsfamily.rickandmortydata.presentation

import android.util.Log
import com.antsfamily.rickandmortydata.data.remote.Location
import com.antsfamily.rickandmortydata.data.remote.Locations
import com.antsfamily.rickandmortydata.domain.useCase.GetLocationsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LocationsTabViewModel @Inject constructor(
    private val getLocationsUseCase: GetLocationsUseCase
) : StatefulViewModel<LocationsTabViewModel.State>(State()) {

    data class State(
        val locations: List<Location> = emptyList(),
        val isLocationsVisible: Boolean = false,
        val isLocationsErrorVisible: Boolean = false,
        val isLocationsLoading: Boolean = true,
    )

    fun showLocations() {
        if (state.value?.locations?.isNullOrEmpty() == true) {
            getLocations()
        }
    }

    private fun getLocations() {
        getLocationsUseCase(
            params = CHARACTER_FIRST_PAGE,
            onResult = ::handleLocationsSuccessResult,
            onError = ::handleLocationsErrorResult
        )
    }

    private fun handleLocationsSuccessResult(data: Locations) {
        changeState {
            it.copy(
                locations = data.locations,
                isLocationsVisible = true,
                isLocationsLoading = false,
            )
        }
    }

    private fun handleLocationsErrorResult(e: Exception) {
        Log.e(TAG, e.message.orEmpty())
        changeState {
            it.copy(
                isLocationsErrorVisible = true,
                isLocationsVisible = false,
                isLocationsLoading = false
            )
        }
    }

    companion object {
        private const val CHARACTER_FIRST_PAGE = 1

        private val TAG = LocationsTabViewModel::class.java.canonicalName
    }
}