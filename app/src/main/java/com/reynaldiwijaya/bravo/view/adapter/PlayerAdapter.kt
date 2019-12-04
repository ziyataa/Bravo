package com.reynaldiwijaya.bravo.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.reynaldiwijaya.bravo.R
import com.reynaldiwijaya.bravo.data.model.player.PlayerItem
import com.reynaldiwijaya.bravo.utils.loadImageUrl
import kotlinx.android.synthetic.main.item_player.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class PlayerAdapter(private val onPlayerClick: OnPlayerClick)
    : RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>() {

    private val mData = mutableListOf<PlayerItem>()

    fun setData(players : List<PlayerItem>) {
        mData.clear()
        mData.addAll(players)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        return PlayerViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_player, parent, false))
    }

    override fun getItemCount(): Int = mData.size

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val player = mData[position]
        holder.bindItem(player, onPlayerClick)
    }

    inner class PlayerViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        fun  bindItem(player : PlayerItem, onPlayerClick: OnPlayerClick) {
            with(itemView) {
                player.strCutout?.let { imgPlayer.loadImageUrl(itemView.context, it) }
                tvPlayerName.text = player.strPlayer
            }
            itemView.onClick {
                onPlayerClick.onPlayerClicked(player)
            }
        }
    }

    interface OnPlayerClick {
        fun onPlayerClicked(player : PlayerItem)
    }
}