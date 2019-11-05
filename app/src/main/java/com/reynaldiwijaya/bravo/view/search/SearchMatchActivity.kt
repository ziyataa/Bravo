package com.reynaldiwijaya.bravo.view.search

import android.content.Context
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.reynaldiwijaya.bravo.R
import com.reynaldiwijaya.bravo.base.BaseActivity
import com.reynaldiwijaya.bravo.data.Injection
import com.reynaldiwijaya.bravo.data.model.match.MatchItem
import com.reynaldiwijaya.bravo.presenter.match.MatchView
import com.reynaldiwijaya.bravo.presenter.search.SearchPresenter
import com.reynaldiwijaya.bravo.utils.emptyData
import com.reynaldiwijaya.bravo.utils.emptyString
import com.reynaldiwijaya.bravo.utils.gone
import com.reynaldiwijaya.bravo.utils.visible
import com.reynaldiwijaya.bravo.view.adapter.MatchAdapter
import com.reynaldiwijaya.bravo.view.detailMatch.DetailMatchActivity
import kotlinx.android.synthetic.main.activity_search_match.*
import kotlinx.android.synthetic.main.layout_error.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity

class SearchMatchActivity : BaseActivity(), MatchAdapter.OnItemClick, MatchView {

    companion object {
        fun start(context : Context) {
            context.startActivity<SearchMatchActivity>()
        }
    }

    private var userInput = emptyString()

    private val matchAdapter : MatchAdapter by lazy {
        MatchAdapter(this)
    }

    private val presenter : SearchPresenter by lazy {
        SearchPresenter(this, Injection.provideApiRepository())
    }

    override val layoutResource: Int = R.layout.activity_search_match

    override fun initLib() {
    }

    override fun initIntent() {}

    override fun initUI() {
        setupToolbar(tbSearch, emptyString(), true)
        setupRecyclerView()
        setupSearchView()
    }

    override fun initAction() {
        layout_error.onClick {
            presenter.getSearchMatchData(userInput)
        }
    }

    override fun initProcess() {}

    private fun setupRecyclerView() {
        rvSearch?.apply {
            layoutManager = LinearLayoutManager(this@SearchMatchActivity)
            adapter = matchAdapter
        }
    }

    private fun setupSearchView() {
        searchMatch.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    userInput = it
                    presenter.getSearchMatchData(it)
                }
                hideSoftKeyboard(this@SearchMatchActivity)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
    }

    override fun showLoading() {
        rvSearch.gone()
        layout_error.gone()
        layout_empty_data.gone()
        layout_progress.visible()
    }

    override fun hideLoading() {
        layout_progress.gone()
    }

    override fun showMatchData(matchData: List<MatchItem>) {
        val matchesSoccer = arrayListOf<MatchItem>()

        rvSearch.visible()
        matchData.forEach {
            if(it.sport.equals(getString(R.string.label_soccer))){
                matchesSoccer.add(it)
            }
        }
        if (matchesSoccer.isNullOrEmpty()) {
            showError(emptyData())
        }else {
            matchAdapter.setData(matchesSoccer)
        }
    }

    override fun showError(message: String) {
        rvSearch.gone()
        if (message == emptyData()) {
            layout_empty_data.visible()
        } else {
            layout_error.visible()
        }
    }

    override fun onItemMatchClicked(match: MatchItem, state: String?) {
        match.idEvent?.let { DetailMatchActivity.start(this, it, state) }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

}
