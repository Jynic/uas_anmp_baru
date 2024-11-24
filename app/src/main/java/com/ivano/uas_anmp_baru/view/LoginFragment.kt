package com.ivano.uas_anmp_baru.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.ivano.uas_anmp_baru.R
import com.ivano.uas_anmp_baru.databinding.FragmentLoginBinding
import com.ivano.uas_anmp_baru.viewmodel.UserViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment(), ButtonClickListener, ButtonActionNavClickListener, TextInputClickListener {
    private lateinit var viewModel: UserViewModel
    private lateinit var binding: FragmentLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_login, container, false)
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.listener = this
        binding.navListener = this
        binding.inputListener = this

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.loginFragment)
            }
        })

        val sharedPref = activity?.getSharedPreferences("loginAccount", Context.MODE_PRIVATE)
        Log.d("Check login", sharedPref?.getString("username", "null").toString())
        if (sharedPref?.getString("username", "null") != "null"){
            val action = LoginFragmentDirections.actionHomeFragment()
            Navigation.findNavController(view).navigate(action)
        }

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
    }

    override fun onButtonClick(v: View) {
        var username = binding.txtUsername.text.toString()
        var password = binding.txtPassword.text.toString()

        if (username.isEmpty() || password.isEmpty()) {
            binding.txtUsername.error = "Username tidak boleh kosong"
            binding.txtPassword.error = "Password tidak boleh kosong"
        } else {
            viewModel.fetchLogin(username, password)

            viewModel.userLoginLD.observe(viewLifecycleOwner, Observer {
                if(it != null){
                    val saveLogin = requireActivity().getSharedPreferences("loginAccount", Context.MODE_PRIVATE)
                    var editor = saveLogin.edit()
                    editor.putString("username", it.username)
                    editor.putString("password", it.password)
                    editor.apply()

                    val action = LoginFragmentDirections.actionHomeFragment()
                    Navigation.findNavController(v).navigate(action)

                    Toast.makeText(requireContext(), "Login Success", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), "Login Failed", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    override fun onButtonActionNavClick(v: View) {
        val action = LoginFragmentDirections.actionRegistFragment()
        Navigation.findNavController(v).navigate(action)
    }

    override fun onInputClick(v: View) {
        if(v.tag == "inputUsername"){
            binding.textInputLayoutUsername.error = null
            binding.textInputLayoutUsername.isErrorEnabled = false
        }
        if(v.tag == "inputPassword"){
            binding.textInputLayoutPassword.error = null
            binding.textInputLayoutPassword.isErrorEnabled = false
        }
    }
}