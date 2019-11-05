package com.reynaldiwijaya.bravo.utils

import com.reynaldiwijaya.bravo.data.model.team.TeamItem

sealed class DetailMatchViewState {
    data class DataLoadedState(var data : List<TeamItem>) : DetailMatchViewState()
    data class DataEmptyState(var message : String) : DetailMatchViewState()
    data class ErrorGetDataState(var message : String) : DetailMatchViewState()
}