package com.reynaldiwijaya.bravo.presenter.detailLeague

import com.reynaldiwijaya.bravo.data.ApiRepository
import com.reynaldiwijaya.bravo.data.DataSource
import com.reynaldiwijaya.bravo.data.model.league.LeagueItem

class DetailPresenter(private val view : DetailView,
                      private val repository: ApiRepository) {

    fun getDataInLeague(id : String) {
        repository.getDataInLeagueById(id, object : DataSource.GetDataLeaguesCallback{
            override fun onShowProgressDialog() {
                view.showLoading()

            }

            override fun onHideProgressDialog() {
                view.hideLoadimg()
            }

            override fun onSuccess(data: List<LeagueItem>) {
                view.showDetailLeagueData(data)
            }

            override fun onFailed(errorMessage: String?) {
                errorMessage?.let { view.showError(it) }
            }

        })
    }

}