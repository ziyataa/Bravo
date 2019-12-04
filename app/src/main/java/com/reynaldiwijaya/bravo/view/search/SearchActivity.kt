package com.reynaldiwijaya.bravo.view.search

import android.content.Context
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.reynaldiwijaya.bravo.R
import com.reynaldiwijaya.bravo.base.BaseActivity
import com.reynaldiwijaya.bravo.data.Injection
import com.reynaldiwijaya.bravo.data.model.favorite.FavoriteMatch
import com.reynaldiwijaya.bravo.data.model.league.LeagueItem
import com.reynaldiwijaya.bravo.data.model.match.MatchItem
import com.reynaldiwijaya.bravo.data.model.team.TeamItem
import com.reynaldiwijaya.bravo.presenter.match.MatchView
import com.reynaldiwijaya.bravo.presenter.search.SearchPresenter
import com.reynaldiwijaya.bravo.utils.*
import com.reynaldiwijaya.bravo.view.adapter.ItemAdapter
import com.reynaldiwijaya.bravo.view.adapter.MatchAdapter
import com.reynaldiwijaya.bravo.view.detailMatch.DetailMatchActivity
import com.reynaldiwijaya.bravo.view.detailTeam.DetailTeamActivity
import kotlinx.android.synthetic.main.activity_search_match.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity

class SearchActivity : BaseActivity(), MatchAdapter.OnItemClick, MatchView,
    com.reynaldiwijaya.bravo.presenter.search.SearchView, ItemAdapter.OnItemClick {

    companion object {
        fun start(context: Context, leagueName: String?) {
            context.startActivity<SearchActivity>(Keys.KEY_EXTRA_LEAGUE to leagueName)
        }
    }

    private var leagueName: String? = emptyString()
    private var userInput = emptyString()
    private var isSearchTeam = true
    private var isSearchMatch = false

    private val matchAdapter: MatchAdapter by lazy {
        MatchAdapter(this)
    }

    private val teamAdapter : ItemAdapter by lazy {
        ItemAdapter(ItemState.TEAMS.state, this)
    }

    private val presenter: SearchPresenter by lazy {
        SearchPresenter(this, this, Injection.provideApiRepository())
    }

    override val layoutResource: Int = R.layout.activity_search_match

    override fun initLib() {
    }

    override fun initIntent() {
        if (intent.getStringExtra(Keys.KEY_EXTRA_LEAGUE) != null) {
            leagueName = intent.getStringExtra(Keys.KEY_EXTRA_LEAGUE) as String
        }
    }

    override fun initUI() {
        setupToolbar(tbSearch, emptyString(), true)
        setupRecyclerView()
        setupSearchView()
        listenerRadioButton()
    }

    override fun initAction() {
        layout_error.onClick {
            checkStateSearch(actionMatch = {
                presenter.getSearchMatchData(userInput)
            }, actionTeam = {
                presenter.getSearchTeamData(userInput)
            })
        }
    }

    override fun initProcess() {}

    private fun setupRecyclerView() {
        rvSearch?.apply {
            layoutManager = LinearLayoutManager(this@SearchActivity)
            adapter = teamAdapter
        }
    }

    private fun setupSearchView() {
        searchMatch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    userInput = it
                    checkStateSearch(actionMatch = {
                        presenter.getSearchMatchData(it)
                    }, actionTeam = {
                        presenter.getSearchTeamData(it)
                    })                }
                hideSoftKeyboard(this@SearchActivity)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
    }

    private fun listenerRadioButton() {
        rbMatch.onClick {
            isSearchMatch = true
            isSearchTeam = false
            checkStateSearch(actionMatch = {
                rvSearch.adapter = matchAdapter
                layout_empty_data.gone()
                layout_error.gone()
            })
        }
        rbTeam.onClick {
            isSearchTeam = true
            isSearchMatch = false
            checkStateSearch(actionTeam = {
                rvSearch.adapter = teamAdapter
                layout_empty_data.gone()
                layout_error.gone()
            })
        }

    }

    override fun showLoading() {
        rvSearch.gone()
        layout_error.gone()
        layout_empty_data.gone()
        layout_progress.visible()
    }

    override fun hideLoading() {
        layout_progress.gone()
    }

    override fun showMatchData(matchData: List<MatchItem>) {
        val matchesSoccer = arrayListOf<MatchItem>()

        rvSearch.visible()
        matchData.forEach {
            if (leagueName != emptyString()) {
                if (it.sport.equals(getString(R.string.label_soccer))) {
                    if (it.leagueName.equals(leagueName)) {
                        matchesSoccer.add(it)
                    }
                }
            }
        }
        if (matchesSoccer.isNullOrEmpty()) {
            showError(emptyData())
        } else {
            matchAdapter.setData(matchesSoccer)
        }
    }

    override fun showDataSearchTeam(data: List<TeamItem>) {
        val resultTeamData = arrayListOf<TeamItem>()

        rvSearch.visible()
        data.forEach {
            if (leagueName != emptyString()) {
                if (it.leagueName.equals(leagueName)) {
                    resultTeamData.add(it)
                }
            }
        }

        if (resultTeamData.isNullOrEmpty()) {
            showError(emptyData())
        } else {
            teamAdapter.setDataTeam(resultTeamData)
        }
    }

    override fun showError(message: String) {
        rvSearch.gone()
        if (message == emptyData()) {
            layout_empty_data.visible()
        } else {
            layout_error.visible()
        }
    }

    private fun checkStateSearch(actionMatch : (() -> Unit)? = null,
                                 actionTeam : (() -> Unit)? = null) {
        if (isSearchMatch) {
            actionMatch?.invoke()
        } else if (isSearchTeam) {
            actionTeam?.invoke()
        }

    }

    override fun onItemMatchClicked(match: MatchItem, state: String?) {
        match.idEvent?.let { DetailMatchActivity.start(this, it, state) }
    }

    override fun onItemTeamClicked(team: TeamItem) {
        DetailTeamActivity.start(this, team)
    }

    override fun showSuccessAddToLocal() {
        // Do Nothing
    }

    override fun showSuccessRemoveFromLocal() {
        // Do Nothing
    }
    override fun onItemLeagueClicked(league: LeagueItem) {
        // Do Nothing
    }

    override fun showLocalMatchData(matchData: List<FavoriteMatch>) {
        // Do Nothing
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

}
