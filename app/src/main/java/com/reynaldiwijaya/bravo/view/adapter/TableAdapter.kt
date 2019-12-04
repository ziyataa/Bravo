package com.reynaldiwijaya.bravo.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.reynaldiwijaya.bravo.R
import com.reynaldiwijaya.bravo.data.model.tableLeague.TableItem
import kotlinx.android.synthetic.main.item_table.view.*

class TableAdapter : RecyclerView.Adapter<TableAdapter.TableViewHolder>() {

    private val tableData = mutableListOf<TableItem>()

    fun setData(data : List<TableItem>) {
        tableData.clear()
        tableData.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableViewHolder {
        return TableViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_table, parent, false))
    }

    override fun getItemCount(): Int = tableData.size

    override fun onBindViewHolder(holder: TableViewHolder, position: Int) {
        val table = tableData[position]

        holder.itemView.tvNumber.text = (position+1).toString()

        holder.bindItem(table)
    }

    class TableViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        fun bindItem(table : TableItem) {
            with(itemView) {
                tvTeamName.text = table.teamName
                tvPlayed.text = table.played
                tvGoalsDifferent.text = table.goalsDifference
                tvPoints.text = table.total
            }
        }
    }
}