package com.ivano.uas_anmp_baru.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ivano.uas_anmp_baru.R
import com.ivano.uas_anmp_baru.databinding.FragmentHomeBinding
import com.ivano.uas_anmp_baru.viewmodel.GameViewModel

class HomeFragment : Fragment() {
    private lateinit var viewModel:GameViewModel
    private val gamelistAdapter  = HomeListAdapter(arrayListOf())
    private lateinit var binding:FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)
        viewModel.fetchGames()
        val sharedPref = activity?.getSharedPreferences("loginAccount", Context.MODE_PRIVATE)
        Log.d("Check login", sharedPref?.getString("username", "null").toString())
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.itemHome)
            }
        })

        binding.txtError.visibility = View.GONE

        binding.recView.layoutManager = LinearLayoutManager(context)
        binding.recView.adapter = gamelistAdapter

        observeViewModel()

    }
    fun observeViewModel() {
        viewModel.gamesLD.observe(viewLifecycleOwner, Observer {
            gamelistAdapter.updateGameList(it)
        })
        viewModel.gameLoadErrorLD.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                binding.txtError.visibility = View.VISIBLE
            } else {
                binding.txtError.visibility = View.GONE
            }
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                binding.recView.visibility = View.GONE
                binding.progressLoad.visibility = View.VISIBLE
            } else {
                binding.recView.visibility = View.VISIBLE
                binding.progressLoad.visibility = View.GONE
            }
        })
    }
}