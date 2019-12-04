package com.reynaldiwijaya.bravo.data.model.tableLeague

import com.google.gson.annotations.SerializedName

data class TableItem(
    @SerializedName("name")
    var teamName : String?,
    @SerializedName("teamid")
    var teamId : String?,
    @SerializedName("played")
    var played : String?,
    @SerializedName("goalsfor")
    var goalsFor : String?,
    @SerializedName("goalsagainst")
    var goalsAgainst : String?,
    @SerializedName("goalsdifference")
    var goalsDifference : String?,
    @SerializedName("win")
    var win : String?,
    @SerializedName("draw")
    var draw : String?,
    @SerializedName("loss")
    var loss : String?,
    @SerializedName("total")
    var total : String?

)