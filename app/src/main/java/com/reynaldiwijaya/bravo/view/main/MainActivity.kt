package com.reynaldiwijaya.bravo.view.main

import androidx.fragment.app.Fragment
import com.reynaldiwijaya.bravo.R
import com.reynaldiwijaya.bravo.base.BaseActivity
import com.reynaldiwijaya.bravo.utils.ItemState
import com.reynaldiwijaya.bravo.view.adapter.BravoPagerAdapter
import com.reynaldiwijaya.bravo.view.item.ItemFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class MainActivity : BaseActivity() {

    private val pagerAdapter  by lazy {
        BravoPagerAdapter(
            supportFragmentManager,
            getMainFragments(),
            getTitlesTab()
        )
    }

    override val layoutResource: Int = R.layout.activity_main

    override fun initLib() {}

    override fun initIntent() {}

    override fun initUI() {
        setUpViewPager()
        setupToolbar(toolbar, getString(R.string.app_name), false)
    }

    override fun initAction() {}

    override fun initProcess() {}

    private fun setUpViewPager() {
        setUpViewPager(
            viewPager = vpMain,
            tabLayout = tabMain,
            adapter = pagerAdapter,
            swipeEnabled = false,
            offScreenPageLimit = 2
        )
    }

    private fun getTitlesTab(): List<String> {
        return listOf(
            getString(R.string.label_team),
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
