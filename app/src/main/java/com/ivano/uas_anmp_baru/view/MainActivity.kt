package com.ivano.uas_anmp_baru.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.ivano.uas_anmp_baru.R
import com.ivano.uas_anmp_baru.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

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

        // Setup bottomNavigation dengan navController
        binding.bottomNavigation.setupWithNavController(navController)
    }

}