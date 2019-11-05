package com.reynaldiwijaya.bravo.view.detailMatch


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.reynaldiwijaya.bravo.R
import com.reynaldiwijaya.bravo.data.model.match.MatchItem
import com.reynaldiwijaya.bravo.utils.Keys
import com.reynaldiwijaya.bravo.utils.emptyScoreMatch
import com.reynaldiwijaya.bravo.utils.emptyString
import kotlinx.android.synthetic.main.fragment_statistic.*

class StatisticFragment : Fragment() {

    companion object {
        fun newInstance(match: MatchItem): StatisticFragment {
            val frag = StatisticFragment()
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

        return inflater.inflate(R.layout.fragment_statistic, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
    }

    private fun initUI() {

        tvLeagueName.text = match?.leagueName
        tvMatchTime.text = match?.time
        tvMatchDate.text = match?.dateMatch

        // Goals
        if (match?.homeGoalDetails != null) {
            val homeGoals = match?.homeGoalDetails?.split(";")
            var homeGoal = emptyString()
            homeGoals?.forEach {
                homeGoal += "$it\n"
            }
            tvHomeGoals.text = homeGoal
        }
        if (match?.awayGoalDetails != null) {
            val awayGoals = match?.awayGoalDetails?.split(";")
            var awayGoal = emptyString()
            awayGoals?.forEach {
                awayGoal += "$it\n"
            }
            tvAwayGoals.text = awayGoal
        }
        if (match?.homeGoalDetails == null) {
            tvHomeGoals.text = emptyScoreMatch()
        }
        if (match?.awayGoalDetails == null) {
            tvAwayGoals.text = emptyScoreMatch()
        }

        // Redcards
        if (match?.homeRedCards != null) {
            val homeRedCards = match?.homeRedCards?.split(";")
            var homeRedCard = emptyString()
            homeRedCards?.forEach {
                homeRedCard += "$it\n"
            }
            tvRedCardsHome.text = homeRedCard

        }
        if (match?.awayRedCards != null) {
            val awayRedCards = match?.awayRedCards?.split(";")
            var awayRedCard = emptyString()
            awayRedCards?.forEach {
                awayRedCard += "$it\n"
            }
            tvRedCardsAway.text = awayRedCard
        }
        if (match?.homeRedCards == null) {
            tvRedCardsHome.text = emptyScoreMatch()
        }
        if (match?.awayRedCards == null) {
            tvRedCardsAway.text = emptyScoreMatch()
        }

        // Yellow Cards
        if (match?.homeYellowCards != null) {
            val homeYellowCards = match?.homeYellowCards?.split(";")
            var homeYellowCard = emptyString()
            homeYellowCards?.forEach {
                homeYellowCard += "$it\n"
            }
            tvYellowCardsHome.text = homeYellowCard
        }
        if (match?.awayYellowCards != null) {
            val awayYellowCards = match?.awayYellowCards?.split(";")
            var awayYellowCard = emptyString()
            awayYellowCards?.forEach {
                awayYellowCard += "$it\n"
            }
            tvYellowCardsAway.text = awayYellowCard
        }
        if (match?.homeYellowCards != null) {
            tvYellowCardsHome.text = emptyScoreMatch()
        }
        if (match?.awayYellowCards == null) {
            tvYellowCardsAway.text = emptyScoreMatch()
        }

        // Shots
        if (match?.homeShots != null) {
            tvShotsHome.text = match?.homeShots
        }
        if (match?.awayShots != null) {
            tvShotsAway.text = match?.awayShots
        }
        if (match?.homeShots == null) {
            tvShotsHome.text = emptyScoreMatch()
        }

        if (match?.awayShots == null) {
            tvShotsAway.text = emptyScoreMatch()
        }
    }


}
