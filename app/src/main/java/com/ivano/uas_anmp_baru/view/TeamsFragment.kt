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
import com.ivano.uas_anmp_baru.databinding.FragmentTeamsBinding
import com.ivano.uas_anmp_baru.viewmodel.TeamViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [TeamsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TeamsFragment : Fragment() {
    private lateinit var binding: FragmentTeamsBinding
    private lateinit var viewModel: TeamViewModel
    private var gameId = 0
    private val teamListAdapter = TeamListAdapter(arrayListOf(), gameId)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_teams, container, false)
        binding = FragmentTeamsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(arguments != null) {
            gameId =
                TeamsFragmentArgs.fromBundle(requireArguments()).gameId

            binding.RecViewTeams.layoutManager = LinearLayoutManager(context)
            binding.RecViewTeams.adapter = teamListAdapter
        }

        Log.d("gameId", gameId.toString())

        viewModel = ViewModelProvider(this).get(TeamViewModel::class.java)
        viewModel.fetchTeams()
        viewModel.getTeamById(gameId)

        observeTeam()
    }

    fun observeTeam(){
        viewModel.teamsData.observe(viewLifecycleOwner, Observer {
            teamListAdapter.updateTeamList(it)
        })

    }

}