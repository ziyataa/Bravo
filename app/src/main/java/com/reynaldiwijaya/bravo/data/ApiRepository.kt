package com.reynaldiwijaya.bravo.data

import android.content.Context
import com.reynaldiwijaya.bravo.data.local.LocalDataSource
import com.reynaldiwijaya.bravo.data.model.favorite.FavoriteMatch
import com.reynaldiwijaya.bravo.data.model.favorite.FavoriteTeam
import com.reynaldiwijaya.bravo.data.model.league.LeagueItem
import com.reynaldiwijaya.bravo.data.model.match.MatchItem
import com.reynaldiwijaya.bravo.data.model.player.PlayerItem
import com.reynaldiwijaya.bravo.data.model.tableLeague.TableItem
import com.reynaldiwijaya.bravo.data.model.team.TeamItem
import com.reynaldiwijaya.bravo.data.remote.RemoteDataSource
import java.net.URL

class ApiRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : DataSource {

    override fun getDataInLeagueById(id: String, callback: DataSource.GetDataLeaguesCallback) {
        remoteDataSource.getDataInLeagueById(id, object : DataSource.GetDataLeaguesCallback {
            override fun onShowProgressDialog() {
                callback.onShowProgressDialog()
            }

            override fun onHideProgressDialog() {
                callback.onHideProgressDialog()
            }

            override fun onSuccess(data: List<LeagueItem>) {
                callback.onSuccess(data)
            }

            override fun onFailed(errorMessage: String?) {
                callback.onFailed(errorMessage)
            }

        })
    }

    fun doRequest(url: String?): String {
        return URL(url).readText()
    }

    override fun getDataMatchPastLeagueById(
        idLeague: String,
        callback: DataSource.GetDataMatchCallback
    ) {
        remoteDataSource.getDataMatchPastLeagueById(
            idLeague,
            object : DataSource.GetDataMatchCallback {
                override fun onShowProgressDialog() {
                    callback.onShowProgressDialog()
                }

                override fun onHideProgressDialog() {
                    callback.onHideProgressDialog()
                }

                override fun onSuccess(data: List<MatchItem>) {
                    callback.onSuccess(data)
                }

                override fun onFailed(errorMessage: String?) {
                    callback.onFailed(errorMessage)
                }

            })
    }

    override fun getDataMatchNextLeagueById(
        idLeague: String,
        callback: DataSource.GetDataMatchCallback
    ) {
        remoteDataSource.getDataMatchNextLeagueById(
            idLeague,
            object : DataSource.GetDataMatchCallback {
                override fun onShowProgressDialog() {
                    callback.onShowProgressDialog()
                }

                override fun onHideProgressDialog() {
                    callback.onHideProgressDialog()
                }

                override fun onSuccess(data: List<MatchItem>) {
                    callback.onSuccess(data)
                }

                override fun onFailed(errorMessage: String?) {
                    callback.onFailed(errorMessage)
                }
            })
    }

    override fun getDataSearchMatchByName(
        nameMatch: String,
        callback: DataSource.GetDataMatchCallback
    ) {
        remoteDataSource.getDataSearchMatchByName(
            nameMatch,
            object : DataSource.GetDataMatchCallback {
                override fun onShowProgressDialog() {
                    callback.onShowProgressDialog()
                }

                override fun onHideProgressDialog() {
                    callback.onHideProgressDialog()
                }

                override fun onSuccess(data: List<MatchItem>) {
                    callback.onSuccess(data)
                }

                override fun onFailed(errorMessage: String?) {
                    callback.onFailed(errorMessage)
                }

            })
    }

    override fun getDataDetailMatchById(
        idMatch: String,
        callback: DataSource.GetDataMatchCallback
    ) {
        remoteDataSource.getDataDetailMatchById(idMatch, object : DataSource.GetDataMatchCallback {
            override fun onShowProgressDialog() {
                callback.onShowProgressDialog()
            }

            override fun onHideProgressDialog() {
                callback.onHideProgressDialog()
            }

            override fun onSuccess(data: List<MatchItem>) {
                callback.onSuccess(data)
            }

            override fun onFailed(errorMessage: String?) {
                callback.onFailed(errorMessage)
            }

        })
    }

    override fun getDataTeamByLeagueId(leagueId: String, callback: DataSource.GetDataTeamCallback) {
        remoteDataSource.getDataTeamByLeagueId(leagueId, object : DataSource.GetDataTeamCallback{
            override fun onShowProgressDialog() {
                callback.onShowProgressDialog()
            }

            override fun onHideProgressDialog() {
                callback.onHideProgressDialog()
            }

            override fun onSuccess(data: List<TeamItem>) {
                callback.onSuccess(data)
            }

            override fun onFailed(errorMessage: String?) {
                callback.onFailed(errorMessage)
            }

        })
    }

    override fun getDataTableTeamByLeagueId(
        leagueId: String,
        callback: DataSource.GetDataTableTeamCallback
    ) {
        remoteDataSource.getDataTableTeamByLeagueId(leagueId, object : DataSource.GetDataTableTeamCallback{
            override fun onShowProgressDialog() {
                callback.onShowProgressDialog()
            }

            override fun onHideProgressDialog() {
                callback.onHideProgressDialog()
            }

            override fun onSuccess(data: List<TableItem>) {
                callback.onSuccess(data)
            }

            override fun onFailed(errorMessage: String?) {
                callback.onFailed(errorMessage)
            }

        })
    }

