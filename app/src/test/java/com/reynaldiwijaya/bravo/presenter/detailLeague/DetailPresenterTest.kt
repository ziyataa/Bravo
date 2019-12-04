package com.reynaldiwijaya.bravo.presenter.detailLeague

import com.reynaldiwijaya.bravo.data.ApiRepository
import com.reynaldiwijaya.bravo.data.model.league.LeagueItem
import com.reynaldiwijaya.bravo.data.model.league.LeagueResponse
import com.reynaldiwijaya.bravo.data.remote.ApiService
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class DetailPresenterTest {

    private val leagueId = "4328"

    @Mock
    private lateinit var view: DetailView

    @Mock
    private lateinit var repository: ApiRepository

    @Mock
    private lateinit var service: ApiService

    private lateinit var presenter: DetailPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        presenter = DetailPresenter(view, repository)
    }

    @Test
    fun getDataInLeague() {
        val leagueItems: MutableList<LeagueItem> = mutableListOf()
        val leagueResponse = LeagueResponse(leagueItems)

        runBlocking {
            Mockito.`when`(service.getDetailLeagueById(leagueId))
                .thenReturn(leagueResponse)

            presenter.getDataInLeague(leagueId)
        }
    }
}