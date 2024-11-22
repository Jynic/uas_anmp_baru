package com.ivano.uas_anmp_baru.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.ivano.uas_anmp_baru.R
import com.ivano.uas_anmp_baru.databinding.FragmentRegistrationUserBinding
import com.ivano.uas_anmp_baru.model.User
import com.ivano.uas_anmp_baru.viewmodel.CreateUserViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RegistrationUser.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegistrationUser : Fragment() {
    private lateinit var binding: FragmentRegistrationUserBinding
    private lateinit var viewModel:CreateUserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegistrationUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(CreateUserViewModel::class.java)
        binding.checkboxAgreement.setOnCheckedChangeListener { _, isChecked ->
            binding.registration.isEnabled = isChecked
        }
        binding.registration.setOnClickListener {
            val username = binding.username.text?.toString()?.trim() ?: ""
            val firstName = binding.firstName.text?.toString()?.trim() ?: ""
            val lastName = binding.lastName.text?.toString()?.trim() ?: ""
            val password = binding.password.text?.toString()?.trim() ?: ""

            if (username.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || password.isEmpty()) {
                Toast.makeText(requireContext(), "All fields are required", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), username, Toast.LENGTH_SHORT).show()
                val user = User(username, firstName, lastName, password)
                var list = listOf(user)
                viewModel.addUser(list)
                Toast.makeText(view.context, "Data added", Toast.LENGTH_LONG).show()
                Navigation.findNavController(it).popBackStack()
            }
        }
    }

}