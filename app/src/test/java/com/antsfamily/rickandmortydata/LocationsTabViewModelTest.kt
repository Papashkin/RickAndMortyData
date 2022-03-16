package com.antsfamily.rickandmortydata

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.antsfamily.domain.repositories.DataRepository
import com.antsfamily.domain.entity.Info
import com.antsfamily.domain.entity.Location
import com.antsfamily.domain.entity.Locations
import com.antsfamily.rickandmortydata.ui.Locations.LocationsTabViewModel
import com.antsfamily.rickandmortydata.ui.Locations.state.LocationsState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class LocationsTabViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var dataRepository: DataRepository

    private lateinit var viewModel: LocationsTabViewModel

    @Before
    fun setup() = testCoroutineRule.runBlockingTest {
        dataRepository = Mockito.mock(DataRepository::class.java)

        viewModel = LocationsTabViewModel(dataRepository)
    }

    @Test
    fun `on retry click success`() = testCoroutineRule.runBlockingTest {
        Mockito.`when`(dataRepository.getLocations(Mockito.anyInt())).thenReturn(MOCK_LOCATIONS)

        viewModel.onRetryClick()

        viewModel.state.observeForever {
            assert(it is LocationsState.DataState)
        }
    }

    @Test
    fun `on retry click error`() = testCoroutineRule.runBlockingTest {
        Mockito.`when`(dataRepository.getLocations(Mockito.anyInt())).thenThrow(MOCK_EXCEPTION)

        viewModel.onRetryClick()

        viewModel.state.observeForever {
            assert(it is LocationsState.ErrorState)
        }
    }

    companion object {
        private val MOCK_EXCEPTION = RuntimeException()
        private val MOCK_INFO = Info(1, 1)
        private val MOCK_LOCATIONS = Locations(
            MOCK_INFO, listOf(
            Location(1, "location 1", "", "", listOf(), "", ""),
            Location(2, "location 2", "", "", listOf(), "", "")
        ))
    }
}
