package com.ivano.uas_anmp_baru.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ivano.uas_anmp_baru.R
import com.ivano.uas_anmp_baru.databinding.FragmentHomeBinding
import com.ivano.uas_anmp_baru.databinding.FragmentSchduleBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SchduleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SchduleFragment : Fragment() {
    private lateinit var binding: FragmentSchduleBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSchduleBinding.inflate(inflater, container, false)
        return binding.root
    }

}