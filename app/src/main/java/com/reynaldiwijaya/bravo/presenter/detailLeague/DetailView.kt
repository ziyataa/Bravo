package com.reynaldiwijaya.bravo.presenter.detailLeague

import com.reynaldiwijaya.bravo.data.model.league.LeagueItem

interface DetailView {
    fun showLoading()
    fun hideLoading()
    fun showDetailLeagueData(leaguesData: List<LeagueItem>)
    fun showError(message: String)
}