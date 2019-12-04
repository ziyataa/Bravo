package com.reynaldiwijaya.bravo.view.detailPlayer

import android.content.Context
import android.view.MenuItem
import com.reynaldiwijaya.bravo.R
import com.reynaldiwijaya.bravo.base.BaseActivity
import com.reynaldiwijaya.bravo.data.model.player.PlayerItem
import com.reynaldiwijaya.bravo.utils.Keys
import com.reynaldiwijaya.bravo.utils.emptyDataWithStrip
import com.reynaldiwijaya.bravo.utils.loadImageUrl
import kotlinx.android.synthetic.main.activity_detail_player.*
import org.jetbrains.anko.startActivity

class DetailPlayerActivity : BaseActivity() {

    companion object {
        fun start(context : Context, player : PlayerItem) {
            context.startActivity<DetailPlayerActivity>(Keys.KEY_EXTRA_PLAYER to player)
        }
    }

    private lateinit var player: PlayerItem

    override val layoutResource: Int = R.layout.activity_detail_player

    override fun initLib() {}

    override fun initIntent() {
        player = intent.getParcelableExtra(Keys.KEY_EXTRA_PLAYER)
    }

    override fun initUI() {
        player.strPlayer?.let { setupToolbar(tbDetailPlayer, it, true) }
        setUpView()
    }

    override fun initAction() {}

    override fun initProcess() {}

    private fun setUpView() {
        player.strTeamFanart?.let { imgBackground.loadImageUrl(this, it) }
        tvPlayerName.text = player.strPlayer
        player.strCutout?.let { imgPlayer.loadImageUrl(this, it) }
        tvTeamName.text = player.strTeam ?: emptyDataWithStrip()
        tvPlayerName2.text = player.strPlayer ?: emptyDataWithStrip()
        tvPlayerNationality.text = player.strNationality ?: emptyDataWithStrip()
        tvPlayerTeam.text = player.strTeam ?: emptyDataWithStrip()
        tvPlayerDateBorn.text = player.dateborn ?: emptyDataWithStrip()
        tvPlayerNumber.text = player.strNumber ?: emptyDataWithStrip()
        tvPlayerDateSigned.text = player.dateSigned ?: emptyDataWithStrip()
        tvPlayerSigning.text = player.strSigning ?: emptyDataWithStrip()
        tvPlayerWage.text = player.strWage ?: emptyDataWithStrip()
        tvPlayerBirthLocation.text = player.strBirthLocation ?: emptyDataWithStrip()
        tvPlayerDesc.text = player.strDescriptionEN ?: emptyDataWithStrip()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finishActivity()
        }
        return super.onOptionsItemSelected(item)
    }

}
