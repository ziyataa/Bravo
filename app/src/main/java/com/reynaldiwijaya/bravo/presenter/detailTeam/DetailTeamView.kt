package com.reynaldiwijaya.bravo.presenter.detailTeam

interface DetailTeamView {
    fun showSuccessAddToLocal()
    fun showSuccessRemoveFromLocal()
    fun showFailed(message : String)
}