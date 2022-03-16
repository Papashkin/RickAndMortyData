package com.antsfamily.rickandmortydata

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.antsfamily.domain.repositories.DataRepository
import com.antsfamily.domain.entity.Episode
import com.antsfamily.domain.entity.Episodes
import com.antsfamily.domain.entity.Info
import com.antsfamily.rickandmortydata.ui.episodes.EpisodesTabViewModel
import com.antsfamily.rickandmortydata.ui.episodes.state.EpisodesState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.lang.RuntimeException

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class EpisodesTabViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var dataRepository: DataRepository

    private lateinit var viewModel: EpisodesTabViewModel

    @Before
    fun setup() = testCoroutineRule.runBlockingTest {
        dataRepository = Mockito.mock(DataRepository::class.java)

        viewModel = EpisodesTabViewModel(dataRepository)
    }

    @Test
    fun `on retry click success`() = testCoroutineRule.runBlockingTest {
        Mockito.`when`(dataRepository.getEpisodes(Mockito.anyInt())).thenReturn(MOCK_EPISODES)

        viewModel.onRetryClick()

        viewModel.state.observeForever {
            assert(it is EpisodesState.DataState)
        }
    }

    @Test
    fun `on retry click error`() = testCoroutineRule.runBlockingTest {
        Mockito.`when`(dataRepository.getEpisodes(Mockito.anyInt())).thenThrow(MOCK_EXCEPTION)

        viewModel.onRetryClick()

        viewModel.state.observeForever {
            assert(it is EpisodesState.ErrorState)
        }
    }

    companion object {
        private val MOCK_EXCEPTION = RuntimeException()
        private val MOCK_INFO = Info(1, 1)
        private val MOCK_EPISODES = Episodes(
            MOCK_INFO,
            listOf(
                Episode(1, "episode 1", "", "", listOf(), "", ""),
                Episode(2, "episode 2", "", "", listOf(), "", "")
            )
        )
    }
}
