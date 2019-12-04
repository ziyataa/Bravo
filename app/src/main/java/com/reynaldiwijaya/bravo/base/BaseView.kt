package com.reynaldiwijaya.bravo.base

import android.app.Activity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.tabs.TabLayout
import com.reynaldiwijaya.bravo.utils.custom.TeamViewPager
import com.reynaldiwijaya.bravo.view.adapter.BravoPagerAdapter

interface BaseView {

    fun setupToolbar(toolbar: Toolbar?, title: String, isChild: Boolean)

    fun setupToolbar(toolbar: Toolbar?, isChild: Boolean)

    fun setupToolbar(title: String, isChild: Boolean)

    fun finishActivity()

    fun setUpViewPager(
        viewPager: TeamViewPager, tabLayout: TabLayout,
        adapter: BravoPagerAdapter, swipeEnabled: Boolean?,
        offScreenPageLimit: Int
    )

    fun hideSoftKeyboard(activity: Activity)

}