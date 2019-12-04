package com.reynaldiwijaya.bravo.data.model.team

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TeamItem(
    @SerializedName("idTeam")
    var teamId: String? = null,

    @SerializedName("strTeam")
    var teamName: String? = null,

    @SerializedName("strTeamBadge")
    var teamBadge: String? = null,

    @SerializedName("strLeague")
    var leagueName : String? = null,

    @SerializedName("strDescriptionEN")
    var teamDesc : String? = null,

    @SerializedName("intFormedYear")
    var formedYear : String? = null,

    @SerializedName("strStadiumThumb")
    val imageStadium : String? = null,

    @SerializedName("strTeamFanart4")
    val strTeamFanart : String? = null
) : Parcelable
