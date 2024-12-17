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
import com.ivano.uas_anmp_baru.viewmodel.GameViewModel
import com.ivano.uas_anmp_baru.viewmodel.TeamViewModel

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
        val gameViewModel = ViewModelProvider(this).get(GameViewModel::class.java)
        val teamViewModel = ViewModelProvider(this).get(TeamViewModel::class.java)
        gameViewModel.fetchGames()
        teamViewModel.fetchTeams()
        gameViewModel.gamesLD.observe(viewLifecycleOwner) { games ->
            val gameNames = mutableListOf("Pilih Game") // Tambahkan "Pilih Game" sebagai default
            gameNames.addAll(games.map { it.name ?: "Unknown" })

            // Set adapter for spinner
            val gameAdapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item,
                gameNames
            )
            gameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.gameSpinner.adapter = gameAdapter
        }

        // Initially disable the team spinner
        binding.teamSpinner.isEnabled = false

        // Setup Spinner Game listener
        binding.gameSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                // Enable team spinner only when a game other than the default is selected
                if (position > 0) {
                    // Setup Spinner Team
                    teamViewModel.teamsLD.observe(viewLifecycleOwner) { teams ->
                        // Buat daftar nama tim dari data yang diterima
                        val teamNames = mutableListOf("Pilih Tim") // Tambahkan opsi default
                        teamNames.addAll(teams.map { it.name }) // Mengambil nama tim dari daftar

                        // Buat ArrayAdapter untuk Spinner
                        val teamAdapter = ArrayAdapter(
                            requireContext(),
                            android.R.layout.simple_spinner_item,
                            teamNames
                        )
                        teamAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

                        // Set adapter ke Spinner
                        binding.teamSpinner.adapter = teamAdapter
                    }
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
            val status = "Waiting";

            if (selectedGame != "Pilih Game" && selectedTeam != "Pilih Team" && alasanBergabung.isNotBlank()) {
                val team = AppliedTeam(
                    game = selectedGame,
                    team = selectedTeam,
                    keterangan = alasanBergabung,
                    status = status
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