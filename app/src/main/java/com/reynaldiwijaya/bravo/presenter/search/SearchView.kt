package com.reynaldiwijaya.bravo.presenter.search

import com.reynaldiwijaya.bravo.data.model.team.TeamItem

interface SearchView {
    fun showDataSearchTeam(data : List<TeamItem>)
}