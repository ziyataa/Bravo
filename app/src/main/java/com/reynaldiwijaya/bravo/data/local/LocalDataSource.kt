package com.reynaldiwijaya.bravo.data.local

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import com.reynaldiwijaya.bravo.data.DataSource
import com.reynaldiwijaya.bravo.data.model.favorite.FavoriteMatch
import com.reynaldiwijaya.bravo.data.model.favorite.FavoriteTeam
import com.reynaldiwijaya.bravo.data.model.match.MatchItem
import com.reynaldiwijaya.bravo.data.model.team.TeamItem
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

object LocalDataSource : DataSource {

    override fun addDataMatchToLocal(
        context: Context,
        match: MatchItem,
        callback: DataSource.AddOrRemoveDataMatchToLocalCallback
    ) {
        try {
            context.database.use {
                insert(
                    FavoriteMatch.TABLE_FAVORITE_MATCH,
                    FavoriteMatch.ID_MATCH to match.idEvent,
                    FavoriteMatch.SPORT to match.sport,
                    FavoriteMatch.TEAM_NAME_HOME to match.teamNameHome,
                    FavoriteMatch.TEAM_NAME_AWAY to match.teamNameAway,
                    FavoriteMatch.HOME_SCORE to match.homeScore,
                    FavoriteMatch.AWAY_SCORE to match.awayScore,
                    FavoriteMatch.LEAGUE_NAME to match.leagueName,
                    FavoriteMatch.HOME_GOAL_DETAILS to match.homeGoalDetails,
                    FavoriteMatch.HOME_RED_CARDS to match.homeRedCards,
                    FavoriteMatch.HOME_YELLOW_CARDS to match.homeYellowCards,
                    FavoriteMatch.HOME_LINEUP_GOALKEEPER to match.homeLineupGoalKeeper,
                    FavoriteMatch.HOME_LINEUP_DEFENSE to match.homeLineupDefense,
                    FavoriteMatch.HOME_LINEUP_MIDFIELDER to match.homeLineupMidfield,
                    FavoriteMatch.HOME_LINEUP_FORWARD to match.homeLineupForward,
                    FavoriteMatch.HOME_LINEUP_SUBSTITUTES to match.homeLineupSubstitutes,
                    FavoriteMatch.HOME_FORMATION to match.homeFormation,
                    FavoriteMatch.AWAY_RED_CARDS to match.awayRedCards,
                    FavoriteMatch.AWAY_YELLOW_CARDS to match.awayYellowCards,
                    FavoriteMatch.AWAY_GOAL_DETAILS to match.awayGoalDetails,
                    FavoriteMatch.AWAY_LINEUP_GOALKEEPER to match.awayLineupGoalkeeper,
                    FavoriteMatch.AWAY_LINEUP_DEFENSE to match.awayLineupDefense,
                    FavoriteMatch.AWAY_LINEUP_MIDFIELDER to match.awayLineupMidField,
                    FavoriteMatch.AWAY_LINEUP_FORWARD to match.awayLineupForward,
                    FavoriteMatch.AWAY_LINEUP_SUBSTITUTES to match.awayLineupSubstitutes,
                    FavoriteMatch.AWAY_FORMATION to match.awayFormation,
                    FavoriteMatch.HOME_SHOTS to match.homeShots,
                    FavoriteMatch.AWAY_SHOTS to match.awayShots,
                    FavoriteMatch.DATE_MATCH to match.dateMatch,
                    FavoriteMatch.TIME to match.time,
                    FavoriteMatch.ID_HOME_TEAM to match.idHomeTeam,
                    FavoriteMatch.ID_AWAY_TEAM to match.idAwayTeam,
                    FavoriteMatch.HOME_LOGO to match.homeLogo,
                    FavoriteMatch.AWAY_LOGO to match.awayLogo
                )
                callback.onSuccessAdd()
            }
        } catch (e: SQLiteConstraintException) {
            e.message?.let { callback.onFailed(it) }
        }
    }

    override fun removeDataMatchFromLocal(
        context: Context,
        idMatch: String,
        callback: DataSource.AddOrRemoveDataMatchToLocalCallback
    ) {
        try {
            context.database.use {
                delete(
                    FavoriteMatch.TABLE_FAVORITE_MATCH, "(ID_MATCH = {id})",
                    "id" to idMatch
                )
            }
            callback.onSuccessRemove()
        } catch (e: SQLiteConstraintException) {
            e.message?.let { callback.onFailed(it) }
        }
    }

    override fun getDataMatchFromLocal(
        context: Context,
        callback: DataSource.GetLocalDataMatchCallback
    ) {
        callback.onShowProgressDialog()
        val data = arrayListOf<FavoriteMatch>()
        try {
            context.database.use {
                val result = select(FavoriteMatch.TABLE_FAVORITE_MATCH)
                data.addAll(result.parseList(classParser()))
                callback.onSuccess(data)
            }
            callback.onHideProgressDialog()
        } catch (e: SQLiteConstraintException) {
            e.message?.let { callback.onFailed(it) }
            callback.onHideProgressDialog()
        }
    }

