package com.reynaldiwijaya.bravo.presenter.search

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

class SearchPresenterTest {

    @Mock
    private lateinit var repository: ApiRepository
    @Mock
    private lateinit var mView: MatchView
    @Mock
    private lateinit var service: ApiService

    private lateinit var presenter: SearchPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        presenter = SearchPresenter(mView, repository)
    }

    @Test
    fun getSearchMatchData() {
        val matchItems = mutableListOf<MatchItem>()
        val responseMatch = MatchResponse(matchItems, matchItems)

        runBlocking {
            Mockito.`when`(service.getMatchSearchDataByName(""))
                .thenReturn(responseMatch)

            presenter.getSearchMatchData("")
        }
    }
}