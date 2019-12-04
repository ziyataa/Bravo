package com.reynaldiwijaya.bravo.presenter.player

import com.reynaldiwijaya.bravo.data.ApiRepository
import com.reynaldiwijaya.bravo.data.DataSource
import com.reynaldiwijaya.bravo.data.model.player.PlayerItem

class PlayerPresenter(private val mView : PlayerView,
                      private val repository: ApiRepository) {

    fun getDataPlayerByTeamId(teamId : String) {
        repository.getDataPlayersByTeamId(teamId, object : DataSource.GetDataPlayersCallback{
            override fun onShowProgressDialog() {
                mView.showLoading()
            }

            override fun onHideProgressDialog() {
                mView.hideLoading()
            }

            override fun onSuccess(data: List<PlayerItem>) {
                mView.showPlayerList(data)
            }

            override fun onFailed(errorMessage: String?) {
                errorMessage?.let { mView.showError(it) }
            }

        })
    }
}