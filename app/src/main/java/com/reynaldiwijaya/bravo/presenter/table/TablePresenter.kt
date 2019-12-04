package com.reynaldiwijaya.bravo.presenter.table

import com.reynaldiwijaya.bravo.data.ApiRepository
import com.reynaldiwijaya.bravo.data.DataSource
import com.reynaldiwijaya.bravo.data.model.tableLeague.TableItem

class TablePresenter(private val mView : TableView,
                     private val repository: ApiRepository) {

    fun getTableDataByLeagueId(leagueId : String) {
        repository.getDataTableTeamByLeagueId(leagueId, object : DataSource.GetDataTableTeamCallback{
            override fun onShowProgressDialog() {
                mView.showLoading()
            }

            override fun onHideProgressDialog() {
                mView.hideLoading()
            }

            override fun onSuccess(data: List<TableItem>) {
                mView.showTableData(data)
            }

            override fun onFailed(errorMessage: String?) {
                errorMessage?.let { mView.showError(it) }
            }

        })
    }

}