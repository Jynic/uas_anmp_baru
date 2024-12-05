package com.ivano.uas_anmp_baru.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.ivano.uas_anmp_baru.R
import com.ivano.uas_anmp_baru.databinding.FragmentProposalTeamBinding
import com.ivano.uas_anmp_baru.viewmodel.ApplyTeamViewModel
import com.ivano.uas_anmp_baru.viewmodel.ProposalTeamViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProposalTeamFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProposalTeamFragment : Fragment(), TambahProposalTeam {
    private lateinit var binding: FragmentProposalTeamBinding
    private lateinit var viewModel: ProposalTeamViewModel
    private val proposalListAdapter = ProposalListAdapter(arrayListOf())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProposalTeamBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProposalTeamViewModel::class.java)
        viewModel.refresh()
        binding.recView.layoutManager = LinearLayoutManager(context)
        binding.recView.adapter = proposalListAdapter

        binding.tambahListener = this

//        binding.btnFAB.setOnClickListener {
//            val action = ProposalTeamFragmentDirections.actionApplyTeam()
//            Navigation.findNavController(it).navigate(action)
//        }

        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.todoLD.observe(viewLifecycleOwner, Observer {
            proposalListAdapter.updateProposalList(it)
            if(it.isEmpty()) {
                binding.recView?.visibility = View.GONE
                binding.txtError.setText("Your todo still empty.")
            } else {
                binding.recView?.visibility = View.VISIBLE
                binding.txtError.setText("")
            }
        })
        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it == false) {
                binding.progressBar?.visibility = View.GONE
            } else {
                binding.progressBar?.visibility = View.VISIBLE
            }
        })
        viewModel.LoadErrorLD.observe(viewLifecycleOwner, Observer {
            if(it == false) {
                binding.txtError?.visibility = View.VISIBLE
            } else {
                binding.txtError?.visibility = View.GONE
            }
        })

    }

    override fun onCreateProposal(v: View) {
        val action = ProposalTeamFragmentDirections.actionApplyTeam()
        Navigation.findNavController(v).navigate(action)
    }

}