package com.reynaldiwijaya.bravo.view.item


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.reynaldiwijaya.bravo.R
import com.reynaldiwijaya.bravo.data.Injection
import com.reynaldiwijaya.bravo.data.model.league.LeagueItem
import com.reynaldiwijaya.bravo.data.model.team.TeamItem
import com.reynaldiwijaya.bravo.presenter.item.ItemPresenter
import com.reynaldiwijaya.bravo.presenter.item.ItemView
import com.reynaldiwijaya.bravo.utils.*
import com.reynaldiwijaya.bravo.view.adapter.ItemAdapter
import com.reynaldiwijaya.bravo.view.detailLeague.DetailLeagueActivity
import com.reynaldiwijaya.bravo.view.detailTeam.DetailTeamActivity
import kotlinx.android.synthetic.main.fragment_item.*
import kotlinx.android.synthetic.main.layout_progress.*
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.toast

class ItemFragment : Fragment(),
    ItemView, AdapterView.OnItemSelectedListener, BaseItemView, ItemAdapter.OnItemClick {

    companion object {
        fun newInstance(state: String, leagueId : String? = null): ItemFragment {
            val frag = ItemFragment()
            val bundle = Bundle()
            bundle.putString(Keys.KEY_STATE_ITEM, state)
            bundle.putString(Keys.KEY_EXTRA_ID_LEAGUE, leagueId)
            frag.arguments = bundle

            return frag
        }
    }

    private lateinit var state: String
    private lateinit var spinnerItem: Spinner
    private lateinit var rvItem: RecyclerView

    private lateinit var presenter: ItemPresenter
    private lateinit var adapter: ItemAdapter

    private val leaguesData = mutableListOf<LeagueItem>()
    private lateinit var leaguesResource: Array<String>
    private lateinit var leagueSelectedItem: String

    private var leagueId: String? = emptyString()

    private val teamsData = mutableListOf<TeamItem>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_item, container, false)

        spinnerItem = view.find(R.id.spinnerItem)
        rvItem = view.find(R.id.rvItem)

        leaguesResource = resources.getStringArray(R.array.league)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initIntent()
        initUI()
        initAction()

        srItem.setOnRefreshListener {
            checkState(actionStateTeam = {
                presenter.getTeamsByLeagueName(leagueSelectedItem)
            }, actionStateLeague = {
                initProcess()
            }, actionStateTeamInLeague = {
                presenter.getTeamsByLeagueId(leagueId)
            }, actionStateTeamFavorite = {
                activity?.let { presenter.getLocalDataTeam(it) }
            })
        }

        checkState(actionStateTeam = {
            setUpSpinner()
        }, actionStateLeague = {
            initProcess()
        }, actionStateTeamInLeague = {
            presenter.getTeamsByLeagueId(leagueId)
        }, actionStateTeamFavorite = {
            activity?.let { presenter.getLocalDataTeam(it) }
        })

    }

    override fun onResume() {
        super.onResume()
        checkState(actionStateTeamFavorite = {
            activity?.let { presenter.getLocalDataTeam(it) }
        })
    }

    override fun initIntent() {
        state = arguments?.getString(Keys.KEY_STATE_ITEM).toString()
        leagueId = arguments?.getString(Keys.KEY_EXTRA_ID_LEAGUE)
    }

    private fun setUpSpinner() {
        val spinnerAdapter = context?.let {
            ArrayAdapter(
                it,
                android.R.layout.simple_spinner_dropdown_item,
                leaguesResource
            )
        }
        spinnerItem.adapter = spinnerAdapter
        spinnerItem.onItemSelectedListener = this
    }

    override fun initUI() {
        adapter = ItemAdapter(state, this)
        presenter = ItemPresenter(this, Injection.provideApiRepository(), Gson())

        rvItem.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = this@ItemFragment.adapter
        }

        checkState(actionStateLeague = {
            spinnerItem.gone()
            progress_circular.gone()
        }, actionStateTeam = {
            spinnerItem.visible()
            progress_circular.visible()
        },actionStateTeamInLeague = {
            spinnerItem.gone()
        }, actionStateTeamFavorite = {
            spinnerItem.gone()
        })
    }

    override fun initProcess() {
        val leagueId = resources.getIntArray(R.array.id_league)
        val leagueLogo = resources.getStringArray(R.array.league_logo)
        checkState(actionStateLeague = {
            leaguesData.clear()
            leaguesResource.forEachIndexed { index, _ ->
                leaguesData.add(
                    LeagueItem(
                        leagueId[index],
                        leaguesResource[index],
                        leagueLogo[index]
                    )
                )
            }
            adapter.setDataLeague(leaguesData)
        })
        srItem.isRefreshing = false
    }

    override fun initAction() {}

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        leagueSelectedItem = spinnerItem.selectedItem.toString()
        presenter.getTeamsByLeagueName(leagueSelectedItem)
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {}

    override fun showLoadingItem() {
        progress_circular.visible()
    }

    override fun hideLoadingItem() {
        srItem.isRefreshing = false
        progress_circular.gone()
    }

    override fun showTeamList(data: List<TeamItem>) {
        teamsData.clear()
        teamsData.addAll(data)
        adapter.setDataTeam(teamsData)
    }

    override fun showLocalTeamList(data: List<TeamItem>) {
        if (data.isNullOrEmpty()) {
            layout_empty_data.visible()
            rvItem.gone()
        } else {
            layout_empty_data.gone()
            rvItem.visible()
            teamsData.clear()
            teamsData.addAll(data)
            adapter.setDataTeam(teamsData)
        }
    }

    override fun showErrorItem(message: String) {
        Log.d(ItemFragment::class.java.simpleName, message)
    }

    private fun checkState(
        actionStateTeam: (() -> Unit)? = null,
        actionStateLeague: (() -> Unit)? = null,
        actionStateTeamInLeague : (() -> Unit)? = null,
        actionStateTeamFavorite : (() -> Unit)? = null
    ) {
        when (state) {
            ItemState.TEAMS.state -> actionStateTeam?.invoke()
            ItemState.LEAGUES.state -> actionStateLeague?.invoke()
            ItemState.TEAMS_IN_LEAGUE.state -> actionStateTeamInLeague?.invoke()
            ItemState.TEAMS_FAVORITE.state -> actionStateTeamFavorite?.invoke()
        }
    }

    override fun onItemLeagueClicked(league: LeagueItem) {
        DetailLeagueActivity.start(context!!, league)
    }

    override fun onItemTeamClicked(team: TeamItem) {
        DetailTeamActivity.start(context!!, team)
    }


}
