package com.reynaldiwijaya.bravo.view.teamAndLeague


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.reynaldiwijaya.bravo.R
import com.reynaldiwijaya.bravo.base.BaseActivity
import com.reynaldiwijaya.bravo.utils.ItemState
import com.reynaldiwijaya.bravo.view.adapter.BravoPagerAdapter
import com.reynaldiwijaya.bravo.view.item.ItemFragment
import kotlinx.android.synthetic.main.fragment_team_league.*

class TeamLeagueFragment : Fragment() {

    private var baseActivity: BaseActivity? = null

    private val pagerAdapter by lazy {
        BravoPagerAdapter(
            childFragmentManager,
            getMainFragments(),
            getTitlesTab()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_team_league, container, false)
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
        setUpViewPager()
    }

    private fun setUpViewPager() {
        baseActivity?.setUpViewPager(
            viewPager = vpMain,
            tabLayout = tabMain,
            adapter = pagerAdapter,
            swipeEnabled = false,
            offScreenPageLimit = 2
        )
    }

    private fun getTitlesTab(): List<String> {
        return listOf(
            getString(R.string.label_teams),
            getString(R.string.label_league)
        )
    }

    private fun getMainFragments(): List<Fragment> {
        return listOf(
            ItemFragment.newInstance(ItemState.TEAMS.state),
            ItemFragment.newInstance(ItemState.LEAGUES.state)
        )
    }


}
