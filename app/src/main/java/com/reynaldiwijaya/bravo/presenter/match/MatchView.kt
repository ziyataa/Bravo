package com.reynaldiwijaya.bravo.presenter.match

import com.reynaldiwijaya.bravo.data.model.match.MatchItem


interface MatchView {
    fun showLoading()
    fun hideLoading()
    fun showMatchData(matchData : List<MatchItem>)
    fun showError(message : String)
}