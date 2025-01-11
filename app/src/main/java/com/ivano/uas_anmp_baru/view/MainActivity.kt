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
import androidx.core.view.GravityCompat
import androidx.core.view.isInvisible
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.ivano.uas_anmp_baru.databinding.ActivityMainBinding
import com.ivano.uas_anmp_baru.viewmodel.UserViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding
    private lateinit var userViewModel: UserViewModel
    private lateinit var drawerLayout: DrawerLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        supportActionBar?.hide()

        // Inisialisasi binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inisialisasi navController
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmenHost) as NavHostFragment
        navController = navHostFragment.navController

        drawerLayout = binding.drawerLayout
        val fabOpenDrawer: FloatingActionButton = findViewById(R.id.fab_open_drawer)
        fabOpenDrawer.setOnClickListener {
            if (!drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.openDrawer(GravityCompat.START) // Buka drawer
            } else {
                drawerLayout.closeDrawer(GravityCompat.START) // Tutup drawer jika sudah terbuka
            }
        }
        binding.navigationView.setupWithNavController(navController)
        binding.navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    navController.navigate(R.id.proposalTeamFragment)
                }
                R.id.nav_profile -> {
                    logOut()
                }
            }
            binding.drawerLayout.closeDrawer(GravityCompat.START) // Tutup drawer setelah klik
            true
        }

        navController.addOnDestinationChangedListener{_, destination, _ ->
            when (destination.id) {
                R.id.loginFragment, R.id.registrationUser -> {
                    binding.fabOpenDrawer.isInvisible = true
                }
                else->{
                    binding.fabOpenDrawer.isInvisible = false
                }
            }
    }

        // Handle insets
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

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
    private fun logOut() {
        val sharedPref = getSharedPreferences("loginAccount", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.clear()
        editor.apply()

        // Navigasi ke Login Fragment
        navController.navigate(R.id.loginFragment)

        // Opsional: Menghapus informasi yang berkaitan dengan sesi login di view model atau lainnya
        // userViewModel.logout() jika Anda menggunakan ViewModel untuk status login
    }
}
