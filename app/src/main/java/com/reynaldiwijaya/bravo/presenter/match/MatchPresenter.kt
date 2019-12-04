package com.reynaldiwijaya.bravo.presenter.match

import android.content.Context
import com.reynaldiwijaya.bravo.data.ApiRepository
import com.reynaldiwijaya.bravo.data.DataSource
import com.reynaldiwijaya.bravo.data.model.favorite.FavoriteMatch
import com.reynaldiwijaya.bravo.data.model.match.MatchItem

class MatchPresenter(
    val matchView: MatchView,
    private val repository: ApiRepository
) {

    fun getDataLastMatchLeague(idLeague: String) {
        repository.getDataMatchPastLeagueById(idLeague, object : DataSource.GetDataMatchCallback {
            override fun onShowProgressDialog() {
                matchView.showLoading()
            }

            override fun onHideProgressDialog() {
                matchView.hideLoading()
            }

            override fun onSuccess(data: List<MatchItem>) {
                matchView.showMatchData(data)
            }

            override fun onFailed(errorMessage: String?) {
                errorMessage?.let { matchView.showError(it) }
            }

        })
    }

    fun getDataNextMatchLeague(idLeague: String) {
        repository.getDataMatchNextLeagueById(idLeague, object : DataSource.GetDataMatchCallback {
            override fun onShowProgressDialog() {
                matchView.showLoading()
            }

            override fun onHideProgressDialog() {
                matchView.hideLoading()
            }

            override fun onSuccess(data: List<MatchItem>) {

                matchView.showMatchData(data)
            }

            override fun onFailed(errorMessage: String?) {
                errorMessage?.let { matchView.showError(it) }
            }
        })
    }

    fun getLocalDataNextMatch(context: Context) {
        repository.getDataMatchFromLocal(context, object : DataSource.GetLocalDataMatchCallback {
            override fun onShowProgressDialog() {
                matchView.showLoading()
            }

            override fun onHideProgressDialog() {
                matchView.hideLoading()
            }

            override fun onSuccess(data: List<FavoriteMatch>) {
                val result = arrayListOf<FavoriteMatch>()
                data.forEach {
                    if (it.homeScore == null && it.awayScore == null) {
                        result.add(it)
                    }
                }
                matchView.showLocalMatchData(result)
            }

            override fun onFailed(errorMessage: String?) {
                errorMessage?.let { matchView.showError(it) }
            }

        })
    }

    fun getLocalDataLastMatch(context: Context) {
        repository.getDataMatchFromLocal(context, object : DataSource.GetLocalDataMatchCallback {
            override fun onShowProgressDialog() {
                matchView.showLoading()
            }

            override fun onHideProgressDialog() {
                matchView.hideLoading()
            }

            override fun onSuccess(data: List<FavoriteMatch>) {
                val result = arrayListOf<FavoriteMatch>()
                data.forEach {
                    if (it.homeScore != null && it.awayScore != null) {
                        result.add(it)
                    }
                }
                matchView.showLocalMatchData(result)
            }

            override fun onFailed(errorMessage: String?) {
                errorMessage?.let { matchView.showError(it) }
            }

        })
    }
}