    override fun getDataPlayersByTeamId(
        teamId: String,
        callback: DataSource.GetDataPlayersCallback
    ) {
        remoteDataSource.getDataPlayersByTeamId(teamId, object : DataSource.GetDataPlayersCallback{
            override fun onShowProgressDialog() {
                callback.onShowProgressDialog()
            }

            override fun onHideProgressDialog() {
                callback.onHideProgressDialog()
            }

            override fun onSuccess(data: List<PlayerItem>) {
                callback.onSuccess(data)
            }

            override fun onFailed(errorMessage: String?) {
                callback.onFailed(errorMessage)
            }

        })
    }

    override fun getDataSearchTeamByName(
        teamName: String,
        callback: DataSource.GetDataTeamCallback
    ) {
        remoteDataSource.getDataSearchTeamByName(teamName, object : DataSource.GetDataTeamCallback{
            override fun onShowProgressDialog() {
                callback.onShowProgressDialog()
            }

            override fun onHideProgressDialog() {
                callback.onHideProgressDialog()
            }

            override fun onSuccess(data: List<TeamItem>) {
                callback.onSuccess(data)
            }

            override fun onFailed(errorMessage: String?) {
                callback.onFailed(errorMessage)
            }

        })
    }

    override fun addDataMatchToLocal(
        context: Context,
        match: MatchItem,
        callback: DataSource.AddOrRemoveDataMatchToLocalCallback
    ) {
        localDataSource.addDataMatchToLocal(
            context,
            match,
            object : DataSource.AddOrRemoveDataMatchToLocalCallback {
                override fun onSuccessAdd() {
                    callback.onSuccessAdd()
                }

                override fun onSuccessRemove() {}

                override fun onSuccessGetData(data: MatchItem) {}

                override fun onFailed(message: String) {
                    callback.onFailed(message)
                }

            })

    }

    override fun removeDataMatchFromLocal(
        context: Context,
        idMatch: String,
        callback: DataSource.AddOrRemoveDataMatchToLocalCallback
    ) {
        localDataSource.removeDataMatchFromLocal(
            context,
            idMatch,
            object : DataSource.AddOrRemoveDataMatchToLocalCallback {
                override fun onSuccessAdd() {}

                override fun onSuccessRemove() {
                    callback.onSuccessRemove()
                }

                override fun onSuccessGetData(data: MatchItem) {
                    callback.onSuccessGetData(data)
                }

                override fun onFailed(message: String) {
                    callback.onFailed(message)
                }

            })
    }

    override fun getDataMatchFromLocal(
        context: Context,
        callback: DataSource.GetLocalDataMatchCallback
    ) {
        localDataSource.getDataMatchFromLocal(
            context,
            object : DataSource.GetLocalDataMatchCallback {
                override fun onShowProgressDialog() {
                    callback.onShowProgressDialog()
                }

                override fun onHideProgressDialog() {
                    callback.onHideProgressDialog()
                }

                override fun onSuccess(data: List<FavoriteMatch>) {
                    callback.onSuccess(data)
                }

                override fun onFailed(errorMessage: String?) {
                    callback.onFailed(errorMessage)
                }
            })
    }

    override fun getLocalDataMatchById(context: Context, idMatch: String): List<FavoriteMatch> {
        return localDataSource.getLocalDataMatchById(context, idMatch)
    }

    override fun getDataTeamFromLocal(
        context: Context,
        callback: DataSource.GetLocalDataTeamCallback
    ) {
        localDataSource.getDataTeamFromLocal(context, object : DataSource.GetLocalDataTeamCallback{
            override fun onShowProgressDialog() {
                callback.onShowProgressDialog()
            }

            override fun onHideProgressDialog() {
                callback.onHideProgressDialog()
            }

            override fun onSuccess(data: List<FavoriteTeam>) {
                callback.onSuccess(data)
            }

            override fun onFailed(errorMessage: String?) {
                callback.onFailed(errorMessage)
            }

        })
    }

    override fun getLocalDataTeamById(context: Context, idTeam: String): List<FavoriteTeam> {
        return localDataSource.getLocalDataTeamById(context, idTeam)
    }

    override fun addDataTeamToLocal(
        context: Context,
        team: TeamItem,
        callback: DataSource.AddOrRemoveDataTeamToLocalCallback
    ) {
        localDataSource.addDataTeamToLocal(context, team, object : DataSource.AddOrRemoveDataTeamToLocalCallback{
            override fun onSuccessAdd() {
                callback.onSuccessAdd()
            }

            override fun onSuccessRemove() {}

            override fun onSuccessGetData(data: TeamItem) {}

            override fun onFailed(message: String) {
                callback.onFailed(message)
            }

        })
    }

    override fun removeDataTeamFromLocal(
        context: Context,
        idTeam: String,
        callback: DataSource.AddOrRemoveDataTeamToLocalCallback
    ) {
        localDataSource.removeDataTeamFromLocal(context, idTeam, object : DataSource.AddOrRemoveDataTeamToLocalCallback{
            override fun onSuccessAdd() {}

            override fun onSuccessRemove() {
                callback.onSuccessRemove()
            }

            override fun onSuccessGetData(data: TeamItem) {}

            override fun onFailed(message: String) {
                callback.onFailed(message)
            }

        })
    }
}