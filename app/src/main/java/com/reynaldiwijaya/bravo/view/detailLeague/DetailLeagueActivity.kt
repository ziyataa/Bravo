package com.reynaldiwijaya.bravo.view.detailLeague

import android.content.Context
import android.util.Log
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.AppBarLayout
import com.reynaldiwijaya.bravo.R
import com.reynaldiwijaya.bravo.base.BaseActivity
import com.reynaldiwijaya.bravo.data.Injection
import com.reynaldiwijaya.bravo.data.model.league.LeagueItem
import com.reynaldiwijaya.bravo.presenter.detailLeague.DetailPresenter
import com.reynaldiwijaya.bravo.presenter.detailLeague.DetailView
import com.reynaldiwijaya.bravo.utils.*
import com.reynaldiwijaya.bravo.view.adapter.BravoPagerAdapter
import com.reynaldiwijaya.bravo.view.item.ItemFragment
import com.reynaldiwijaya.bravo.view.match.MatchFragment
import com.reynaldiwijaya.bravo.view.search.SearchActivity
import com.reynaldiwijaya.bravo.view.standings.StandingsFragment
import kotlinx.android.synthetic.main.activity_detail_league.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity
import kotlin.math.abs

class DetailLeagueActivity : BaseActivity(),
    DetailView, AppBarLayout.OnOffsetChangedListener, DetailLeagueActionListener {

    companion object {
        fun start(context: Context, league: LeagueItem) {
            context.startActivity<DetailLeagueActivity>(Keys.KEY_EXTRA_LEAGUE to league)
        }
    }

    private lateinit var league: LeagueItem
    private lateinit var presenter: DetailPresenter

    private val pagerAdapter: BravoPagerAdapter by lazy {
        BravoPagerAdapter(supportFragmentManager, getFragments(), getTitlesFragment())
    }

    override val layoutResource: Int = R.layout.activity_detail_league

    override fun initLib() {
        presenter = DetailPresenter(this, Injection.provideApiRepository())
    }

    override fun initIntent() {
        try {
            league = intent.getParcelableExtra(Keys.KEY_EXTRA_LEAGUE)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun initUI() {
        imgBgLeague.alpha = 0.5F
        setupToolbar(tbLeague, emptyString(), true)
        imgSearch.setImageResource(R.drawable.ic_search_gray)
        setUpViewPager()
        appBarLayout.addOnOffsetChangedListener(this)

    }

    override fun initAction() {
        imgSearch.onClick { onClickSearch() }
        cvLeague.onClick { DetailLeagueInfoActivity.start(this@DetailLeagueActivity, league) }
    }

    override fun initProcess() {
        league.idLeague?.let { presenter.getDataInLeague(it.toString()) }
    }

    private var isHideToolbarView = false
    override fun onOffsetChanged(appBarLayout: AppBarLayout?, position: Int) {
        val maxScroll = appBarLayout?.totalScrollRange
        val percentage: Float = abs(position).toFloat() / (maxScroll?.toFloat() ?: 0f)

        if (percentage == 1f && isHideToolbarView) {
            tvTitleOnAppBar.visible()
            isHideToolbarView = !isHideToolbarView
        } else if (percentage < 1f && !isHideToolbarView) {
            tvTitleOnAppBar.invisible()
            isHideToolbarView = !isHideToolbarView
        }
    }

    private fun setUpViewPager() {
        setUpViewPager(
            viewPager = vpLeague,
            tabLayout = tabLeague,
            adapter = pagerAdapter,
            swipeEnabled = false,
            offScreenPageLimit = 4
        )
    }

    override fun showLoading() {
        layout_progress.visible()
    }

    override fun hideLoading() {
        layout_progress.gone()
    }

    override fun showDetailLeagueData(leaguesData: List<LeagueItem>) {
        val league = leaguesData[0]
        this.league = league
        league.leaguePoster?.let { imgBgLeague.loadImageUrl(this, it) }
        league.leagueBadge?.let { imgLeagueLogo.loadImageUrl(this, it) }
        tvTitleOnAppBar.text = league.leagueName
        tvLeagueName.text = league.leagueName
        tvLeagueDesc.text = league.leagueDesc
    }

    override fun showError(message: String) {
        Log.d(DetailLeagueActivity::class.java.simpleName, message)
        layout_error.visible()
    }

    private fun getTitlesFragment(): List<String> {
        return listOf(
            getString(R.string.label_last_match),
            getString(R.string.label_standings),
            getString(R.string.label_next_match),
            getString(R.string.label_teams)
        )
    }

    private fun getFragments(): List<Fragment> {
        return listOf(
            MatchFragment.newInstance(
                MatchState.LAST.state,
                MatchState.API.state,
                league.idLeague.toString()
            ),
            StandingsFragment.newInstance(league.idLeague.toString(), league.leagueName.toString()),
            MatchFragment.newInstance(
                MatchState.NEXT.state,
                MatchState.API.state,
                league.idLeague.toString()
            ),
            ItemFragment.newInstance(ItemState.TEAMS_IN_LEAGUE.state, league.idLeague.toString())
        )
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onClickSearch() {
        league.leagueName?.let { SearchActivity.start(this, it) }
    }

}
