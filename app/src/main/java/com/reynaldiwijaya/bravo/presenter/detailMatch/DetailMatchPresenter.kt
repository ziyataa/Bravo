package com.reynaldiwijaya.bravo.presenter.detailMatch

import android.content.Context
import com.reynaldiwijaya.bravo.data.ApiRepository
import com.reynaldiwijaya.bravo.data.DataSource
import com.reynaldiwijaya.bravo.data.model.favorite.FavoriteMatch
import com.reynaldiwijaya.bravo.data.model.match.MatchItem
import com.reynaldiwijaya.bravo.presenter.match.MatchView

class DetailMatchPresenter(
    val matchView: MatchView,
    private val repository: ApiRepository
) {

    fun getDataDetailMatch(idMatch: String) {
        repository.getDataDetailMatchById(idMatch, object : DataSource.GetDataMatchCallback {
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

    fun addDataMatchToLocal(context: Context, matchItem: MatchItem) {
        repository.addDataMatchToLocal(
            context,
            matchItem,
            object : DataSource.AddOrRemoveDataMatchToLocalCallback {
                override fun onSuccessAdd() {
                    matchView.showSuccessAddToLocal()
                }

                override fun onSuccessRemove() {
                    // Do Nothing
                }

                override fun onSuccessGetData(data: MatchItem) {
                    // Do Nothing
                }

                override fun onFailed(message: String) {
                    matchView.showError(message)
                }

            })
    }

    fun removeDataMatchFromLocal(context: Context, idMatch: String) {
        repository.removeDataMatchFromLocal(
            context,
            idMatch,
            object : DataSource.AddOrRemoveDataMatchToLocalCallback {
                override fun onSuccessAdd() {
                    // Do Nothing
                }

                override fun onSuccessRemove() {
                    matchView.showSuccessRemoveFromLocal()
                }

                override fun onSuccessGetData(data: MatchItem) {
                    // Do Nothing
                }

                override fun onFailed(message: String) {
                    matchView.showError(message)
                }

            })
    }

    fun getLocalDataMatchById(context: Context, idMatch: String): List<FavoriteMatch> {
        return repository.getLocalDataMatchById(context, idMatch)
    }
}