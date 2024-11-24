package com.ivano.uas_anmp_baru.view

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.ivano.uas_anmp_baru.R
import android.Manifest
import androidx.core.view.isInvisible
import androidx.lifecycle.ViewModelProvider
import com.ivano.uas_anmp_baru.databinding.ActivityMainBinding
import com.ivano.uas_anmp_baru.viewmodel.UserViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding
    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        supportActionBar?.hide()

        // Inisialisasi binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Handle insets
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inisialisasi navController
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmenHost) as NavHostFragment
        navController = navHostFragment.navController

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.loginFragment, R.id.registrationUser -> {
                    binding.bottomNavigation.isInvisible = true
                }
                else->{
                    binding.bottomNavigation.isInvisible = false
                }
            }
        }

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        // Setup bottomNavigation dengan navController
        binding.bottomNavigation.setupWithNavController(navController)

        if(!userViewModel.isUserLoggedIn()){
            Log.d("Check login", "Berhasil cek login")
            navController.navigate(R.id.loginFragment)
        }

    }
}
