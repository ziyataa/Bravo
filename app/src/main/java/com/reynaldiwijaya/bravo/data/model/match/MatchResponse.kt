package com.reynaldiwijaya.bravo.data.model.match

import com.google.gson.annotations.SerializedName

data class MatchResponse(
    @SerializedName("events")
    val matches : List<MatchItem>,
    @SerializedName("event")
    val match : List<MatchItem>
)