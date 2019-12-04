package com.reynaldiwijaya.bravo.data.model.league

import com.google.gson.annotations.SerializedName

data class LeagueResponse(
    @SerializedName("leagues")
    val leagues: List<LeagueItem>?
)