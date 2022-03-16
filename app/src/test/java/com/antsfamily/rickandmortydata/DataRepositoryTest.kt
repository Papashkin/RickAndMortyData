package com.antsfamily.rickandmortydata

import com.antsfamily.domain.entity.*
import com.antsfamily.domain.repositories.DataRepository
import com.antsfamily.domain.repositories.DataRepositoryImpl
import com.antsfamily.domain.remote.RemoteSource
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
class DataRepositoryTest {

    @get:Rule
    var testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var remoteSource: RemoteSource

    private lateinit var dataRepository: DataRepository

    @Before
    fun setup() {
        remoteSource = Mockito.mock(RemoteSource::class.java)

        dataRepository = DataRepositoryImpl(remoteSource)
    }

    @Test
    fun `get characters`() = testCoroutineRule.runBlockingTest  {
        Mockito.`when`(remoteSource.getCharacters(Mockito.anyInt())).thenReturn(MOCK_CHARACTERS)

        val data = dataRepository.getCharacters(1)
        assert(data.characters.size == 2)
        assert(data == MOCK_CHARACTERS)
    }

    @Test
    fun `get episodes`() = testCoroutineRule.runBlockingTest  {
        Mockito.`when`(remoteSource.getEpisodes(Mockito.anyInt())).thenReturn(MOCK_EPISODES)

        val data = dataRepository.getEpisodes(1)
        assert(data.episodes.size == 2)
        assert(data == MOCK_EPISODES)
    }

    @Test
    fun `get locations`() = testCoroutineRule.runBlockingTest  {
        Mockito.`when`(remoteSource.getLocations(Mockito.anyInt())).thenReturn(MOCK_LOCATIONS)

        val data = dataRepository.getLocations(1)
        assert(data.locations.size == 2)
        assert(data == MOCK_LOCATIONS)
    }

    @Test
    fun `get character info`() = testCoroutineRule.runBlockingTest  {
        Mockito.`when`(remoteSource.getCharacter(Mockito.anyInt())).thenReturn(MOCK_CHARACTER)

        val data = dataRepository.getCharacter(1)
        assert(data.id == 111)
        assert(data == MOCK_CHARACTER)
    }

    companion object {
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
        private val MOCK_EPISODES = Episodes(MOCK_INFO, listOf(
            Episode(1, "episode 1", "", "", listOf(), "", ""),
            Episode(2, "episode 2", "", "", listOf(), "", "")
        ))
        private val MOCK_LOCATIONS = Locations(MOCK_INFO, listOf(
            Location(1, "location 1", "", "", listOf(), "", ""),
            Location(2, "location 2", "", "", listOf(), "", "")
        ))
        private val MOCK_CHARACTER = Character(
            111,
            "mock 111",
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
    }
}
