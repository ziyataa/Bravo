package com.reynaldiwijaya.bravo.view.standings


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.reynaldiwijaya.bravo.R
import com.reynaldiwijaya.bravo.data.Injection
import com.reynaldiwijaya.bravo.data.model.tableLeague.TableItem
import com.reynaldiwijaya.bravo.presenter.table.TablePresenter
import com.reynaldiwijaya.bravo.presenter.table.TableView
import com.reynaldiwijaya.bravo.utils.Keys
import com.reynaldiwijaya.bravo.utils.gone
import com.reynaldiwijaya.bravo.utils.visible
import com.reynaldiwijaya.bravo.view.adapter.TableAdapter
import kotlinx.android.synthetic.main.fragment_standings.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class StandingsFragment : Fragment(), TableView {

    companion object {
        fun newInstance(leagueId : String, leagueName : String) : StandingsFragment {
            val frag = StandingsFragment()
            val bundle = Bundle()
            bundle.putString(Keys.KEY_EXTRA_ID_LEAGUE, leagueId)
            bundle.putString(Keys.KEY_EXTRA_LEAGUE_NAME, leagueName)
            frag.arguments = bundle

            return frag
        }
    }

    private lateinit var leagueId : String
    private lateinit var leagueName: String

    private val tableAdapter : TableAdapter by lazy {
        TableAdapter()
    }
    private val presenter : TablePresenter by lazy {
        TablePresenter(this, Injection.provideApiRepository())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_standings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        leagueName = arguments?.getString(Keys.KEY_EXTRA_LEAGUE_NAME).toString()
        leagueId = arguments?.getString(Keys.KEY_EXTRA_ID_LEAGUE).toString()

        initUI()
        initAction()
        initProcess()
    }

    private fun initUI() {
        rvTable.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = tableAdapter
        }
        tvLeagueName.text = leagueName
    }

    private fun initAction() {
        layout_error.onClick { initProcess() }
    }


    private fun initProcess() {
        presenter.getTableDataByLeagueId(leagueId)
    }


    override fun showLoading() {
        layout_progress.visible()
        themeTableContainer.gone()
        rvTable.gone()
        layout_error.gone()
    }

    override fun hideLoading() {
        layout_progress.gone()
    }

    override fun showTableData(data: List<TableItem>) {
        themeTableContainer.visible()
        rvTable.visible()
        tableAdapter.setData(data)
    }

    override fun showError(message: String) {
        layout_error.visible()
    }

}
