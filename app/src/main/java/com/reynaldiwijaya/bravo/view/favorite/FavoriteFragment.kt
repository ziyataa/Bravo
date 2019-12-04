package com.reynaldiwijaya.bravo.view.favorite


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.reynaldiwijaya.bravo.R
import com.reynaldiwijaya.bravo.base.BaseActivity
import com.reynaldiwijaya.bravo.utils.ItemState
import com.reynaldiwijaya.bravo.utils.MatchState
import com.reynaldiwijaya.bravo.view.adapter.BravoPagerAdapter
import com.reynaldiwijaya.bravo.view.item.ItemFragment
import com.reynaldiwijaya.bravo.view.match.MatchFragment
import kotlinx.android.synthetic.main.fragment_favorite.*

class FavoriteFragment : Fragment() {

    private var baseActivity: BaseActivity? = null

    private val pagerAdapter: BravoPagerAdapter by lazy {
        BravoPagerAdapter(
            childFragmentManager,
            getFragments(),
            getTitles()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity) {
            val activity = context as BaseActivity?
            this.baseActivity = activity
        }
    }

    override fun onDetach() {
        baseActivity = null
        super.onDetach()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        baseActivity?.setUpViewPager(
            viewPager = vpFavorite,
            tabLayout = tabFavorite,
            adapter = pagerAdapter,
            swipeEnabled = false,
            offScreenPageLimit = 3
        )
    }

    private fun getFragments(): List<Fragment> {
        return listOf(
            MatchFragment.newInstance(MatchState.LAST.state, MatchState.FAVORITE.state),
            MatchFragment.newInstance(MatchState.NEXT.state, MatchState.FAVORITE.state),
            ItemFragment.newInstance(ItemState.TEAMS_FAVORITE.state)
        )
    }

    private fun getTitles(): List<String> {
        return listOf(
            getString(R.string.label_last_match),
            getString(R.string.label_next_match),
            getString(R.string.label_teams)
        )
    }


}
