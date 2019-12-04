package com.reynaldiwijaya.bravo.view.detailTeam

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.view.MenuItem
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.reynaldiwijaya.bravo.R
import com.reynaldiwijaya.bravo.base.BaseActivity
import com.reynaldiwijaya.bravo.data.Injection
import com.reynaldiwijaya.bravo.data.model.team.TeamItem
import com.reynaldiwijaya.bravo.presenter.detailTeam.DetailTeamPresenter
import com.reynaldiwijaya.bravo.presenter.detailTeam.DetailTeamView
import com.reynaldiwijaya.bravo.utils.Keys
import com.reynaldiwijaya.bravo.utils.loadImageUrl
import com.reynaldiwijaya.bravo.utils.showMessage
import com.reynaldiwijaya.bravo.view.adapter.BravoPagerAdapter
import kotlinx.android.synthetic.main.activity_detail_team.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity

class DetailTeamActivity : BaseActivity(), DetailTeamView {

    companion object {
        fun start(context : Context, teamData : TeamItem) {
            context.startActivity<DetailTeamActivity>(Keys.KEY_EXTRA_TEAM to teamData)
        }
    }

    private val presenter : DetailTeamPresenter by lazy {
        DetailTeamPresenter(this, Injection.provideApiRepository())
    }

    private lateinit var teamData : TeamItem
    private lateinit var pagerAdapter: BravoPagerAdapter
    private var isFavorite = false

    override val layoutResource: Int = R.layout.activity_detail_team

    override fun initLib() {}

    override fun initIntent() {
        teamData = intent.getParcelableExtra(Keys.KEY_EXTRA_TEAM)
    }

    @SuppressLint("ObsoleteSdkInt")
    override fun initUI() {
        setupToolbar(tbDetailTeam, "", true)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val w = window
            w.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }

        setUpView()
        setupViewPager()
    }

    override fun initAction() {
        fabTeamFavorite.onClick {
            if (isFavorite) {
                removeMatchFromLocal(teamData)
            } else {
                insertMatchToLocal(teamData)
            }
        }
    }

    override fun initProcess() {
        checkFavorite()
    }

    private fun insertMatchToLocal(teamData: TeamItem) {
        presenter.addDataTeamToLocal(this, teamData)
        checkFavorite()
    }

    private fun removeMatchFromLocal(team: TeamItem) {
        teamData.teamId?.let { presenter.removeDataTeamFromLocal(this, it) }
        checkFavorite()
    }

    private fun checkFavorite() {
        val dataFavorite = teamData.teamId?.let { presenter.getLocalDataTeamById(this, it) }
        if (dataFavorite?.isNotEmpty() == true) {
            isFavorite = true
            setUpFavorite()
        } else {
            isFavorite = false
            setUpFavorite()
        }
    }

    private fun setUpFavorite() {
        if (isFavorite) {
            setImageResourceFabFavorite(true)
        } else {
            setImageResourceFabFavorite(false)
        }
    }

    override fun showSuccessAddToLocal() {
        showMessage(vpDetailTeam, "Success add ${teamData.teamName} to favorite")
    }

    override fun showSuccessRemoveFromLocal() {
        showMessage(vpDetailTeam, "Success remove ${teamData.teamName} from favorite")
    }

    override fun showFailed(message: String) {
        showMessage(vpDetailTeam, message)
    }

    private fun setImageResourceFabFavorite(state: Boolean) {
        if (state) {
            fabTeamFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_added_to_favorite
                )
            )
        } else {
            fabTeamFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_add_to_favorite
                )
            )
        }
    }

    private fun setupViewPager() {
        pagerAdapter = BravoPagerAdapter(supportFragmentManager, getFragments(), getTitles())
        setUpViewPager(vpDetailTeam, tabDetailTeam, pagerAdapter, false, 2)
    }

    private fun getTitles(): List<String> {
        return listOf(
            getString(R.string.label_desc),
            getString(R.string.label_squad)
        )
    }

    private fun getFragments(): List<Fragment> {
        return listOf(
            TeamDescFragment.newInstance(teamData.teamDesc),
            PlayersFragment.newInstance(teamData)
        )
    }

    private fun setUpView() {
        teamData.imageStadium?.let { imgStadium.loadImageUrl(this, it) }
        teamData.teamBadge?.let { imgLogoTeam.loadImageUrl(this, it) }
        tvTeamName.text = teamData.teamName
        tvFormedYear.text = teamData.formedYear
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finishActivity()
        }
        return super.onOptionsItemSelected(item)
    }
}
