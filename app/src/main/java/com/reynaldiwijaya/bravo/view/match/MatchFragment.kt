package com.reynaldiwijaya.bravo.view.match

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.reynaldiwijaya.bravo.R
import com.reynaldiwijaya.bravo.data.Injection
import com.reynaldiwijaya.bravo.data.model.favorite.FavoriteMatch
import com.reynaldiwijaya.bravo.data.model.match.MatchItem
import com.reynaldiwijaya.bravo.presenter.match.MatchPresenter
import com.reynaldiwijaya.bravo.presenter.match.MatchView
import com.reynaldiwijaya.bravo.utils.*
import com.reynaldiwijaya.bravo.view.adapter.MatchAdapter
import com.reynaldiwijaya.bravo.view.detailMatch.DetailMatchActivity
import kotlinx.android.synthetic.main.fragment_match.*
import org.jetbrains.anko.find

class MatchFragment : Fragment(), MatchView, MatchAdapter.OnItemClick {

    companion object {
        fun newInstance(
            stateLastOrNext: String,
            stateApiOrFavorite: String,
            idLeague: String = emptyString()
        ): MatchFragment {
            val frag = MatchFragment()
            val bundle = Bundle()
            bundle.putString(Keys.KEY_STATE_MATCH, stateLastOrNext)
            bundle.putString(Keys.KEY_EXTRA_ID_LEAGUE, idLeague)
            bundle.putString(Keys.KEY_STATE_MATCH_API_OR_FAVORITE, stateApiOrFavorite)
            frag.arguments = bundle

            return frag
        }
    }

    private val presenter: MatchPresenter by lazy {
        MatchPresenter(this, Injection.provideApiRepository())
    }

    private lateinit var matchAdapter: MatchAdapter

    private lateinit var rvMatch: RecyclerView
    private lateinit var srMatch: SwipeRefreshLayout


    private lateinit var state: String
    private lateinit var stateApiOrFavorite: String
    private lateinit var idLeague: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_match, container, false)

        rvMatch = view.find(R.id.rvMatch)
        srMatch = view.find(R.id.srMatch)

        stateApiOrFavorite = arguments?.getString(Keys.KEY_STATE_MATCH_API_OR_FAVORITE) as String
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

    override fun onResume() {
        super.onResume()
        checkStateApiOrLocal(actionStateLocal = {
            getDataBasedOnState()
        })
    }

    private fun getDataBasedOnState() {
        checkStateApiOrLocal(actionStateApi = {
            checkStateLastOrNextMatch(actionStateLastMatch = {
                getLastMatchesData()
            }, actionStateNextMatch = {
                getNextMatchesData()
            })
        }, actionStateLocal = {
            checkStateLastOrNextMatch(actionStateLastMatch = {
                getLastMatchesDataLocal()
            }, actionStateNextMatch = {
                getNextMatchesDataLocal()
            })
        })
    }

    private fun getNextMatchesDataLocal() {
        context?.let { presenter.getLocalDataNextMatch(it) }
    }

    private fun getLastMatchesDataLocal() {
        context?.let { presenter.getLocalDataLastMatch(it) }
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
        layout_empty_data.gone()
        layout_error.gone()
        rvMatch.gone()
        progressMatch.visible()
    }

    override fun hideLoading() {
        srMatch.isRefreshing = false
        progressMatch.gone()
    }

    override fun showLocalMatchData(matchData: List<FavoriteMatch>) {
        if (matchData.isNullOrEmpty()) {
            layout_empty_data.visible()
            rvMatch.gone()
        } else {
            rvMatch.visible()
            matchAdapter.setData(matchData.map { it.toMatchItem() })
        }
    }

    override fun showMatchData(matchData: List<MatchItem>) {
        if (matchData.isNullOrEmpty()) {
            layout_empty_data.visible()
            rvMatch.gone()
        } else {
            rvMatch.visible()
            matchAdapter.setData(matchData)
        }

        Log.d("Aldi123", matchData.toString())
    }

    override fun showError(message: String) {
        rvMatch.gone()
        layout_error.visible()
    }

    override fun showSuccessAddToLocal() {
        // Do Nothing
    }

    override fun showSuccessRemoveFromLocal() {
        //Do Nothing
    }

    private fun checkStateLastOrNextMatch(
        actionStateNextMatch: (() -> Unit)? = null,
        actionStateLastMatch: (() -> Unit)? = null
    ) {
        when (state) {
            MatchState.NEXT.state -> actionStateNextMatch?.invoke()
            MatchState.LAST.state -> actionStateLastMatch?.invoke()
        }
    }

    private fun checkStateApiOrLocal(
        actionStateApi: (() -> Unit)? = null,
        actionStateLocal: (() -> Unit)? = null
    ) {
        when (stateApiOrFavorite) {
            MatchState.API.state -> actionStateApi?.invoke()
            MatchState.FAVORITE.state -> actionStateLocal?.invoke()
        }
    }

    override fun onItemMatchClicked(match: MatchItem, state: String?) {
        context?.let { match.idEvent?.let { it1 -> DetailMatchActivity.start(it, it1, state) } }
    }

}
