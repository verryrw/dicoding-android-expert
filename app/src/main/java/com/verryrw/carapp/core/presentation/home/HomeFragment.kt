package com.verryrw.carapp.core.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.verryrw.carapp.core.common.Resource
import com.verryrw.carapp.core.presentation.adapter.CarAdapter
import com.verryrw.carapp.databinding.FragmentHomeBinding
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
                            rvCars.adapter = CarAdapter(cars.data!!)
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