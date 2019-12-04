package com.reynaldiwijaya.bravo.data.local

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.reynaldiwijaya.bravo.data.model.favorite.FavoriteMatch
import com.reynaldiwijaya.bravo.data.model.favorite.FavoriteTeam
import org.jetbrains.anko.db.*

class MyDatabaseOpenHelper(ctx: Context) :
    ManagedSQLiteOpenHelper(ctx, "Favorite.db", null, 1) {

    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelper {
            if (instance == null) {
                instance =
                    MyDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as MyDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Here you create Tables
        db.createTable(
            FavoriteMatch.TABLE_FAVORITE_MATCH, true,
            FavoriteMatch.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            FavoriteMatch.ID_MATCH to TEXT + UNIQUE,
            FavoriteMatch.SPORT to TEXT,
            FavoriteMatch.TEAM_NAME_HOME to TEXT,
            FavoriteMatch.TEAM_NAME_AWAY to TEXT,
            FavoriteMatch.HOME_SCORE to TEXT,
            FavoriteMatch.AWAY_SCORE to TEXT,
            FavoriteMatch.LEAGUE_NAME to TEXT,
            FavoriteMatch.HOME_GOAL_DETAILS to TEXT,
            FavoriteMatch.HOME_RED_CARDS to TEXT,
            FavoriteMatch.HOME_YELLOW_CARDS to TEXT,
            FavoriteMatch.HOME_LINEUP_GOALKEEPER to TEXT,
            FavoriteMatch.HOME_LINEUP_DEFENSE to TEXT,
            FavoriteMatch.HOME_LINEUP_MIDFIELDER to TEXT,
            FavoriteMatch.HOME_LINEUP_FORWARD to TEXT,
            FavoriteMatch.HOME_LINEUP_SUBSTITUTES to TEXT,
            FavoriteMatch.HOME_FORMATION to TEXT,
            FavoriteMatch.AWAY_RED_CARDS to TEXT,
            FavoriteMatch.AWAY_YELLOW_CARDS to TEXT,
            FavoriteMatch.AWAY_GOAL_DETAILS to TEXT,
            FavoriteMatch.AWAY_LINEUP_GOALKEEPER to TEXT,
            FavoriteMatch.AWAY_LINEUP_DEFENSE to TEXT,
            FavoriteMatch.AWAY_LINEUP_MIDFIELDER to TEXT,
            FavoriteMatch.AWAY_LINEUP_FORWARD to TEXT,
            FavoriteMatch.AWAY_LINEUP_SUBSTITUTES to TEXT,
            FavoriteMatch.AWAY_FORMATION to TEXT,
            FavoriteMatch.HOME_SHOTS to TEXT,
            FavoriteMatch.AWAY_SHOTS to TEXT,
            FavoriteMatch.DATE_MATCH to TEXT,
            FavoriteMatch.TIME to TEXT,
            FavoriteMatch.ID_HOME_TEAM to TEXT,
            FavoriteMatch.ID_AWAY_TEAM to TEXT,
            FavoriteMatch.HOME_LOGO to TEXT,
            FavoriteMatch.AWAY_LOGO to TEXT
        )

        db.createTable(
            FavoriteTeam.TABLE_FAVORITE_TEAM, true,
            FavoriteTeam.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            FavoriteTeam.TEAM_ID to TEXT + UNIQUE,
            FavoriteTeam.TEAM_NAME to TEXT,
            FavoriteTeam.TEAM_BADGE to TEXT,
            FavoriteTeam.LEAGUE_NAME to TEXT,
            FavoriteTeam.TEAM_DESC to TEXT,
            FavoriteTeam.FORMED_YEAR to TEXT,
            FavoriteTeam.IMAGE_STADIUM to TEXT,
            FavoriteTeam.TEAM_FANART to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        // Here you can upgrade tables, as usual
        db.dropTable(FavoriteMatch.TABLE_FAVORITE_MATCH, true)
        db.dropTable(FavoriteTeam.TABLE_FAVORITE_TEAM, true)
    }
}

// Access property for Context
val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(this)