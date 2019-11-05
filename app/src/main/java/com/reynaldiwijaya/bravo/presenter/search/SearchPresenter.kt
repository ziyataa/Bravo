package com.reynaldiwijaya.bravo.presenter.search

import com.reynaldiwijaya.bravo.data.ApiRepository
import com.reynaldiwijaya.bravo.data.DataSource
import com.reynaldiwijaya.bravo.data.model.match.MatchItem
import com.reynaldiwijaya.bravo.presenter.match.MatchView

class SearchPresenter(
    private val matchView: MatchView,
    private val repository: ApiRepository) {

    fun getSearchMatchData(nameMatch : String) {
        repository.getDataSearchMatchByName(nameMatch, object : DataSource.GetDataMatchCallback{
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