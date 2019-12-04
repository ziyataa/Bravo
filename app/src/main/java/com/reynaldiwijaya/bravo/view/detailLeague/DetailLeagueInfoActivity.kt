package com.reynaldiwijaya.bravo.view.detailLeague

import android.content.Context
import android.view.MenuItem
import com.reynaldiwijaya.bravo.R
import com.reynaldiwijaya.bravo.base.BaseActivity
import com.reynaldiwijaya.bravo.data.model.league.LeagueItem
import com.reynaldiwijaya.bravo.utils.Keys
import com.reynaldiwijaya.bravo.utils.loadImageUrl
import kotlinx.android.synthetic.main.activity_detail_league_info.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import org.jetbrains.anko.startActivity

class DetailLeagueInfoActivity : BaseActivity() {

    companion object {
        fun start(context: Context, league: LeagueItem) {
            context.startActivity<DetailLeagueInfoActivity>(Keys.KEY_EXTRA_LEAGUE to league)
        }
    }

    private lateinit var league: LeagueItem

    override val layoutResource: Int = R.layout.activity_detail_league_info

    override fun initLib() {
    }

    override fun initIntent() {
        league = intent.getParcelableExtra(Keys.KEY_EXTRA_LEAGUE)
    }

    override fun initUI() {
        setupToolbar(toolbar, league.leagueName.toString(), true)
        setupData()
    }

    override fun initAction() {
    }

    override fun initProcess() {
    }

    private fun setupData() {
        league.leagueBadge?.let { imgLeagueLogo.loadImageUrl(this, it) }
        tvLeagueName.text = league.leagueName
        tvOtherLeagueName.text = league.otherLeagueName
        tvFormedYear.text = league.formedYear
        tvCountry.text = league.country
        tvGender.text = league.gender
        tvLeagueDesc.text = league.leagueDesc
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

}
