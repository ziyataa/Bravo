package com.reynaldiwijaya.bravo.presenter.match

import com.reynaldiwijaya.bravo.data.ApiRepository
import com.reynaldiwijaya.bravo.data.model.match.MatchItem
import com.reynaldiwijaya.bravo.data.model.match.MatchResponse
import com.reynaldiwijaya.bravo.data.remote.ApiService
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MatchPresenterTest {

    private val leagueId = "4328"

    @Mock
    private lateinit var matchView: MatchView
    @Mock
    private lateinit var repository: ApiRepository
    @Mock
    private lateinit var service: ApiService

    private lateinit var presenter: MatchPresenter

    private val matchItems = mutableListOf<MatchItem>()
    private val matchResponse = MatchResponse(matches = matchItems, match = matchItems)

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        presenter = MatchPresenter(matchView, repository)
    }

    @Test
    fun getDataLastMatchLeague() {
        runBlocking {
            Mockito.`when`(service.getMatchesPastLeagueById(leagueId))
                .thenReturn(matchResponse)

            presenter.getDataLastMatchLeague(leagueId)
        }
    }

    @Test
    fun getDataNextMatchLeague() {
        runBlocking {
            Mockito.`when`(service.getMatchesNextLeagueById(leagueId))
                .thenReturn(matchResponse)

            presenter.getDataNextMatchLeague(leagueId)
        }
    }
}