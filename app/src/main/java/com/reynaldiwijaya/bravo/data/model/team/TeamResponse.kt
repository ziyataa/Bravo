package com.reynaldiwijaya.bravo.data.model.team

data class TeamResponse(
    val teams : List<TeamItem>,
    val errorMessage : String? = null
)