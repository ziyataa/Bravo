package com.reynaldiwijaya.bravo.view.detailTeam


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

import com.reynaldiwijaya.bravo.R
import com.reynaldiwijaya.bravo.data.Injection
import com.reynaldiwijaya.bravo.data.model.player.PlayerItem
import com.reynaldiwijaya.bravo.data.model.team.TeamItem
import com.reynaldiwijaya.bravo.presenter.player.PlayerPresenter
import com.reynaldiwijaya.bravo.presenter.player.PlayerView
import com.reynaldiwijaya.bravo.utils.Keys
import com.reynaldiwijaya.bravo.utils.emptyData
import com.reynaldiwijaya.bravo.utils.gone
import com.reynaldiwijaya.bravo.utils.visible
import com.reynaldiwijaya.bravo.view.adapter.PlayerAdapter
import com.reynaldiwijaya.bravo.view.detailPlayer.DetailPlayerActivity
import kotlinx.android.synthetic.main.fragment_players.*
import org.jetbrains.anko.find

class PlayersFragment : Fragment(), PlayerAdapter.OnPlayerClick, PlayerView {

    companion object {
        fun newInstance(teamItem : TeamItem) : PlayersFragment {
            val frag = PlayersFragment()
            val bundle = Bundle()
            bundle.putParcelable(Keys.KEY_EXTRA_TEAM, teamItem)
            frag.arguments = bundle

            return frag
        }
    }

    private var teamItem: TeamItem? = null

    private lateinit var rvPlayers : RecyclerView
    private lateinit var srPlayer : SwipeRefreshLayout

    private val playerAdapter : PlayerAdapter by lazy {
        PlayerAdapter(this)
    }
    private val presenter : PlayerPresenter by lazy {
        PlayerPresenter(this, Injection.provideApiRepository())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_players, container, false)

        rvPlayers = view.find(R.id.rvPlayers)
        srPlayer = view.find(R.id.srPlayer)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        teamItem = arguments?.getParcelable(Keys.KEY_EXTRA_TEAM)

        initUI()
        initProcess()
    }

    private fun initUI() {
        rvPlayers.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = playerAdapter
        }
        srPlayer.setOnRefreshListener {
            initProcess()
        }
    }

    private fun initProcess() {
        teamItem?.teamId?.let { presenter.getDataPlayerByTeamId(it) }
    }

    override fun showLoading() {
        progressPlayersContainer.visible()
        errorContainer.gone()
        emptyDataContainer.gone()
        rvPlayers.gone()
    }

    override fun hideLoading() {
        srPlayer.isRefreshing = false
        progressPlayersContainer.gone()
    }

    override fun showPlayerList(data: List<PlayerItem>) {
        rvPlayers.visible()
        playerAdapter.setData(data)
    }

    override fun showError(message: String) {
        if (message == emptyData()) {
            emptyDataContainer.visible()
        } else {
            errorContainer.visible()
        }
    }

    override fun onPlayerClicked(player: PlayerItem) {
        activity?.let { DetailPlayerActivity.start(it, player.copy(strTeamFanart = teamItem?.strTeamFanart)) }
    }


}
