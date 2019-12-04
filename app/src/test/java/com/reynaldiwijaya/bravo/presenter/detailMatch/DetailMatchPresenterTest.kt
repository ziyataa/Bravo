package com.reynaldiwijaya.bravo.presenter.detailMatch

import com.reynaldiwijaya.bravo.data.ApiRepository
import com.reynaldiwijaya.bravo.data.model.match.MatchItem
import com.reynaldiwijaya.bravo.data.model.match.MatchResponse
import com.reynaldiwijaya.bravo.data.remote.ApiService
import com.reynaldiwijaya.bravo.presenter.match.MatchView
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class DetailMatchPresenterTest {

    private val leagueId = "4328"

    @Mock
    private lateinit var service: ApiService
    @Mock
    private lateinit var repository: ApiRepository
    @Mock
    private lateinit var mView: MatchView

    private lateinit var presenter: DetailMatchPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        presenter = DetailMatchPresenter(mView, repository)
    }

    @Test
    fun getDataDetailMatch() {
        val matchItems = mutableListOf<MatchItem>()
        val responseMatch = MatchResponse(matchItems, matchItems)

        runBlocking {
            Mockito.`when`(service.getDetailMatchById(leagueId))
                .thenReturn(responseMatch)

            presenter.getDataDetailMatch(leagueId)
        }
    }
}