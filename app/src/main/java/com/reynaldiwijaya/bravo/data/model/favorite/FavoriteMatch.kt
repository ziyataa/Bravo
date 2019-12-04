package com.reynaldiwijaya.bravo.data.model.favorite

import com.reynaldiwijaya.bravo.data.model.match.MatchItem

data class FavoriteMatch(
    val id: Long?,
    val idMatch: String?,
    val sport: String?,
    val teamNameHome: String?,
    val teamNameAway: String?,
    val homeScore: String?,
    val awayScore: String?,
    val leagueName: String?,
    val homeGoalDetails: String?,
    val homeRedCards: String?,
    val homeYellowCards: String?,
    val homeLineupGoalKeeper: String?,
    val homeLineupDefense: String?,
    val homeLineupMidfield: String?,
    val homeLineupForward: String?,
    val homeLineupSubstitutes: String?,
    val homeFormation: String?,
    val awayRedCards: String?,
    val awayYellowCards: String?,
    val awayGoalDetails: String?,
    val awayLineupGoalkeeper: String?,
    val awayLineupDefense: String?,
    val awayLineupMidField: String?,
    val awayLineupForward: String?,
    val awayLineupSubstitutes: String?,
    val awayFormation: String?,
    val homeShots: String?,
    val awayShots: String?,
    val dateMatch: String?,
    val time: String?,
    val idHomeTeam: String?,
    val idAwayTeam: String?,
    val homeLogo: String?,
    val awayLogo: String?
) {

    companion object {
        const val TABLE_FAVORITE_MATCH: String = "TABLE_FAVORITE_MATCH"
        const val ID: String = "ID_"
        const val ID_MATCH: String = "ID_MATCH"
        const val SPORT: String = "SPORT"
        const val TEAM_NAME_HOME = "TEAM_NAME_HOME"
        const val TEAM_NAME_AWAY = "TEAM_NAME_AWAY"
        const val HOME_SCORE = "HOME_SCORE"
        const val AWAY_SCORE = "AWAY_SCORE"
        const val LEAGUE_NAME = "LEAGUE_NAME"
        const val HOME_GOAL_DETAILS = "HOME_GOAL_DETAILS"
        const val HOME_RED_CARDS = "HOME_RED_CARDS"
        const val HOME_YELLOW_CARDS = "HOME_YELLOW_CARDS"
        const val HOME_LINEUP_GOALKEEPER = "HOME_LINEUP_GOALKEEPER"
        const val HOME_LINEUP_DEFENSE = "HOME_LINEUP_DEFENSE"
        const val HOME_LINEUP_MIDFIELDER = "HOME_LINEUP_MIDFIELDER"
        const val HOME_LINEUP_FORWARD = "HOME_LINEUP_FORWARD"
        const val HOME_LINEUP_SUBSTITUTES = "HOME_LINEUP_SUBSTITUTES"
        const val HOME_FORMATION = "HOME_FORMATION"
        const val AWAY_RED_CARDS = "AWAY_RED_CARDS"
        const val AWAY_YELLOW_CARDS = "AWAY_YELLOW_CARDS"
        const val AWAY_GOAL_DETAILS = "AWAY_GOAL_DETAILS"
        const val AWAY_LINEUP_GOALKEEPER = "AWAY_LINEUP_GOALKEEPER"
        const val AWAY_LINEUP_DEFENSE = "AWAY_LINEUP_DEFENSE"
        const val AWAY_LINEUP_MIDFIELDER = "AWAY_LINEUP_MIDFIELDER"
        const val AWAY_LINEUP_FORWARD = "AWAY_LINEUP_FORWARD"
        const val AWAY_LINEUP_SUBSTITUTES = "AWAY_LINEUP_SUBSTITUTES"
        const val AWAY_FORMATION = "AWAY_FORMATION"
        const val HOME_SHOTS = "HOME_SHOTS"
        const val AWAY_SHOTS = "AWAY_SHOTS"
        const val DATE_MATCH = "DATE_MATCH"
        const val TIME = "TIME"
        const val ID_HOME_TEAM = "ID_HOME_TEAM"
        const val ID_AWAY_TEAM = "ID_AWAY_TEAM"
        const val HOME_LOGO = "HOME_LOGO"
        const val AWAY_LOGO = "AWAY_LOGO"
    }

    fun toMatchItem(): MatchItem {
        return MatchItem(
            idEvent = idMatch,
            sport = sport,
            teamNameHome = teamNameHome,
            teamNameAway = teamNameAway,
            homeScore = homeScore,
            awayScore = awayScore,
            leagueName = leagueName,
            homeGoalDetails = homeGoalDetails,
            homeRedCards = homeRedCards,
            homeYellowCards = homeYellowCards,
            homeLineupGoalKeeper = homeLineupGoalKeeper,
            homeLineupDefense = homeLineupDefense,
            homeLineupMidfield = homeLineupMidfield,
            homeLineupForward = homeLineupForward,
            homeLineupSubstitutes = homeLineupSubstitutes,
            homeFormation = homeFormation,
            awayRedCards = awayRedCards,
            awayYellowCards = awayYellowCards,
            awayGoalDetails = awayGoalDetails,
            awayLineupGoalkeeper = awayLineupGoalkeeper,
            awayLineupDefense = awayLineupDefense,
            awayLineupMidField = awayLineupMidField,
            awayLineupForward = awayLineupMidField,
            awayLineupSubstitutes = awayLineupSubstitutes,
            awayFormation = awayFormation,
            homeShots = homeShots,
            awayShots = awayShots,
            dateMatch = dateMatch,
            time = time,
            idHomeTeam = idHomeTeam,
            idAwayTeam = idAwayTeam,
            homeLogo = homeLogo,
            awayLogo = awayLogo
        )
    }

}