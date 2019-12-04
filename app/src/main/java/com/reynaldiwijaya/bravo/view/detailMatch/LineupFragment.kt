package com.reynaldiwijaya.bravo.view.detailMatch


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.reynaldiwijaya.bravo.R
import com.reynaldiwijaya.bravo.data.model.match.MatchItem
import com.reynaldiwijaya.bravo.utils.Keys
import com.reynaldiwijaya.bravo.utils.emptyDataWithStrip
import com.reynaldiwijaya.bravo.utils.emptyString
import kotlinx.android.synthetic.main.fragment_lineup.*

class LineupFragment : Fragment() {

    companion object {
        fun newInstance(match: MatchItem): LineupFragment {
            val frag = LineupFragment()
            val bundle = Bundle()
            bundle.putParcelable(Keys.KEY_EXTRA_MATCH, match)
            frag.arguments = bundle

            return frag
        }
    }

    private var match: MatchItem? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        match = arguments?.getParcelable(Keys.KEY_EXTRA_MATCH)

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lineup, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
    }

    private fun initUI() {
        tvTeamNameHome.text = match?.teamNameHome
        tvTeamNameAway.text = match?.teamNameAway

        // Goalkeeper
        if (match?.homeLineupGoalKeeper != null) {
            val goalKeeperHome = match?.homeLineupGoalKeeper?.replace(";", "")
            tvLineupGoalkeeperHome.text = goalKeeperHome
        }
        if (match?.awayLineupGoalkeeper != null) {
            val goalKeeperAway = match?.awayLineupGoalkeeper?.replace(";", "")
            tvLineupGoalkeeperAway.text = goalKeeperAway
        }
        if (match?.homeLineupGoalKeeper == null) {
            tvLineupGoalkeeperHome.text = emptyDataWithStrip()
        }
        if (match?.awayLineupGoalkeeper == null) {
            tvLineupGoalkeeperAway.text = emptyDataWithStrip()
        }

        // Defense
        if (match?.homeLineupDefense != null) {
            val defenders = match?.homeLineupDefense?.split(";")
            var defender = emptyString()
            defenders?.forEach {
                defender += "$it\n"
            }
            tvLineupDefendersHome.text = defender
        }
        if (match?.awayLineupDefense != null) {
            val defenders = match?.awayLineupDefense?.split(";")
            var defender = emptyString()
            defenders?.forEach {
                defender += "$it\n"
            }
            tvLineupDefendersAway.text = defender
        }
        if (match?.homeLineupDefense == null) {
            tvLineupDefendersHome.text = emptyDataWithStrip()
        }
        if (match?.awayLineupDefense == null) {
            tvLineupDefendersAway.text = emptyDataWithStrip()
        }

        // Midfielders
        if (match?.homeLineupMidfield != null) {
            val midFielders = match?.homeLineupMidfield?.split(";")
            var midField = emptyString()
            midFielders?.forEach {
                midField += "$it\n"
            }
            tvLineupMidFieldersHome.text = midField
        }
        if (match?.awayLineupMidField != null) {
            val midFielders = match?.awayLineupMidField?.split(";")
            var midField = emptyString()
            midFielders?.forEach {
                midField += "$it\n"
            }
            tvLineupMidFieldersAway.text = midField
        }
        if (match?.homeLineupMidfield == null) {
            tvLineupMidFieldersHome.text = emptyDataWithStrip()
        }
        if (match?.awayLineupMidField == null) {
            tvLineupMidFieldersAway.text = emptyDataWithStrip()
        }

        // Forward
        if (match?.homeLineupForward != null) {
            val forwards = match?.homeLineupForward?.split(";")
            var forward = emptyString()
            forwards?.forEach {
                forward += "$it\n"
            }
            tvLineupForwardHome.text = forward
        }
        if (match?.awayLineupForward != null) {
            val forwards = match?.awayLineupForward?.split(";")
            var forward = emptyString()
            forwards?.forEach {
                forward += "$it\n"
            }
            tvLineupForwardAway.text = forward
        }
        if (match?.homeLineupForward == null) {
            tvLineupForwardHome.text = emptyDataWithStrip()
        }
        if (match?.awayLineupForward == null) {
            tvLineupForwardAway.text = emptyDataWithStrip()
        }

        // Substitutes
        if (match?.homeLineupSubstitutes != null) {
            val substitutes = match?.homeLineupSubstitutes?.split(";")
            var substitute = emptyString()
            substitutes?.forEach {
                substitute += "$it\n"
            }
            tvLineupSubstitutesHome.text = substitute
        }
        if (match?.awayLineupSubstitutes != null) {
            val substitutes = match?.awayLineupSubstitutes?.split(";")
            var substitute = emptyString()
            substitutes?.forEach {
                substitute += "$it\n"
            }
            tvLineupSubstitutesAway.text = substitute
        }
        if (match?.awayLineupSubstitutes == null) {
            tvLineupSubstitutesHome.text = emptyDataWithStrip()
        }
        if (match?.awayLineupSubstitutes == null) {
            tvLineupSubstitutesAway.text = emptyDataWithStrip()
        }

    }


}
