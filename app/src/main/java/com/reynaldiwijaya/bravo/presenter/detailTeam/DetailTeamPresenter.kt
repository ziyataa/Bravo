package com.reynaldiwijaya.bravo.presenter.detailTeam

import android.content.Context
import com.reynaldiwijaya.bravo.data.ApiRepository
import com.reynaldiwijaya.bravo.data.DataSource
import com.reynaldiwijaya.bravo.data.model.favorite.FavoriteTeam
import com.reynaldiwijaya.bravo.data.model.team.TeamItem

class DetailTeamPresenter(private val mView: DetailTeamView,
                          private val repository: ApiRepository) {

    fun addDataTeamToLocal(context: Context, team : TeamItem) {
        repository.addDataTeamToLocal(context, team, object : DataSource.AddOrRemoveDataTeamToLocalCallback{
            override fun onSuccessAdd() {
                mView.showSuccessAddToLocal()
            }

            override fun onSuccessRemove() {}

            override fun onSuccessGetData(data: TeamItem) {}

            override fun onFailed(message: String) {
                mView.showFailed(message)
            }

        })
    }

    fun removeDataTeamFromLocal(context: Context, idTeam : String) {
        repository.removeDataTeamFromLocal(context, idTeam, object : DataSource.AddOrRemoveDataTeamToLocalCallback{
            override fun onSuccessAdd() {}

            override fun onSuccessRemove() {
                mView.showSuccessRemoveFromLocal()
            }

            override fun onSuccessGetData(data: TeamItem) {}

            override fun onFailed(message: String) {
                mView.showFailed(message)
            }

        })
    }

    fun getLocalDataTeamById(context: Context, idTeam : String) : List<FavoriteTeam> {
        return repository.getLocalDataTeamById(context, idTeam)
    }
}