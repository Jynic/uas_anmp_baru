package com.ivano.uas_anmp_baru.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ivano.uas_anmp_baru.R
import com.ivano.uas_anmp_baru.databinding.FragmentTeamDetailBinding
import com.ivano.uas_anmp_baru.databinding.MemberListItemBinding
import com.ivano.uas_anmp_baru.viewmodel.TeamViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TeamDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TeamDetailFragment : Fragment() {
    private lateinit var binding: FragmentTeamDetailBinding
    private lateinit var viewModelTeam: TeamViewModel
    private val memberListAdapter = MemberListAdapter(arrayListOf())
    private var teamId = 0
    private var gameId = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_team_detail, container, false)
        binding = FragmentTeamDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recViewMember.layoutManager = LinearLayoutManager(context)
        binding.recViewMember.adapter = memberListAdapter

        viewModelTeam = ViewModelProvider(this).get(TeamViewModel::class.java)

        if(arguments != null) {
            teamId =
                TeamDetailFragmentArgs.fromBundle(requireArguments()).teamId
            gameId =
                TeamDetailFragmentArgs.fromBundle(requireArguments()).gameId

            Log.d("TeamId", teamId.toString())

        }
        viewModelTeam.fetchTeams()
        viewModelTeam.getMembersByTeamId(teamId)

        observeTeam()
        observeMember()
    }

    fun observeMember(){
        viewModelTeam.membersLD.observe(viewLifecycleOwner, Observer {
            memberListAdapter.updateMemberList(it)
        })
    }

    fun observeTeam(){
        viewModelTeam.teamsData.observe(viewLifecycleOwner, Observer {
            binding.txtTeamName.setText(it[0].name)
        })

    }
}