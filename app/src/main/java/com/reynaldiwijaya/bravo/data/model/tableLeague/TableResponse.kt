package com.reynaldiwijaya.bravo.data.model.tableLeague

import com.google.gson.annotations.SerializedName

data class TableResponse(
    @SerializedName("table")
    var table : List<TableItem>
)