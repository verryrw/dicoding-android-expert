package com.verryrw.carapp.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.verryrw.carapp.R
import com.verryrw.carapp.core.domain.models.Car
import com.verryrw.carapp.databinding.ActivityDetailCarBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailCarActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityDetailCarBinding
    private val viewModel: DetailCarViewModel by viewModels()
    private var statusFavorite: Boolean = false
    private lateinit var car: Car

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailCarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpActionListener()
        setUpData()
        supportActionBar?.title = "Detail Car"
         supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setUpActionListener() {
        binding.apply {
            fabFavorite.setOnClickListener(this@DetailCarActivity)
        }
    }

    @Suppress("DEPRECATION")
    private fun setUpData() {
        car = intent.getParcelableExtra(EXTRA_DATA)!!
        statusFavorite = car.isFavorite

        binding.apply {
            Glide.with(applicationContext).load(car.urlToImage).into(imgCar)
            tvTitle.text = car.name
            tvDescription.text = car.description
        }
        setFavoriteDrawable()
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun setStatusFavorite() {
        statusFavorite = !statusFavorite

        viewModel.setFavoriteTourism(car, statusFavorite)
        setFavoriteDrawable()
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun setFavoriteDrawable() {
        if (statusFavorite) {
            binding.fabFavorite.icon = resources.getDrawable(R.drawable.ic_favorite_filled)
        } else {
            binding.fabFavorite.icon = resources.getDrawable(R.drawable.ic_favorite_outline)
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.fab_favorite -> setStatusFavorite()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish() // Finish the current activity
                return true
            }
            // Add other menu item handling here if needed
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}