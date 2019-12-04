package com.reynaldiwijaya.bravo.view.detailTeam


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.reynaldiwijaya.bravo.R
import com.reynaldiwijaya.bravo.utils.Keys
import kotlinx.android.synthetic.main.fragment_team_desc.*

class TeamDescFragment : Fragment() {

    companion object {
        fun newInstance(desc : String?) : TeamDescFragment {
            val frag = TeamDescFragment()
            val bundle = Bundle()
            bundle.putString(Keys.KEY_EXTRA_DESC, desc)
            frag.arguments = bundle

            return frag
        }
    }

    private lateinit var teamDesc : String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_team_desc, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        teamDesc = arguments?.getString(Keys.KEY_EXTRA_DESC).toString()

        tvTeamDesc.text = teamDesc
    }


}
