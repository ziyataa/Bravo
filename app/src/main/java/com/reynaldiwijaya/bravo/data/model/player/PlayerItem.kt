package com.reynaldiwijaya.bravo.data.model.player

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PlayerItem(
    @Expose
    @SerializedName("idPlayer")
    var idPlayer: String? = null,
    @Expose
    @SerializedName("idTeam")
    var idTeam: String? = null,
    @Expose
    @SerializedName("strNationality")
    var strNationality: String? = null,
    @Expose
    @SerializedName("strPlayer")
    var strPlayer: String? = null,
    @Expose
    @SerializedName("strTeam")
    var strTeam: String? = null,
    @Expose
    @SerializedName("dateBorn")
    var dateborn: String? = null,
    @Expose
    @SerializedName("strNumber")
    var strNumber: String? = null,
    @Expose
    @SerializedName("dateSigned")
    var dateSigned: String? = null,
    @Expose
    @SerializedName("strSigning")
    val strSigning: String? = null,
    @Expose
    @SerializedName("strWage")
    var strWage: String? = null,
    @Expose
    @SerializedName("strAgent")
    var strAgent: String? = null,
    @Expose
    @SerializedName("strBirthLocation")
    var strBirthLocation: String? = null,
    @Expose
    @SerializedName("strDescriptionEN")
    var strDescriptionEN: String? = null,
    @Expose
    @SerializedName("strPosition")
    var strPosition: String? = null,
    @Expose
    @SerializedName("strThumb")
    var strThumb: String? = null,
    @Expose
    @SerializedName("strCutout")
    var strCutout: String? = null,
    var strTeamFanart : String? = null
) : Parcelable