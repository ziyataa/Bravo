package com.reynaldiwijaya.bravo.presenter.table

import com.reynaldiwijaya.bravo.data.model.tableLeague.TableItem

interface TableView {
    fun showLoading()
    fun hideLoading()
    fun showTableData(data: List<TableItem>)
    fun showError(message: String)
}