package com.reynaldiwijaya.bravo.presenter.player

import com.reynaldiwijaya.bravo.data.model.player.PlayerItem

interface PlayerView {
    fun showLoading()
    fun hideLoading()
    fun showPlayerList(data: List<PlayerItem>)
    fun showError(message: String)
}