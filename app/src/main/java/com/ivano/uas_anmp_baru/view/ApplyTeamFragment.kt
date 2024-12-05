package com.ivano.uas_anmp_baru.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.ivano.uas_anmp_baru.R
import com.ivano.uas_anmp_baru.databinding.FragmentApplyTeamBinding
import com.ivano.uas_anmp_baru.model.AppliedTeam
import com.ivano.uas_anmp_baru.viewmodel.ApplyTeamViewModel

class ApplyTeamFragment : Fragment(), SpinnerListener {
    private lateinit var binding: FragmentApplyTeamBinding
    private lateinit var viewModel: ApplyTeamViewModel
    private lateinit var proposal: AppliedTeam

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_apply_team,
            container,
            false
        )
        viewModel = ViewModelProvider(this).get(ApplyTeamViewModel::class.java)
        proposal = AppliedTeam()
        binding.lifecycleOwner = viewLifecycleOwner

        binding.proposal = proposal
        binding.listener = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup Spinner Game
        val games = listOf("Pilih Game", "Game A", "Game B", "Game C")
        val gameAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            games
        )
        gameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.gameSpinner.adapter = gameAdapter

        // Initially disable the team spinner
        binding.teamSpinner.isEnabled = false

        // Setup Spinner Game listener
        binding.gameSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                // Enable team spinner only when a game other than the default is selected
                if (position > 0) {
                    // Setup Spinner Team
                    val teams = listOf("Pilih Team", "Team X", "Team Y", "Team Z")
                    val teamAdapter = ArrayAdapter(
                        requireContext(),
                        android.R.layout.simple_spinner_item,
                        teams
                    )
                    teamAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    binding.teamSpinner.adapter = teamAdapter
                    binding.teamSpinner.isEnabled = true
                } else {
                    // Disable team spinner and reset if no game is selected
                    binding.teamSpinner.isEnabled = false
                    binding.teamSpinner.setSelection(0)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                binding.teamSpinner.isEnabled = false
            }
        }

        binding.btnApply.setOnClickListener {
            val selectedGame = binding.gameSpinner.selectedItem.toString()
            val selectedTeam = binding.teamSpinner.selectedItem.toString()
            val alasanBergabung = binding.alasanBergabung.text.toString()

            if (selectedGame != "Pilih Game" && selectedTeam != "Pilih Team" && alasanBergabung.isNotBlank()) {
                val team = AppliedTeam(
                    game = selectedGame,
                    team = selectedTeam,
                    keterangan = alasanBergabung
                )
                viewModel.addTeam(listOf(team))
                Toast.makeText(view.context, "Data berhasil ditambahkan", Toast.LENGTH_LONG).show()
                Navigation.findNavController(it).popBackStack()
            } else {
                Toast.makeText(view.context, "Harap lengkapi semua field", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onSpinnerItemSelected(item: Any) {
        when (item) {
            is String -> {
                viewModel.updateSelectedItem(item)
            }
        }
    }
}