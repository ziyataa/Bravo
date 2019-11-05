package com.reynaldiwijaya.bravo.presenter.match

import com.reynaldiwijaya.bravo.data.ApiRepository
import com.reynaldiwijaya.bravo.data.DataSource
import com.reynaldiwijaya.bravo.data.model.match.MatchItem
import com.reynaldiwijaya.bravo.data.model.team.TeamItem

class MatchPresenter(val matchView : MatchView,
                     private val repository: ApiRepository) {

    fun getDataLastMatchLeague(idLeague : String) {
        repository.getDataMatchPastLeagueById(idLeague, object : DataSource.GetDataMatchCallback{
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

    fun getDataNextMatchLeague(idLeague : String) {
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
}