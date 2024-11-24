package com.ivano.uas_anmp_baru.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.ivano.uas_anmp_baru.databinding.FragmentRegistrationUserBinding
import com.ivano.uas_anmp_baru.model.User
import com.ivano.uas_anmp_baru.viewmodel.UserViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RegistrationUser.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegistrationUser : Fragment(), ButtonClickListener, ButtonActionNavClickListener, TextInputClickListener {
    private lateinit var binding: FragmentRegistrationUserBinding
    private lateinit var viewModel:UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegistrationUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
//        binding.checkboxAgreement.setOnCheckedChangeListener { _, isChecked ->
//            binding.registration.isEnabled = isChecked
//        }

        binding.listener = this
        binding.navListener = this
        binding.inputListener = this
    }

    override fun onButtonClick(v: View) {
        val fname = binding.firstName.text.toString()
        val lname = binding.lastName.text.toString()
        val username = binding.username.text.toString()
        val password = binding.password.text.toString()
        val passwordRepeat = binding.passwordRepeat.text.toString()

        if (username.isEmpty() || fname.isEmpty() || lname.isEmpty() || password.isEmpty()) {
            Toast.makeText(requireContext(), "All fields are required", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), username, Toast.LENGTH_SHORT).show()
            val user = User(username, fname, lname, password)
            var list = listOf(user)

            if(password == passwordRepeat){
                viewModel.addUser(list)
                Toast.makeText(requireContext(), "Data added", Toast.LENGTH_LONG).show()
                Navigation.findNavController(v).popBackStack()
            } else {
                Toast.makeText(requireContext(), "Password not match", Toast.LENGTH_LONG).show()
            }
        }

        viewModel.isAgreed.observe(viewLifecycleOwner, Observer { isChecked ->
            // Lakukan sesuatu ketika isAgreed berubah
            binding.registration.isEnabled = true
        })
    }

    override fun onButtonActionNavClick(v: View) {
        Navigation.findNavController(v).popBackStack()
    }

    override fun onInputClick(v: View) {
        if (v.tag == "inputFirstName") {
            binding.firstNameLayout.error = null
            binding.firstNameLayout.isEnabled = false
        }
        if (v.tag == "inputLastName") {
            binding.lastNameLayout.error = null
            binding.lastNameLayout.isEnabled = false
        }
        if (v.tag == "inputUsername") {
            binding.usernameLayout.error = null
            binding.usernameLayout.isEnabled = false
        }
        if (v.tag == "inputPassword") {
            binding.passwordLayout.error = null
            binding.passwordRepeatLayout.isEnabled = false
        }
        if (v.tag == "inputRepeatPassword"){
            binding.passwordRepeatLayout.error = null
            binding.passwordRepeatLayout.isEnabled = false
        }
    }

}