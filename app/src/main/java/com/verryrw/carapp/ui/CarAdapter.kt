package com.verryrw.carapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.verryrw.carapp.ui.favorite.FavoriteCarFragmentDirections
import com.verryrw.carapp.ui.home.HomeFragmentDirections
import com.verryrw.core.databinding.ItemCarBinding
import com.verryrw.core.domain.models.Car

class CarAdapter(private val data: List<Car>, private val source: String = "home") :
    RecyclerView.Adapter<CarAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemCarBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val car = data[position]
        holder.bind(car)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ItemViewHolder(private val binding: ItemCarBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(car: Car) {
            Glide.with(binding.root)
                .load(car.urlToImage)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.imgCar)

            binding.tvTitle.text = car.name
            binding.tvDescription.text = car.description
            setUpActionListener(car)
        }

        private fun setUpActionListener(car: Car) {
            itemView.setOnClickListener {
                var action: NavDirections =
                    HomeFragmentDirections.actionHomeFragmentToDetailFishFragment(car)

                if (source == "favorite") {
                    action =
                        FavoriteCarFragmentDirections.actionFavoriteFishFragmentToDetailCarFragment(
                            car
                        )
                }

                itemView.findNavController().navigate(action)
            }
        }
    }
}