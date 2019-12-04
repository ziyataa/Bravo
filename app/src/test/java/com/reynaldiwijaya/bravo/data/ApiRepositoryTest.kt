package com.reynaldiwijaya.bravo.data

import org.junit.Test

import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class ApiRepositoryTest {

    @Test
    fun doRequest() {
        val apiRepository = mock(ApiRepository::class.java)
        val url =
            "https://www.thesportsdb.com/api/v1/json/1/search_all_teams.php?l=English%20Premier%20League"
        apiRepository.doRequest(url)
        verify(apiRepository).doRequest(url)
    }

    @Test
    fun doRequestDataLeague() {
        val repository = mock(ApiRepository::class.java)
        val callback = mock(DataSource.GetDataLeaguesCallback::class.java)
        val id = "4328"
        repository.getDataInLeagueById(id, callback)
        verify(repository).getDataInLeagueById(id, callback)
    }
}