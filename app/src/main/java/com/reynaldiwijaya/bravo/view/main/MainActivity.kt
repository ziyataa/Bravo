package com.reynaldiwijaya.bravo.view.main

import android.os.Bundle
import com.reynaldiwijaya.bravo.R
import com.reynaldiwijaya.bravo.base.BaseActivity
import com.reynaldiwijaya.bravo.view.favorite.FavoriteFragment
import com.reynaldiwijaya.bravo.view.teamAndLeague.TeamLeagueFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class MainActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        bottomNavMain.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.teams -> {
                    loadTeamsAndLeagueFragment(savedInstanceState)
                }
                R.id.favorites -> {
                    loadFavoritesFragment(savedInstanceState)
                }
            }
            true
        }

        bottomNavMain.selectedItemId = R.id.teams
    }

    override val layoutResource: Int = R.layout.activity_main

    override fun initLib() {}

    override fun initIntent() {}

    override fun initUI() {
        setupToolbar(toolbar, getString(R.string.app_name), false)
    }

    override fun initAction() {
    }

    override fun initProcess() {}

    private fun loadFavoritesFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.mainContainer,
                    FavoriteFragment(), FavoriteFragment::class.java.simpleName
                )
                .commit()
        }
    }

    private fun loadTeamsAndLeagueFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.mainContainer,
                    TeamLeagueFragment(),
                    TeamLeagueFragment::class.java.simpleName
                )
                .commit()
        }
    }
}
