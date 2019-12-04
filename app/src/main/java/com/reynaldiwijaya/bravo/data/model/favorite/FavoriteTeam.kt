package com.reynaldiwijaya.bravo.data.model.favorite

import com.reynaldiwijaya.bravo.data.model.team.TeamItem

data class FavoriteTeam(
    val id : Long?,
    val teamId : String?,
    val teamName : String?,
    val teamBadge : String?,
    val leagueName : String?,
    val teamDesc : String?,
    val formedYear : String?,
    val imageStadium : String?,
    val strTeamFanart : String?
) {
    companion object {
        const val TABLE_FAVORITE_TEAM: String = "TABLE_FAVORITE_TEAM"
        const val ID: String = "ID_"
        const val TEAM_ID = "TEAM_ID"
        const val TEAM_NAME = "TEAM_NAME"
        const val TEAM_BADGE = "TEAM_BADGE"
        const val LEAGUE_NAME = "LEAGUE_NAME"
        const val TEAM_DESC = "TEAM_DESC"
        const val FORMED_YEAR = "FORMED_YEAR"
        const val IMAGE_STADIUM = "IMAGE_STADIUM"
        const val TEAM_FANART = "TEAM_FANART"
    }

    fun toTeamItem() : TeamItem {
        return TeamItem(
            teamId, teamName, teamBadge, leagueName, teamDesc, formedYear, imageStadium, strTeamFanart
        )
    }
}