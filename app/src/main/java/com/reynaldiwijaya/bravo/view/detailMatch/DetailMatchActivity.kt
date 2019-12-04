package com.reynaldiwijaya.bravo.view.detailMatch

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.util.Log
import android.view.MenuItem
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.reynaldiwijaya.bravo.R
import com.reynaldiwijaya.bravo.base.BaseActivity
import com.reynaldiwijaya.bravo.data.Injection
import com.reynaldiwijaya.bravo.data.model.favorite.FavoriteMatch
import com.reynaldiwijaya.bravo.data.model.match.MatchItem
import com.reynaldiwijaya.bravo.presenter.detailMatch.DetailMatchPresenter
import com.reynaldiwijaya.bravo.presenter.match.MatchView
import com.reynaldiwijaya.bravo.utils.*
import com.reynaldiwijaya.bravo.view.adapter.BravoPagerAdapter
import kotlinx.android.synthetic.main.activity_detail_match.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity

class DetailMatchActivity : BaseActivity(), MatchView {

    companion object {
        fun start(context: Context, idMatch: String, state: String?) {
            context.startActivity<DetailMatchActivity>(
                Keys.KEY_EXTRA_ID_MATCH to idMatch, Keys.KEY_STATE_MATCH to state
            )
        }
    }

    private var idMatch: String? = emptyString()
    private lateinit var match: MatchItem
    private lateinit var adapter: BravoPagerAdapter
    private var isFavorite = false

    private val presenter: DetailMatchPresenter by lazy {
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
        fabFavorite.onClick {
            if (isFavorite) {
                removeMatchFromLocal(match)
            } else {
                insertMatchToLocal(match)
            }
        }
    }

    override fun initProcess() {
        idMatch?.let { presenter.getDataDetailMatch(it) }
        checkFavorite()
    }

    private fun checkFavorite() {
        val dataFavorite = idMatch?.let { presenter.getLocalDataMatchById(applicationContext, it) }
        if (dataFavorite?.isNotEmpty() == true) {
            isFavorite = true
            setUpFavorite()
        } else {
            isFavorite = false
            setUpFavorite()
        }
    }

    override fun showLoading() {
        resultMatchContainer.gone()
        vpDetailMatch.gone()
        detailMatchError.gone()
        fabFavorite.gone()
        detailMatchProgress.visible()
    }

    override fun hideLoading() {
        resultMatchContainer.visible()
        vpDetailMatch.visible()
        detailMatchProgress.gone()
    }

    override fun showMatchData(matchData: List<MatchItem>) {
        fabFavorite.visible()

        this.match = matchData[0]

        setupViewPager()
        setupData(this.match)

    }

    override fun showError(message: String) {
        resultMatchContainer.gone()
        vpDetailMatch.gone()
        fabFavorite.gone()
        detailMatchError.visible()
        Log.d(DetailMatchActivity::class.java.simpleName, message)
    }

    override fun showSuccessAddToLocal() {
        showMessage(vpDetailMatch, getString(R.string.label_success_to_add))
    }

    override fun showSuccessRemoveFromLocal() {
        showMessage(vpDetailMatch, getString(R.string.label_success_to_remove))
    }

    override fun showLocalMatchData(matchData: List<FavoriteMatch>) {
        // Do Nothing
    }

    private fun setupViewPager() {
        adapter = BravoPagerAdapter(supportFragmentManager, getFragments(), getTitles())
        setUpViewPager(vpDetailMatch, tabDetailMatch, adapter, false, 2)

    }

    private fun setupData(data: MatchItem) {
        tvTeamNameHome.text = data.teamNameHome
        tvTeamNameAway.text = data.teamNameAway
        tvHomeScore.text = data.homeScore ?: emptyDataWithStrip()
        tvAwayScore.text = data.awayScore ?: emptyDataWithStrip()
        if (data.homeLogo != null && data.awayLogo != null) {
            imgTeamLogoAway?.apply {
                visible()
                imgTeamLogoAway.loadImageUrl(this@DetailMatchActivity, data.awayLogo)
            }
            imgTeamLogoHome?.apply {
                visible()
                imgTeamLogoHome.loadImageUrl(this@DetailMatchActivity, data.homeLogo)
            }
        } else {
            imgTeamLogoAway.gone()
            imgTeamLogoHome.gone()
        }
        setUpFavorite()
    }

    private fun setUpFavorite() {
        if (isFavorite) {
            setImageResourceFabFavorite(true)
        } else {
            setImageResourceFabFavorite(false)
        }
    }

    private fun insertMatchToLocal(match: MatchItem) {
        presenter.addDataMatchToLocal(this, match)
        checkFavorite()
    }

    private fun removeMatchFromLocal(data: MatchItem) {
        data.idEvent?.let { presenter.removeDataMatchFromLocal(this, it) }
        checkFavorite()
    }

    private fun setImageResourceFabFavorite(state: Boolean) {
        if (state) {
            fabFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_added_to_favorite
                )
            )
        } else {
            fabFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_add_to_favorite
                )
            )
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
