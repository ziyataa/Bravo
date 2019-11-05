package com.reynaldiwijaya.bravo.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.reynaldiwijaya.bravo.R
import com.reynaldiwijaya.bravo.data.model.match.MatchItem
import com.reynaldiwijaya.bravo.utils.*
import kotlinx.android.synthetic.main.item_match.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import java.text.SimpleDateFormat

class MatchAdapter(private val onItemClick: OnItemClick, private val state: String? = null)
    : RecyclerView.Adapter<MatchAdapter.ViewHolder>() {

    private val items = mutableListOf<MatchItem>()

    fun setData(data : List<MatchItem>) {
        this.items.clear()
        this.items.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_match, parent, false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val match = items[position]
        holder.bindItem(match, onItemClick, state)
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SimpleDateFormat")
        fun bindItem(data : MatchItem, onItemClick: OnItemClick, state : String? = null) {
            with(itemView) {
                var dateMatch : String? = null
                var timeMatch : String? = null

                if (data.dateMatch != null || data.time != null) {
                    // Date
                    val date = strToDate(data.dateMatch)
                    dateMatch = changeFormatDate(date)
                    tvMatchDate.text = dateMatch

                    //time
                    val dateTime = data.time?.let { data.dateMatch?.let { it1 -> toGMTFormat(it1, it) } }
                    if (dateTime != null) {
                        timeMatch =  SimpleDateFormat(itemView.context.getString(R.string.label_format_time))
                            .format(dateTime)
                        tvMatchTime.text = timeMatch
                    }

                } else {
                    tvMatchDate.text = emptyScoreMatch()
                    tvMatchTime.text = emptyScoreMatch()
                }


                tvTeamNameHome.text = data.teamNameHome
                tvTeamNameAway.text = data.teamNameAway
                if (data.homeScore != null) tvHomeScore.text = data.homeScore
                else tvHomeScore.text = emptyScoreMatch()
                if (data.awayScore != null) tvAwaycore.text = data.awayScore
                else tvAwaycore.text = emptyScoreMatch()

                onClick {
                    onItemClick.onItemMatchClicked(data.copy(dateMatch = dateMatch, time = timeMatch), state)
                }
            }
        }
    }

    interface OnItemClick {
        fun onItemMatchClicked(match : MatchItem, state : String?)
    }
}