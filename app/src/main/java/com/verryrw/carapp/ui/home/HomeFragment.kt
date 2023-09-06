package com.verryrw.carapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.verryrw.carapp.R
import com.verryrw.carapp.core.common.Resource
import com.verryrw.carapp.core.ui.CarAdapter
import com.verryrw.carapp.databinding.FragmentHomeBinding
import com.verryrw.carapp.ui.detail.DetailCarActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()
        setUpLiveDataObserver()
    }

    private fun setUpRecyclerView() {
        binding.rvCars.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }


    private fun setUpLiveDataObserver() {
        viewModel.cars.observe(viewLifecycleOwner) { cars ->
            if (cars != null) {
                when (cars) {
                    is Resource.Success -> {
                        binding.apply {
                            rvShimmer.stopShimmer()
                            rvShimmer.visibility = View.GONE
                            rvCars.visibility = View.VISIBLE

                            val adapter = CarAdapter(cars.data!!)
                            adapter.onItemClick = { selectedData ->
                                val intent = Intent(activity, DetailCarActivity::class.java)
                                intent.putExtra(DetailCarActivity.EXTRA_DATA, selectedData)
                                startActivity(intent)
                            }
                            rvCars.adapter = adapter
                        }
                    }

                    is Resource.Error -> {
                        binding.apply {
                            rvShimmer.stopShimmer()
                            rvShimmer.visibility = View.GONE
                            rvCars.visibility = View.VISIBLE
                        }
                    }

                    is Resource.Loading -> {
                        binding.apply {
                            rvShimmer.startShimmer()
                            rvShimmer.visibility = View.VISIBLE
                            rvCars.visibility = View.GONE
                        }
                    }
                }
            }
        }
    }

}