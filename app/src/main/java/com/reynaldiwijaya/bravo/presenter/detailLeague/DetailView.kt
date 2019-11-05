package com.reynaldiwijaya.bravo.presenter.detailLeague

import com.reynaldiwijaya.bravo.data.model.league.LeagueItem

interface DetailView {
    fun showLoading()
    fun hideLoadimg()
    fun showDetailLeagueData(leaguesData : List<LeagueItem>)
    fun showError(message : String)
}