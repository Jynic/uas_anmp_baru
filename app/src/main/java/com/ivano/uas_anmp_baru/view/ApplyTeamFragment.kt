package com.ivano.uas_anmp_baru.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.ivano.uas_anmp_baru.R
import com.ivano.uas_anmp_baru.databinding.FragmentApplyTeamBinding
import com.ivano.uas_anmp_baru.model.AppliedTeam
import com.ivano.uas_anmp_baru.viewmodel.ApplyTeamViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ApplyTeamFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ApplyTeamFragment : Fragment() {
    private lateinit var binding:FragmentApplyTeamBinding
    private lateinit var viewModel: ApplyTeamViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentApplyTeamBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ApplyTeamViewModel::class.java)

        val games = listOf("Game A", "Game B", "Game C")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, games)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.gameSpinner.adapter = adapter

        val teams = listOf("Team X", "Team Y", "Team Z")
        val teamAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            teams
        )
        teamAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.teamSpinner.adapter = teamAdapter

        binding.btnApply.setOnClickListener {
            var team = AppliedTeam(
                binding.gameSpinner.selectedItem.toString(),
                binding.teamSpinner.selectedItem.toString(),
                binding.alasanBergabung.text.toString()
            )
            var list = listOf(team)
            viewModel.addTeam(list)
            Toast.makeText(view.context, "Data added", Toast.LENGTH_LONG).show()
            Navigation.findNavController(it).popBackStack()

        }
    }
}