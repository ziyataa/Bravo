package com.reynaldiwijaya.bravo.data.model.match

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MatchItem(
    @SerializedName("idEvent")
    val idEvent : String? = null,
    @SerializedName("strEvent")
    val teamVersus : String? = null,
    @SerializedName("strSport")
    val sport : String? = null,
    @SerializedName("strHomeTeam")
    val teamNameHome : String? = null,
    @SerializedName("strAwayTeam")
    val teamNameAway : String? = null,
    @SerializedName("intHomeScore")
    val homeScore : String? = null,
    @SerializedName("intAwayScore")
    val awayScore : String? = null,
    @SerializedName("strLeague")
    val leagueName : String? = null,
    @SerializedName("strHomeGoalDetails")
    val homeGoalDetails : String? = null,
    @SerializedName("strHomeRedCards")
    val homeRedCards : String? = null,
    @SerializedName("strHomeYellowCards")
    val homeYellowCards : String? = null,
    @SerializedName("strHomeLineupGoalkeeper")
    val homeLineupGoalKeeper : String? = null,
    @SerializedName("strHomeLineupDefense")
    val homeLineupDefense : String? = null,
    @SerializedName("strHomeLineupMidfield")
    val homeLineupMidfield : String? = null,
    @SerializedName("strHomeLineupForward")
    val homeLineupForward : String? = null,
    @SerializedName("strHomeLineupSubstitutes")
    val homeLineupSubstitutes : String? = null,
    @SerializedName("strHomeFormation")
    val homeFormation : String? = null,
    @SerializedName("strAwayRedCards")
    val awayRedCards : String? = null,
    @SerializedName("strAwayYellowCards")
    val awayYellowCards : String? = null,
    @SerializedName("strAwayGoalDetails")
    val awayGoalDetails : String? = null,
    @SerializedName("strAwayLineupGoalkeeper")
    val awayLineupGoalkeeper : String? = null,
    @SerializedName("strAwayLineupDefense")
    val awayLineupDefense : String? = null,
    @SerializedName("strAwayLineupMidfield")
    val awayLineupMidField : String? = null,
    @SerializedName("strAwayLineupForward")
    val awayLineupForward : String? = null,
    @SerializedName("strAwayLineupSubstitutes")
    val awayLineupSubstitutes : String? = null,
    @SerializedName("strAwayFormation")
    val awayFormation : String? = null,
    @SerializedName("intHomeShots")
    val homeShots : String? = null,
    @SerializedName("intAwayShots")
    val awayShots : String? = null,
    @SerializedName("dateEvent")
    val dateMatch : String? = null,
    @SerializedName("strTime")
    val time : String? = null,
    @SerializedName("idHomeTeam")
    val idHomeTeam : String? = null,
    @SerializedName("idAwayTeam")
    val idAwayTeam : String? = null,
    val homeLogo : String? = null,
    val awayLogo : String? = null
) : Parcelable