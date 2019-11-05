package com.reynaldiwijaya.bravo.view.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.reynaldiwijaya.bravo.R
import com.reynaldiwijaya.bravo.data.model.league.LeagueItem
import com.reynaldiwijaya.bravo.data.model.team.TeamItem
import com.reynaldiwijaya.bravo.utils.ItemState
import com.reynaldiwijaya.bravo.utils.loadImageUrl
import com.reynaldiwijaya.bravo.view.ankoUi.TeamItemUI
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk27.coroutines.onClick

class ItemAdapter(private var state: String, val onItemClick: OnItemClick) :
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    private val teamsData = mutableListOf<TeamItem>()
    private val leaguesData = mutableListOf<LeagueItem>()

    fun setDataTeam(datas : List<TeamItem>) {
        this.teamsData.clear()
        this.teamsData.addAll(datas)
        notifyDataSetChanged()
    }

    fun setDataLeague(datas : List<LeagueItem>) {
        this.leaguesData.clear()
        this.leaguesData.addAll(datas)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return when(state) {
            ItemState.TEAMS.state -> TeamViewHolder(TeamItemUI().createView(AnkoContext.create(parent.context, parent)))
            else -> LeagueViewHolder(TeamItemUI().createView(AnkoContext.create(parent.context, parent)))
        }
    }

    override fun getItemCount(): Int {
        return when(state) {
            ItemState.TEAMS.state -> teamsData.size
            else -> leaguesData.size
        }
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        when(state) {
            ItemState.TEAMS.state -> {
                val team = teamsData[position]
                (holder as TeamViewHolder).bindItem(team)
            }
            else -> {
                val league = leaguesData[position]
                (holder as LeagueViewHolder).bindItem(league)
            }
        }
    }

    open inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName : TextView = view.find(R.id.team_name)
        val imgLogo : ImageView = view.find(R.id.team_badge)
    }

    inner class TeamViewHolder(view: View) : ItemViewHolder(view) {
        fun bindItem(data : TeamItem) {
            tvName.text = data.teamName
            data.teamBadge?.let { imgLogo.loadImageUrl(itemView.context, it) }
            itemView.onClick {
                onItemClick.onItemTeamClicked(data)
            }
        }
    }

    inner class LeagueViewHolder(view: View) : ItemViewHolder(view) {
        fun bindItem(data : LeagueItem) {
            data.leagueBadge?.let { imgLogo.loadImageUrl(itemView.context, it) }
            tvName.text = data.leagueName
            itemView.onClick {
                onItemClick.onItemLeagueClicked(data)
            }
        }
    }

    interface OnItemClick {
        fun onItemLeagueClicked(league : LeagueItem)
        fun onItemTeamClicked(team : TeamItem)
    }

}


