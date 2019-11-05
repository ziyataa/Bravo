package com.reynaldiwijaya.bravo.presenter.item

import com.reynaldiwijaya.bravo.data.model.team.TeamItem

interface ItemView {
    fun showLoadingItem()
    fun hideLoadingItem()
    fun showTeamList(data: List<TeamItem>)
    fun showErrorItem(message : String)
}