    override fun getLocalDataMatchById(context: Context, idMatch: String): List<FavoriteMatch> {
        val data = arrayListOf<FavoriteMatch>()

        try {
            context.database.use {
                val result = select(FavoriteMatch.TABLE_FAVORITE_MATCH)
                    .whereArgs(
                        "(ID_MATCH = {id})",
                        "id" to idMatch
                    )
                data.addAll(result.parseList(classParser()))
            }
        } catch (e: SQLiteConstraintException) {
            e.printStackTrace()
        }

        return data
    }

    override fun addDataTeamToLocal(
        context: Context,
        team: TeamItem,
        callback: DataSource.AddOrRemoveDataTeamToLocalCallback
    ) {
        try {
            context.database.use {
                insert(
                    FavoriteTeam.TABLE_FAVORITE_TEAM,
                    FavoriteTeam.TEAM_ID to team.teamId,
                    FavoriteTeam.TEAM_NAME to team.teamName,
                    FavoriteTeam.TEAM_BADGE to team.teamBadge,
                    FavoriteTeam.LEAGUE_NAME to team.leagueName,
                    FavoriteTeam.TEAM_DESC to team.teamDesc,
                    FavoriteTeam.FORMED_YEAR to team.formedYear,
                    FavoriteTeam.IMAGE_STADIUM to team.imageStadium,
                    FavoriteTeam.TEAM_FANART to team.strTeamFanart
                )
                callback.onSuccessAdd()
            }
        }catch (e: SQLiteConstraintException) {
            e.message?.let { callback.onFailed(it) }
        }
    }

    override fun removeDataTeamFromLocal(
        context: Context,
        idTeam: String,
        callback: DataSource.AddOrRemoveDataTeamToLocalCallback
    ) {
        try {
            context.database.use {
                delete(
                    FavoriteTeam.TABLE_FAVORITE_TEAM, "(TEAM_ID = {id})",
                    "id" to idTeam
                )
            }
            callback.onSuccessRemove()
        } catch (e: SQLiteConstraintException) {
            e.message?.let { callback.onFailed(it) }
        }
    }

    override fun getDataTeamFromLocal(
        context: Context,
        callback: DataSource.GetLocalDataTeamCallback
    ) {
        callback.onShowProgressDialog()
        val data = arrayListOf<FavoriteTeam>()
        try {
            context.database.use {
                val result = select(FavoriteTeam.TABLE_FAVORITE_TEAM)
                data.addAll(result.parseList(classParser()))
                callback.onSuccess(data)
            }
            callback.onHideProgressDialog()
        } catch (e: SQLiteConstraintException) {
            e.message?.let { callback.onFailed(it) }
            callback.onHideProgressDialog()
        }
    }

    override fun getLocalDataTeamById(context: Context, idTeam: String): List<FavoriteTeam> {
        val data = arrayListOf<FavoriteTeam>()

        try {
            context.database.use {
                val result = select(FavoriteTeam.TABLE_FAVORITE_TEAM)
                    .whereArgs(
                        "(TEAM_ID = {id})",
                        "id" to idTeam
                    )
                data.addAll(result.parseList(classParser()))
            }
        } catch (e: SQLiteConstraintException) {
            e.printStackTrace()
        }

        return data
    }

    override fun getDataInLeagueById(id: String, callback: DataSource.GetDataLeaguesCallback) {
        // Do Nothing
    }

    override fun getDataMatchPastLeagueById(
        idLeague: String,
        callback: DataSource.GetDataMatchCallback
    ) {
        // Do Nothing
    }

    override fun getDataMatchNextLeagueById(
        idLeague: String,
        callback: DataSource.GetDataMatchCallback
    ) {
        // Do Nothing
    }

    override fun getDataSearchMatchByName(
        nameMatch: String,
        callback: DataSource.GetDataMatchCallback
    ) {
        // Do Nothing
    }

    override fun getDataDetailMatchById(
        idMatch: String,
        callback: DataSource.GetDataMatchCallback
    ) {
        // Do Nothing
    }

    override fun getDataTeamByLeagueId(leagueId: String, callback: DataSource.GetDataTeamCallback) {
    }

    override fun getDataTableTeamByLeagueId(
        leagueId: String,
        callback: DataSource.GetDataTableTeamCallback
    ) {
        // Do Nothing
    }

    override fun getDataPlayersByTeamId(
        teamId: String,
        callback: DataSource.GetDataPlayersCallback
    ) {
        // Do Nothing
    }

    override fun getDataSearchTeamByName(
        teamName: String,
        callback: DataSource.GetDataTeamCallback
    ) {
        // DO NOTHING
    }
}