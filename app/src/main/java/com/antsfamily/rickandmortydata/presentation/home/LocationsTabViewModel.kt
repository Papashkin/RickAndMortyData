package com.antsfamily.rickandmortydata.presentation.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.antsfamily.rickandmortydata.data.DataRepository
import com.antsfamily.rickandmortydata.domain.entity.Location
import com.antsfamily.rickandmortydata.domain.entity.Locations
import com.antsfamily.rickandmortydata.presentation.home.model.LocationItem
import com.antsfamily.rickandmortydata.presentation.home.state.LocationsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationsTabViewModel @Inject constructor(
    private val repository: DataRepository
) : ViewModel() {

    private val _state = MutableLiveData<LocationsState>(LocationsState.LoadingState)
    val state: LiveData<LocationsState>
        get() = _state

    init {
        getLocations()
    }

    fun onRetryClick() {
        _state.postValue(LocationsState.LoadingState)
        getLocations()
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
        _state.postValue(LocationsState.DataState(data.locations.mapToItems()))
    }

    private fun handleLocationsErrorResult(e: Exception) {
        Log.e(TAG, e.message.orEmpty())
        _state.postValue(LocationsState.ErrorState)
    }

    private fun List<Location>.mapToItems(): List<LocationItem> = this.map { location ->
        with(location) {
            LocationItem(id, name, type, dimension, residents, url, created)
        }
    }

    companion object {
        private const val LOCATIONS_FIRST_PAGE = 1

        private val TAG = LocationsTabViewModel::class.java.canonicalName
    }
}
