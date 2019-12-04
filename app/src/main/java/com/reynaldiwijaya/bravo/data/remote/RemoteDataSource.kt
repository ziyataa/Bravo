package com.reynaldiwijaya.bravo.data.remote

import android.content.Context
import android.util.Log
import com.reynaldiwijaya.bravo.BuildConfig
import com.reynaldiwijaya.bravo.data.DataSource
import com.reynaldiwijaya.bravo.data.model.favorite.FavoriteMatch
import com.reynaldiwijaya.bravo.data.model.favorite.FavoriteTeam
import com.reynaldiwijaya.bravo.data.model.match.MatchItem
import com.reynaldiwijaya.bravo.data.model.team.TeamItem
import com.reynaldiwijaya.bravo.utils.emptyData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object RemoteDataSource : DataSource {

    fun getTeams(league: String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/search_all_teams.php?l=" + league
    }

    override fun getDataInLeagueById(id: String, callback: DataSource.GetDataLeaguesCallback) {
        callback.onShowProgressDialog()
        CoroutineScope(Dispatchers.IO).launch {
            val response = ApiService.getApiService.getDetailLeagueById(id)
            withContext(Dispatchers.Main) {
                try {
                    if (response.leagues?.isNotEmpty() == true) {
                        callback.onSuccess(response.leagues)
                    } else {
                        callback.onFailed(emptyData())
                    }
                    callback.onHideProgressDialog()
                } catch (e: Exception) {
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
                } catch (e: Exception) {
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
                } catch (e: Exception) {
                    callback.onFailed(e.message)
                    callback.onHideProgressDialog()
                }
            }
        }
    }

    override fun getDataSearchMatchByName(
        nameMatch: String,
        callback: DataSource.GetDataMatchCallback
    ) {
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
                } catch (e: Exception) {
                    callback.onFailed(e.message)
                    callback.onHideProgressDialog()
                }
            }
        }
    }

    override fun getDataDetailMatchById(
        idMatch: String,
        callback: DataSource.GetDataMatchCallback
    ) {
        callback.onShowProgressDialog()

        var teamHome: TeamItem? = null
        var teamAway: TeamItem? = null
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
                                    if (!(responseTeamHome?.teams.isNullOrEmpty() && responseTeamAway?.teams.isNullOrEmpty())) {
                                        teamHome = responseTeamHome?.teams?.get(0)
                                        teamAway = responseTeamAway?.teams?.get(0)
                                    }
                                } catch (e: Exception) {
                                    e.printStackTrace()
                                    callback.onFailed(e.message)
                                }
                            }

                        }
                        val result = arrayListOf<MatchItem>()
                        result.add(
                            match.copy(
                                homeLogo = teamHome?.teamBadge,
                                awayLogo = teamAway?.teamBadge
                            )
                        )

                        callback.onSuccess(result)
                    }
                    callback.onHideProgressDialog()
                } catch (e: Exception) {
                    callback.onFailed(e.message)
                    callback.onHideProgressDialog()
                }
            }
        }
    }

    override fun getDataTeamByLeagueId(leagueId: String, callback: DataSource.GetDataTeamCallback) {
        callback.onShowProgressDialog()
        CoroutineScope(Dispatchers.IO).launch {
            val response = ApiService.getApiService.getTeamsByLeagueId(leagueId)
            withContext(Dispatchers.Main) {
                try {
                    if (response.teams.isNullOrEmpty()) {
                        callback.onFailed(emptyData())
                    } else {
                        callback.onSuccess(response.teams)
                    }
                    callback.onHideProgressDialog()
                } catch (e: Exception) {
                    callback.onFailed(e.message)
                    callback.onHideProgressDialog()
                }
            }
        }
    }

    override fun getDataTableTeamByLeagueId(
        leagueId: String,
        callback: DataSource.GetDataTableTeamCallback
    ) {
        callback.onShowProgressDialog()
        CoroutineScope(Dispatchers.IO).launch {
            val response = ApiService.getApiService.getTableLeague(leagueId)
            withContext(Dispatchers.Main) {
                try {
                    if (response.table.isNullOrEmpty()) {
                        callback.onFailed(emptyData())
                    } else {
                        callback.onSuccess(response.table)
                    }
                    callback.onHideProgressDialog()
                } catch (e: Exception) {
                    callback.onFailed(e.message)
                    callback.onHideProgressDialog()
                }
            }
        }
    }

    override fun getDataPlayersByTeamId(
        teamId: String,
        callback: DataSource.GetDataPlayersCallback
    ) {
        callback.onShowProgressDialog()
        CoroutineScope(Dispatchers.IO).launch {
            val response = ApiService.getApiService.getPlayersByTeamId(teamId)
            withContext(Dispatchers.Main) {
                try {
                    if (response.players.isNullOrEmpty()) {
                        callback.onFailed(emptyData())
                    } else {
                        callback.onSuccess(response.players)
                    }
                    callback.onHideProgressDialog()
                } catch (e: Exception) {
                    callback.onFailed(e.message)
                    callback.onHideProgressDialog()
                }
            }
        }
    }

    override fun getDataSearchTeamByName(
        teamName: String,
        callback: DataSource.GetDataTeamCallback
    ) {
        callback.onShowProgressDialog()
        CoroutineScope(Dispatchers.IO).launch {
            val response = ApiService.getApiService.getTeamSearchDataByName(teamName)
            withContext(Dispatchers.Main) {
                try {
                    if (response.teams.isNullOrEmpty()) {
                        callback.onFailed(emptyData())
                    } else {
                        callback.onSuccess(response.teams)
                    }
                    callback.onHideProgressDialog()
                } catch (e: Exception) {
                    callback.onFailed(e.message)
                    callback.onHideProgressDialog()
                }
            }
        }
    }

    override fun addDataMatchToLocal(
        context: Context,
        match: MatchItem,
        callback: DataSource.AddOrRemoveDataMatchToLocalCallback
    ) {
        // Do Nothing
    }

    override fun removeDataMatchFromLocal(
        context: Context,
        idMatch: String,
        callback: DataSource.AddOrRemoveDataMatchToLocalCallback
    ) {
        // Do Nothing
    }

    override fun getDataMatchFromLocal(
        context: Context,
        callback: DataSource.GetLocalDataMatchCallback
    ) {
        // Do Nothing
    }

    override fun getLocalDataMatchById(context: Context, idMatch: String): List<FavoriteMatch> {
        return emptyList()
    }

    override fun getDataTeamFromLocal(
        context: Context,
        callback: DataSource.GetLocalDataTeamCallback
    ) {
        // DO NOTHING
    }

    override fun getLocalDataTeamById(context: Context, idTeam: String): List<FavoriteTeam> {
        return emptyList()
    }

    override fun addDataTeamToLocal(
        context: Context,
        team: TeamItem,
        callback: DataSource.AddOrRemoveDataTeamToLocalCallback
    ) {
        // DO NOTHING
    }

    override fun removeDataTeamFromLocal(
        context: Context,
        idTeam: String,
        callback: DataSource.AddOrRemoveDataTeamToLocalCallback
    ) {
        // DO NOTHING
    }

}