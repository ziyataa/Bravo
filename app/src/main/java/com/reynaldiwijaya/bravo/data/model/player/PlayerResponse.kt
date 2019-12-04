package com.reynaldiwijaya.bravo.data.model.player

import com.google.gson.annotations.SerializedName

data class PlayerResponse(
    @SerializedName("player")
    var players : List<PlayerItem>
)