package com.verryrw.carapp.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.verryrw.carapp.R
import com.verryrw.carapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpBottomNavigationView()
    }

    private fun setUpBottomNavigationView() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val bottomNavigationView = binding.navView

        bottomNavigationView.setOnItemSelectedListener { item ->
            navController.popBackStack()


            val destinationId = item.itemId
            val currentDestinationId = navController.currentDestination?.id

            if (destinationId != currentDestinationId) {
                navController.navigate(destinationId)
            }
            true
        }
    }

    private fun moveToChatActivity() {
        startActivity(Intent(this, Class.forName("com.verryrw.carapp.favorite.HaloActivity")))
    }
}