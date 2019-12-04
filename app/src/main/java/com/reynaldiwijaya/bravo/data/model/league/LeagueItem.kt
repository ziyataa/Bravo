package com.reynaldiwijaya.bravo.data.model.league

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LeagueItem(
    @SerializedName("idLeague")
    val idLeague: Int? = null,
    @SerializedName("strLeague")
    val leagueName: String? = null,
    @SerializedName("strBadge")
    val leagueBadge: String? = null,
    @SerializedName("strDescriptionEN")
    val leagueDesc: String? = null,
    @SerializedName("strPoster")
    val leaguePoster: String? = null,
    @SerializedName("strLeagueAlternate")
    val otherLeagueName: String? = null,
    @SerializedName("intFormedYear")
    val formedYear: String? = null,
    @SerializedName("strGender")
    val gender: String? = null,
    @SerializedName("strCountry")
    val country: String? = null,
    @SerializedName("strWebsite")
    val website: String? = null
) : Parcelable