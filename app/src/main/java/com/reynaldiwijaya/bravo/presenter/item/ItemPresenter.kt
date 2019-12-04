package com.reynaldiwijaya.bravo.presenter.item

import android.content.Context
import com.google.gson.Gson
import com.reynaldiwijaya.bravo.data.ApiRepository
import com.reynaldiwijaya.bravo.data.DataSource
import com.reynaldiwijaya.bravo.data.model.favorite.FavoriteTeam
import com.reynaldiwijaya.bravo.data.model.team.TeamItem
import com.reynaldiwijaya.bravo.data.model.team.TeamResponse
import com.reynaldiwijaya.bravo.data.remote.RemoteDataSource
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class ItemPresenter(
    private val view: ItemView,
    private val repository: ApiRepository,
    private val gson: Gson
) {

    fun getTeamsByLeagueName(league: String?) {
        view.showLoadingItem()
        doAsync {
            val data = gson.fromJson(
                repository.doRequest(league?.let { RemoteDataSource.getTeams(it) }),
                TeamResponse::class.java
            )

            uiThread {
                view.showTeamList(data.teams)
                view.hideLoadingItem()
            }
        }
    }

    fun getTeamsByLeagueId(leagueId : String?) {
        leagueId?.let {
            repository.getDataTeamByLeagueId(it, object : DataSource.GetDataTeamCallback{
                override fun onShowProgressDialog() {
                    view.showLoadingItem()
                }

                override fun onHideProgressDialog() {
                    view.hideLoadingItem()
                }

                override fun onSuccess(data: List<TeamItem>) {
                    view.showTeamList(data)
                }

                override fun onFailed(errorMessage: String?) {
                    errorMessage?.let { it1 -> view.showErrorItem(it1) }
                }

            })
        }
    }

    fun getLocalDataTeam(context : Context) {
        repository.getDataTeamFromLocal(context, object : DataSource.GetLocalDataTeamCallback{
            override fun onShowProgressDialog() {
                view.showLoadingItem()
            }

            override fun onHideProgressDialog() {
                view.hideLoadingItem()
            }

            override fun onSuccess(data: List<FavoriteTeam>) {
                view.showLocalTeamList(data.map { it.toTeamItem() })
            }

            override fun onFailed(errorMessage: String?) {
                errorMessage?.let { view.showErrorItem(it) }
            }

        })
    }
}