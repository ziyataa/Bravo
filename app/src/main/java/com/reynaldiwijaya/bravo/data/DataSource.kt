package com.reynaldiwijaya.bravo.data

import com.reynaldiwijaya.bravo.data.model.league.LeagueItem
import com.reynaldiwijaya.bravo.data.model.match.MatchItem
import com.reynaldiwijaya.bravo.data.model.team.TeamItem

interface DataSource {
    fun getDataInLeagueById(id : String, callback: GetDataLeaguesCallback)
    fun getDataMatchPastLeagueById(idLeague : String, callback : GetDataMatchCallback)
    fun getDataMatchNextLeagueById(idLeague : String, callback : GetDataMatchCallback)
    fun getDataSearchMatchByName(nameMatch : String, callback: GetDataMatchCallback)
    fun getDataDetailMatchById(idMatch : String, callback: GetDataMatchCallback)

    // Response Callback
    interface GetDataLeaguesCallback : ResponseCallback<List<LeagueItem>>
    interface GetDataMatchCallback : ResponseCallback<List<MatchItem>>
    interface ResponseCallback<T> {
        fun onShowProgressDialog()
        fun onHideProgressDialog()
        fun onSuccess(data: T)
        fun onFailed(errorMessage: String? = "")
    }
}