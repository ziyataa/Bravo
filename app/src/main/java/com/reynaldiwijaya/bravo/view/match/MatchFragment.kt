package com.reynaldiwijaya.bravo.view.match


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.reynaldiwijaya.bravo.R
import com.reynaldiwijaya.bravo.data.Injection
import com.reynaldiwijaya.bravo.data.model.match.MatchItem
import com.reynaldiwijaya.bravo.data.model.team.TeamItem
import com.reynaldiwijaya.bravo.presenter.match.MatchPresenter
import com.reynaldiwijaya.bravo.presenter.match.MatchView
import com.reynaldiwijaya.bravo.utils.Keys
import com.reynaldiwijaya.bravo.utils.MatchState
import com.reynaldiwijaya.bravo.utils.gone
import com.reynaldiwijaya.bravo.utils.visible
import com.reynaldiwijaya.bravo.view.adapter.MatchAdapter
import com.reynaldiwijaya.bravo.view.detailMatch.DetailMatchActivity
import kotlinx.android.synthetic.main.fragment_match.*
import org.jetbrains.anko.find

class MatchFragment : Fragment(), MatchView, MatchAdapter.OnItemClick {

    companion object {
        fun newInstance(state : String, idLeague : String) : MatchFragment {
            val frag = MatchFragment()
            val bundle = Bundle()
            bundle.putString(Keys.KEY_STATE_MATCH, state)
            bundle.putString(Keys.KEY_EXTRA_ID_LEAGUE, idLeague)
            frag.arguments = bundle

            return frag
        }
    }

    private val presenter : MatchPresenter by lazy {
        MatchPresenter(this, Injection.provideApiRepository())
    }

    private lateinit var matchAdapter : MatchAdapter

    private lateinit var rvMatch : RecyclerView
    private lateinit var srMatch : SwipeRefreshLayout

    private lateinit var state : String
    private lateinit var idLeague : String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_match, container, false)

        rvMatch = view.find(R.id.rvMatch)
        srMatch = view.find(R.id.srMatch)

        state = arguments?.getString(Keys.KEY_STATE_MATCH) as String
        idLeague = arguments?.getString(Keys.KEY_EXTRA_ID_LEAGUE) as String

        matchAdapter = MatchAdapter(this, state)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()

        getDataBasedOnState()

        srMatch.setOnRefreshListener {
            getDataBasedOnState()
        }
    }

    private fun getDataBasedOnState() {
        checkState(actionStateLastMatch = {
            getLastMatchesData()
        }, actionStateNextMatch = {
            getNextMatchesData()
        })
    }

    private fun getNextMatchesData() {
        presenter.getDataNextMatchLeague(idLeague)
    }

    private fun getLastMatchesData() {
        presenter.getDataLastMatchLeague(idLeague)
    }

    private fun initUI() {
        rvMatch.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = matchAdapter
        }
    }

    override fun showLoading() {
        layout_error.gone()
        progressMatch.visible()
    }

    override fun hideLoading() {
        srMatch.isRefreshing = false
        progressMatch.gone()
    }

    override fun showMatchData(matchData: List<MatchItem>) {
        matchAdapter.setData(matchData)
    }

    override fun showError(message: String) {
        layout_error.visible()
    }

    private fun checkState(
        actionStateNextMatch : (() -> Unit)? = null,
        actionStateLastMatch : (() -> Unit)? = null)
    {
        when(state) {
            MatchState.NEXT.state -> actionStateNextMatch?.invoke()
            MatchState.LAST.state -> actionStateLastMatch?.invoke()
        }
    }

    override fun onItemMatchClicked(match : MatchItem, state: String?) {
        context?.let { match.idEvent?.let { it1 -> DetailMatchActivity.start(it, it1, state) } }
    }

}
