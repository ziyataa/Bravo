package com.reynaldiwijaya.bravo.base

import android.app.Activity
import android.os.Bundle
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.tabs.TabLayout
import com.reynaldiwijaya.bravo.utils.custom.TeamViewPager
import com.reynaldiwijaya.bravo.view.adapter.BravoPagerAdapter

abstract class BaseActivity : AppCompatActivity(), BaseView {

    protected abstract val layoutResource: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        setContentView(layoutResource)

        onViewReady()
    }

    override fun setupToolbar(toolbar: Toolbar?, title: String, isChild: Boolean) {
        toolbar?.let {
            setSupportActionBar(toolbar)
        }

        if (supportActionBar != null) {
            supportActionBar!!.title = title
            supportActionBar!!.setDisplayHomeAsUpEnabled(isChild)
        }
    }

    override fun setupToolbar(toolbar: Toolbar?, isChild: Boolean) {
        toolbar?.let {
            setSupportActionBar(toolbar)
        }

        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(isChild)
        }
    }

    override fun setupToolbar(title: String, isChild: Boolean) {
        if (supportActionBar != null) {
            supportActionBar!!.title = title
            supportActionBar!!.setDisplayHomeAsUpEnabled(isChild)
        }
    }

    override fun finishActivity() {
        finish()
    }

    override fun setUpViewPager(
        viewPager: TeamViewPager,
        tabLayout: TabLayout,
        adapter: BravoPagerAdapter,
        swipeEnabled: Boolean?,
        offScreenPageLimit: Int
    ) {
        tabLayout.tabIconTint = null

        viewPager.adapter = adapter
        if (swipeEnabled == null) viewPager.swipeEnabled = false
        else viewPager.swipeEnabled = swipeEnabled
        viewPager.offscreenPageLimit = offScreenPageLimit
        tabLayout.setupWithViewPager(viewPager)
    }

    override fun hideSoftKeyboard(activity: Activity) {
        val inputMethodManager =
            activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(
            activity.currentFocus!!.windowToken, 0
        )
    }

    private fun onViewReady() {
        initLib()
        initIntent()
        initUI()
        initAction()
        initProcess()
    }

    //    Init Presenter and Component Injection here
    protected abstract fun initLib()

    //    Extract desired intent here
    protected abstract fun initIntent()

    //    initialize the UI, setup toolbar, setText etc here
    protected abstract fun initUI()

    //    initialize UI interaction here
    protected abstract fun initAction()

    //    initialize main Process here e.g call presenter to load data
    protected abstract fun initProcess()
}