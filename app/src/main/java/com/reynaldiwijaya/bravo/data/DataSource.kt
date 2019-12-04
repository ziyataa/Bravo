package com.reynaldiwijaya.bravo.data

import android.content.Context
import com.reynaldiwijaya.bravo.data.model.favorite.FavoriteMatch
import com.reynaldiwijaya.bravo.data.model.favorite.FavoriteTeam
import com.reynaldiwijaya.bravo.data.model.league.LeagueItem
import com.reynaldiwijaya.bravo.data.model.match.MatchItem
import com.reynaldiwijaya.bravo.data.model.player.PlayerItem
import com.reynaldiwijaya.bravo.data.model.tableLeague.TableItem
import com.reynaldiwijaya.bravo.data.model.team.TeamItem

interface DataSource {

    // Remote
    fun getDataInLeagueById(id: String, callback: GetDataLeaguesCallback)
    fun getDataMatchPastLeagueById(idLeague: String, callback: GetDataMatchCallback)
    fun getDataMatchNextLeagueById(idLeague: String, callback: GetDataMatchCallback)
    fun getDataSearchMatchByName(nameMatch: String, callback: GetDataMatchCallback)
    fun getDataDetailMatchById(idMatch: String, callback: GetDataMatchCallback)
    fun getDataTeamByLeagueId(leagueId : String, callback : GetDataTeamCallback)
    fun getDataTableTeamByLeagueId(leagueId: String, callback: GetDataTableTeamCallback)
    fun getDataPlayersByTeamId(teamId : String, callback : GetDataPlayersCallback)
    fun getDataSearchTeamByName(teamName : String, callback : GetDataTeamCallback)


    // Local
    fun addDataMatchToLocal(
        context: Context,
        match: MatchItem,
        callback: AddOrRemoveDataMatchToLocalCallback
    )
    fun removeDataMatchFromLocal(
        context: Context,
        idMatch: String,
        callback: AddOrRemoveDataMatchToLocalCallback
    )
    fun getDataMatchFromLocal(context: Context, callback: GetLocalDataMatchCallback)
    fun getLocalDataMatchById(context: Context, idMatch: String): List<FavoriteMatch>
    fun addDataTeamToLocal(context: Context, team : TeamItem, callback: AddOrRemoveDataTeamToLocalCallback)
    fun removeDataTeamFromLocal(context: Context, idTeam : String, callback: AddOrRemoveDataTeamToLocalCallback)
    fun getDataTeamFromLocal(context: Context, callback: GetLocalDataTeamCallback)
    fun getLocalDataTeamById(context: Context, idTeam: String): List<FavoriteTeam>



    // Response Callback
    interface GetDataLeaguesCallback : ResponseCallback<List<LeagueItem>>
    interface GetDataPlayersCallback : ResponseCallback<List<PlayerItem>>
    interface GetDataMatchCallback : ResponseCallback<List<MatchItem>>
    interface GetLocalDataMatchCallback : ResponseCallback<List<FavoriteMatch>>
    interface GetLocalDataTeamCallback : ResponseCallback<List<FavoriteTeam>>
    interface GetDataTeamCallback : ResponseCallback<List<TeamItem>>
    interface GetDataTableTeamCallback : ResponseCallback<List<TableItem>>
    interface AddOrRemoveDataMatchToLocalCallback : ResponseLocalCallback<MatchItem>
    interface AddOrRemoveDataTeamToLocalCallback : ResponseLocalCallback<TeamItem>
    interface ResponseCallback<T> {
        fun onShowProgressDialog()
        fun onHideProgressDialog()
        fun onSuccess(data: T)
        fun onFailed(errorMessage: String? = "")
    }

    interface ResponseLocalCallback<T> {
        fun onSuccessAdd()
        fun onSuccessRemove()
        fun onSuccessGetData(data: T)
        fun onFailed(message: String)
    }
}