package com.antsfamily.rickandmortydata

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.antsfamily.rickandmortydata.data.DataRepository
import com.antsfamily.rickandmortydata.domain.entity.*
import com.antsfamily.rickandmortydata.presentation.home.CharactersTabViewModel
import com.antsfamily.rickandmortydata.presentation.home.state.CharactersState
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
class CharactersTabViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var dataRepository: DataRepository

    private lateinit var viewModel: CharactersTabViewModel

    @Before
    fun setup() = testCoroutineRule.runBlockingTest {
        dataRepository = Mockito.mock(DataRepository::class.java)

        viewModel = CharactersTabViewModel(dataRepository)
    }

    @Test
    fun `on retry click success`() = testCoroutineRule.runBlockingTest {
        Mockito.`when`(dataRepository.getCharacters(Mockito.anyInt())).thenReturn(MOCK_CHARACTERS)

        viewModel.onRetryClick()

        viewModel.state.observeForever {
            assert(it is CharactersState.DataState)
        }
    }

    @Test
    fun `on retry click error`() = testCoroutineRule.runBlockingTest {
        Mockito.`when`(dataRepository.getCharacters(Mockito.anyInt())).thenThrow(MOCK_EXCEPTION)

        viewModel.onRetryClick()

        viewModel.state.observeForever {
            assert(it is CharactersState.ErrorState)
        }
    }

    companion object {
        private val MOCK_EXCEPTION = RuntimeException()
        private val MOCK_INFO = Info(1, 1)
        private val MOCK_ORIGIN = Origin("origin 1", "http://mock")
        private val MOCK_LOCATION = CharacterLocation("location 1", "http://mock")
        private val MOCK_CHARACTERS = Characters(
            info = MOCK_INFO,
            characters = listOf(
                Character(
                    1,
                    "mock 1",
                    "",
                    "",
                    "",
                    "",
                    MOCK_ORIGIN,
                    MOCK_LOCATION,
                    "",
                    listOf(),
                    "",
                    ""
                ),
                Character(
                    2,
                    "mock 2",
                    "",
                    "",
                    "",
                    "",
                    MOCK_ORIGIN,
                    MOCK_LOCATION,
                    "",
                    listOf(),
                    "",
                    ""
                )
            )
        )
    }
}
