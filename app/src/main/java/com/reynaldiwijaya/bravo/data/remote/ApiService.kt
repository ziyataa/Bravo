package com.reynaldiwijaya.bravo.data.remote

import com.reynaldiwijaya.bravo.BuildConfig
import com.reynaldiwijaya.bravo.data.model.league.LeagueResponse
import com.reynaldiwijaya.bravo.data.model.match.MatchResponse
import com.reynaldiwijaya.bravo.data.model.player.PlayerResponse
import com.reynaldiwijaya.bravo.data.model.tableLeague.TableResponse
import com.reynaldiwijaya.bravo.data.model.team.TeamResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface ApiService {

    @GET("api/v1/json/1/lookupleague.php")
    suspend fun getDetailLeagueById(
        @Query("id") id: String
    ): LeagueResponse

    @GET("api/v1/json/1/eventspastleague.php")
    suspend fun getMatchesPastLeagueById(
        @Query("id") idLeague: String
    ): MatchResponse

    @GET("api/v1/json/1/eventsnextleague.php")
    suspend fun getMatchesNextLeagueById(
        @Query("id") idLeague: String
    ): MatchResponse

    @GET("api/v1/json/1/searchevents.php")
    suspend fun getMatchSearchDataByName(
        @Query("e") nameMatch: String
    ): MatchResponse

    @GET("api/v1/json/1/lookupevent.php")
    suspend fun getDetailMatchById(
        @Query("id") idEvent: String
    ): MatchResponse

    @GET("api/v1/json/1/lookupteam.php")
    suspend fun getDetailTeamById(
        @Query("id") idTeam: String
    ): TeamResponse

    @GET("api/v1/json/1/lookup_all_teams.php")
    suspend fun getTeamsByLeagueId(
        @Query("id") idLeague : String
    ) : TeamResponse

    @GET("api/v1/json/1/lookuptable.php")
    suspend fun getTableLeague(
        @Query("l") idLeague : String
    ) : TableResponse

    @GET("api/v1/json/1/lookup_all_players.php")
    suspend fun getPlayersByTeamId(
        @Query("id") idTeam : String
    ) : PlayerResponse

    @GET("api/v1/json/1/searchteams.php")
    suspend fun getTeamSearchDataByName(
        @Query("t") teamName : String
    ) : TeamResponse

    companion object Factory {
        val getApiService: ApiService by lazy {

            val mClient =
                OkHttpClient.Builder()
                    .readTimeout(30, TimeUnit.SECONDS)
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build()

            val mRetrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(mClient)
                .build()

            mRetrofit.create(ApiService::class.java)
        }
    }
}