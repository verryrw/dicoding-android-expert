package com.verryrw.carapp.favorite

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.verryrw.carapp.core.ui.CarAdapter
import com.verryrw.carapp.di.FavoriteModuleDependencies
import com.verryrw.carapp.favorite.databinding.ActivityFavoriteBinding
import com.verryrw.carapp.ui.detail.DetailCarActivity
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavoriteActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelFactory
    private val viewModel: FavoriteCarViewModel by viewModels { factory }

    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = "Favorite Cars"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        DaggerFavoriteComponent.builder()
            .context(this)
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    this,
                    FavoriteModuleDependencies::class.java
                )
            )
            .build()
            .inject(this)

        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setUpRecyclerView()
        setUpLiveDataObserver()
        setContentView(binding.root)
    }

    private fun setUpRecyclerView() {
        binding.rvCars.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private fun setUpLiveDataObserver() {
        viewModel.favoriteCars.observe(this) {
            if (it != null) {
                binding.apply {
                    rvShimmer.stopShimmer()
                    rvShimmer.visibility = View.GONE
                    if (it.isEmpty()) {
                        rvCars.visibility = View.GONE
                        rvShimmer.visibility = View.GONE
                        tvEmpty.visibility = View.VISIBLE
                    } else {
                        rvCars.visibility = View.VISIBLE
                        val adapter = CarAdapter(it)
                        adapter.onItemClick = { selectedData ->
                            val intent =
                                Intent(this@FavoriteActivity, DetailCarActivity::class.java)
                            intent.putExtra(DetailCarActivity.EXTRA_DATA, selectedData)
                            startActivity(intent)
                        }
                        rvCars.adapter = adapter
                    }
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}