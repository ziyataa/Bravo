package com.reynaldiwijaya.bravo.data.remote

import android.net.Uri
import com.reynaldiwijaya.bravo.BuildConfig
import com.reynaldiwijaya.bravo.data.DataSource
import com.reynaldiwijaya.bravo.data.model.match.MatchItem
import com.reynaldiwijaya.bravo.data.model.team.TeamItem
import com.reynaldiwijaya.bravo.utils.emptyData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

object RemoteDataSource : DataSource {


    fun getTeams(league: String): String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath("search_all_teams.php")
            .appendQueryParameter("l", league)
            .build()
            .toString()
    }

    override fun getDataInLeagueById(id: String, callback: DataSource.GetDataLeaguesCallback) {
        callback.onShowProgressDialog()
        CoroutineScope(Dispatchers.IO).launch {
            val response = ApiService.getApiService.getTeamsInLeagueById(id)
            withContext(Dispatchers.Main) {
                try {
                    if (response.leagues?.isNotEmpty() == true) {
                        callback.onSuccess(response.leagues)
                    } else {
                        callback.onFailed(emptyData())
                    }
                    callback.onHideProgressDialog()
                }catch (e : Exception) {
                    callback.onFailed(e.message)
                    callback.onHideProgressDialog()
                }
            }
        }
    }

    override fun getDataMatchPastLeagueById(
        idLeague: String,
        callback: DataSource.GetDataMatchCallback
    ) {
        callback.onShowProgressDialog()
        CoroutineScope(Dispatchers.IO).launch {
            val response = ApiService.getApiService.getMatchesPastLeagueById(idLeague)
            withContext(Dispatchers.Main) {
                try {
                    if (response.matches.isNotEmpty()) {
                        callback.onSuccess(response.matches)
                    } else {
                        callback.onFailed(emptyData())
                    }
                    callback.onHideProgressDialog()
                }catch (e : Exception) {
                    callback.onFailed(e.message)
                    callback.onHideProgressDialog()
                }
            }
        }
    }

    override fun getDataMatchNextLeagueById(
        idLeague: String,
        callback: DataSource.GetDataMatchCallback
    ) {
        callback.onShowProgressDialog()
        CoroutineScope(Dispatchers.IO).launch {
            val response = ApiService.getApiService.getMatchesNextLeagueById(idLeague)
            withContext(Dispatchers.Main) {
                try {
                    if (response.matches.isNullOrEmpty()) {
                        callback.onFailed(emptyData())
                    } else {
                        callback.onSuccess(response.matches)
                    }
                    callback.onHideProgressDialog()
                }catch (e : Exception) {
                    callback.onFailed(e.message)
                    callback.onHideProgressDialog()
                }
            }
        }
    }

    override fun getDataSearchMatchByName(nameMatch: String, callback: DataSource.GetDataMatchCallback) {
        callback.onShowProgressDialog()
        CoroutineScope(Dispatchers.IO).launch {
            val response = ApiService.getApiService.getMatchSearchDataByName(nameMatch)
            withContext(Dispatchers.Main) {
                try {
                    if (response.match.isNullOrEmpty()) {
                        callback.onFailed(emptyData())
                    } else {
                        callback.onSuccess(response.match)
                    }
                    callback.onHideProgressDialog()
                }catch (e : Exception) {
                    callback.onFailed(e.message)
                    callback.onHideProgressDialog()
                }
            }
        }
    }

    override fun getDataDetailMatchById(idMatch: String, callback: DataSource.GetDataMatchCallback) {
        callback.onShowProgressDialog()

        var teamHome : TeamItem? = null
        var teamAway : TeamItem? = null
        CoroutineScope(Dispatchers.IO).launch {
            val response = ApiService.getApiService.getDetailMatchById(idMatch)
            withContext(Dispatchers.Main) {
                try {
                    if (response.matches.isNullOrEmpty()) {
                        callback.onFailed(emptyData())
                    } else {
                        val match = response.matches[0]
                        withContext(Dispatchers.IO) {
                            val responseTeamHome = match.idHomeTeam?.let {
                                ApiService.getApiService.getDetailTeamById(
                                    it
                                )
                            }
                            val responseTeamAway = match.idAwayTeam?.let {
                                ApiService.getApiService.getDetailTeamById(
                                    it
                                )
                            }
                            withContext(Dispatchers.Main) {
                                try {
                                    if (responseTeamHome?.teams.isNullOrEmpty() && responseTeamAway?.teams.isNullOrEmpty()) {
                                        // Team home and away null
                                    } else {
                                        teamHome = responseTeamHome?.teams?.get(0)
                                        teamAway = responseTeamAway?.teams?.get(0)
                                    }
                                }catch (e : Exception) {
                                    e.printStackTrace()
                                    callback.onFailed(e.message)
                                }
                            }

                        }
                        val result = arrayListOf<MatchItem>()
                        result.add(match.copy(homeLogo = teamHome?.teamBadge, awayLogo = teamAway?.teamBadge))

                        callback.onSuccess(result)
                    }
                    callback.onHideProgressDialog()
                }catch (e : Exception) {
                    callback.onFailed(e.message)
                    callback.onHideProgressDialog()
                }
            }
        }
    }
}