package com.verryrw.carapp.core.presentation.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.verryrw.carapp.R
import com.verryrw.carapp.core.domain.models.Car
import com.verryrw.carapp.databinding.FragmentDetailCarBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailCarFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentDetailCarBinding
    private val args: DetailCarFragmentArgs by navArgs()
    private val viewModel: DetailCarViewModel by viewModels()
    private var statusFavorite: Boolean = false
    private lateinit var car: Car

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailCarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpActionListener()
        setUpData()
    }

    private fun setUpData() {
        car = args.car
        statusFavorite = car.isFavorite
        binding.apply {
            Glide.with(requireContext()).load(car.urlToImage).into(imgCar)
            tvTitle.text = car.name
            tvDescription.text = car.description
        }
        setFavoriteDrawable()
    }

    private fun setUpActionListener() {
        binding.apply {
            btnBack.setOnClickListener(this@DetailCarFragment)
            fabFavorite.setOnClickListener(this@DetailCarFragment)
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_back -> findNavController().navigateUp()
            R.id.fab_favorite -> setStatusFavorite()
        }
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
}