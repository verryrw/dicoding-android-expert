package com.verryrw.carapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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

        supportActionBar?.title = "All Cars"
        // setUpBottomNavigationView()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.top_app_bar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_menu_favorite -> {
                moveToChatActivity()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

//    private fun setUpBottomNavigationView() {
//        val navHostFragment =
//            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
//        val navController = navHostFragment.navController
//        val bottomNavigationView = binding.navView
//
//        bottomNavigationView.setOnItemSelectedListener { item ->
//            navController.popBackStack()
//
//            val destinationId = item.itemId
//            val currentDestinationId = navController.currentDestination?.id
//
//            if (destinationId != currentDestinationId) {
//                navController.navigate(destinationId)
//            }
//            true
//        }
//    }

    private fun moveToChatActivity() {
        try {
            startActivity(
                Intent(
                    this,
                    Class.forName("com.verryrw.carapp.favorite.FavoriteActivity")
                )
            )
        } catch (e: Exception) {
            Toast.makeText(this, "Module not found", Toast.LENGTH_SHORT).show()
        }
    }
}