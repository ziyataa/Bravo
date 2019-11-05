package com.reynaldiwijaya.bravo.presenter.item

import com.google.gson.Gson
import com.reynaldiwijaya.bravo.data.ApiRepository
import com.reynaldiwijaya.bravo.data.model.team.TeamResponse
import com.reynaldiwijaya.bravo.data.remote.RemoteDataSource
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class ItemPresenter(private val view : ItemView,
                    private val repository: ApiRepository,
                    private val gson : Gson) {

    fun getTeamsInLeague(league : String?) {
        view.showLoadingItem()
        doAsync {
            val data = gson.fromJson(repository
                .doRequest(league?.let { RemoteDataSource.getTeams(it) }),
                TeamResponse::class.java
            )

            uiThread {
                view.hideLoadingItem()
                view.showTeamList(data.teams)
            }
        }
    }


}