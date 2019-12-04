package com.reynaldiwijaya.bravo.presenter.item

import com.google.gson.Gson
import com.reynaldiwijaya.bravo.data.ApiRepository
import com.reynaldiwijaya.bravo.data.model.team.TeamItem
import com.reynaldiwijaya.bravo.data.model.team.TeamResponse
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class ItemPresenterTest {

    @Mock
    private lateinit var view: ItemView
    @Mock
    private lateinit var gson: Gson
    @Mock
    private lateinit var repository: ApiRepository

    private lateinit var presenter: ItemPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        presenter = ItemPresenter(view, repository, gson)
    }

    @Test
    fun getTeamsInLeague() {
        val teams: MutableList<TeamItem> = mutableListOf()
        val response = TeamResponse(teams)
        val league = "English Premiere League"
        val apiResponse = ""

        runBlocking {

            Mockito.`when`(repository.doRequest(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)
            Mockito.`when`(
                gson.fromJson(
                    "",
                    TeamResponse::class.java
                )
            )
                .thenReturn(response)
            presenter.getTeamsByLeagueName(league)
        }
    }
}