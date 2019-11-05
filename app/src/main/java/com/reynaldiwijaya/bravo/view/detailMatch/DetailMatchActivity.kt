package com.reynaldiwijaya.bravo.view.detailMatch

import android.annotation.SuppressLint
import android.content.Context
import androidx.fragment.app.Fragment
import com.reynaldiwijaya.bravo.R
import com.reynaldiwijaya.bravo.base.BaseActivity
import com.reynaldiwijaya.bravo.data.model.match.MatchItem
import com.reynaldiwijaya.bravo.view.adapter.BravoPagerAdapter
import kotlinx.android.synthetic.main.activity_detail_match.*
import org.jetbrains.anko.startActivity
import android.view.WindowManager
import android.os.Build
import android.util.Log
import android.view.MenuItem
import com.reynaldiwijaya.bravo.data.Injection
import com.reynaldiwijaya.bravo.data.model.team.TeamItem
import com.reynaldiwijaya.bravo.presenter.detailMatch.DetailMatchPresenter
import com.reynaldiwijaya.bravo.presenter.item.ItemView
import com.reynaldiwijaya.bravo.presenter.match.MatchView
import com.reynaldiwijaya.bravo.utils.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class DetailMatchActivity : BaseActivity(), MatchView {

    companion object {
        fun start(context: Context, idMatch : String, state : String?) {
            context.startActivity<DetailMatchActivity>(
                Keys.KEY_EXTRA_ID_MATCH to idMatch, Keys.KEY_STATE_MATCH to state)
        }
    }

    private var idMatch : String? = emptyString()
    private lateinit var match : MatchItem
    private lateinit var adapter : BravoPagerAdapter

    private val presenter : DetailMatchPresenter by lazy {
        DetailMatchPresenter(this, Injection.provideApiRepository())
    }

    override val layoutResource: Int = R.layout.activity_detail_match

    override fun initLib() {
    }

    override fun initIntent() {
        this.idMatch = intent.getStringExtra(Keys.KEY_EXTRA_ID_MATCH)
    }

    @SuppressLint("ObsoleteSdkInt")
    override fun initUI() {
        setupToolbar(tbMatch, "", true)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val w = window
            w.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }
    }

    override fun initAction() {
        detailMatchError.onClick { idMatch?.let { it1 -> presenter.getDataDetailMatch(it1) } }
    }

    override fun initProcess() {
        idMatch?.let { presenter.getDataDetailMatch(it) }
    }

    override fun showLoading() {
        detailMatchContainer.gone()
        vpDetailMatch.gone()
        detailMatchError.gone()
        detailMatchProgress.visible()
    }

    override fun hideLoading() {
        detailMatchContainer.visible()
        vpDetailMatch.visible()
        detailMatchProgress.gone()
    }

    override fun showMatchData(matchData: List<MatchItem>) {
        match = matchData[0]

        setupViewPager()
        setupData()
    }

    override fun showError(message: String) {
        detailMatchContainer.gone()
        vpDetailMatch.gone()
        detailMatchError.visible()
        Log.d(DetailMatchActivity::class.java.simpleName, message)
    }

    private fun setupViewPager() {
        adapter = BravoPagerAdapter(supportFragmentManager, getFragments(), getTitles())
        setUpViewPager(vpDetailMatch, tabDetailMatch, adapter, false, 2)

    }

    private fun setupData() {
        tvTeamNameHome.text = this.match.teamNameHome
        tvTeamNameAway.text = this.match.teamNameAway
        if (this.match.homeScore != null) tvHomeScore.text = this.match.homeScore
        else tvHomeScore.text = emptyScoreMatch()
        if (this.match.awayScore != null) tvAwaycore.text = this.match.awayScore
        else tvAwaycore.text = emptyScoreMatch()
        if (this.match.homeLogo != null && this.match.awayLogo != null) {
            imgTeamLogoAway?.apply {
                visible()
                imgTeamLogoAway.loadImageUrl(this@DetailMatchActivity, match.awayLogo ?: emptyString())
            }
            imgTeamLogoHome?.apply {
                visible()
                imgTeamLogoHome.loadImageUrl(this@DetailMatchActivity, match.homeLogo ?: emptyString())
            }
        } else{
            imgTeamLogoAway.gone()
            imgTeamLogoHome.gone()
        }
    }

    private fun getFragments(): List<Fragment> {
        return listOf(
            StatisticFragment.newInstance(this.match),
            LineupFragment.newInstance(this.match)
        )
    }

    private fun getTitles(): List<String> {
        return listOf(
            getString(R.string.label_statistic),
            getString(R.string.label_lineup)
        )
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finishActivity()
        }
        return super.onOptionsItemSelected(item)
    }

}
