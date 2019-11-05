package com.reynaldiwijaya.bravo.data

import com.reynaldiwijaya.bravo.data.model.league.LeagueItem
import com.reynaldiwijaya.bravo.data.model.match.MatchItem
import com.reynaldiwijaya.bravo.data.remote.RemoteDataSource
import java.net.URL

class ApiRepository(private val remoteDataSource: RemoteDataSource) : DataSource {

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
}