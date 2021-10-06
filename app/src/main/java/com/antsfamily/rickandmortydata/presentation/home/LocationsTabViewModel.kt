package com.antsfamily.rickandmortydata.presentation.home

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.antsfamily.rickandmortydata.data.DataRepository
import com.antsfamily.rickandmortydata.domain.entity.Location
import com.antsfamily.rickandmortydata.domain.entity.Locations
import com.antsfamily.rickandmortydata.presentation.StatefulViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationsTabViewModel @Inject constructor(
    private val repository: DataRepository,
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

    private fun getLocations() = viewModelScope.launch {
        try {
            val data = repository.getLocations(LOCATIONS_FIRST_PAGE)
            handleLocationsSuccessResult(data)
        } catch (e: Exception) {
            handleLocationsErrorResult(e)
        }
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
        private const val LOCATIONS_FIRST_PAGE = 1

        private val TAG = LocationsTabViewModel::class.java.canonicalName
    }
}
