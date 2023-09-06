//package com.verryrw.carapp.favorite
//
//import android.content.Intent
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.Fragment
//import androidx.fragment.app.viewModels
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.verryrw.carapp.core.ui.CarAdapter
//import com.verryrw.carapp.di.FavoriteModuleDependencies
//import com.verryrw.carapp.favorite.databinding.FragmentFavoriteCarBinding
//import com.verryrw.carapp.ui.detail.DetailCarActivity
//import dagger.hilt.android.EntryPointAccessors
//import javax.inject.Inject
//
//class FavoriteCarFragment : Fragment() {
//
//    @Inject
//    lateinit var factory: ViewModelFactory
//    private val viewModel: FavoriteCarViewModel by viewModels { factory }
//
//    private lateinit var binding: FragmentFavoriteCarBinding
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//
//        DaggerFavoriteComponent.builder()
//            .context(requireActivity())
//            .appDependencies(
//                EntryPointAccessors.fromApplication(
//                    requireContext().applicationContext,
//                    FavoriteModuleDependencies::class.java
//                )
//            )
//            .build()
//            .inject(this)
//
//        binding = FragmentFavoriteCarBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        setUpRecyclerView()
//        setUpLiveDataObserver()
//    }
//
//    private fun setUpRecyclerView() {
//        binding.rvCars.layoutManager =
//            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
//    }
//
//    private fun setUpLiveDataObserver() {
//        viewModel.favoriteCars.observe(viewLifecycleOwner) {
//            if (it != null) {
//                binding.apply {
//                    rvShimmer.stopShimmer()
//                    rvShimmer.visibility = View.GONE
//                    if (it.isEmpty()) {
//                        rvCars.visibility = View.GONE
//                        rvShimmer.visibility = View.GONE
//                        tvEmpty.visibility = View.VISIBLE
//                    } else {
//                        rvCars.visibility = View.VISIBLE
//                        val adapter = CarAdapter(it)
//                        adapter.onItemClick = { selectedData ->
//                            val intent = Intent(activity, DetailCarActivity::class.java)
//                            intent.putExtra(DetailCarActivity.EXTRA_DATA, selectedData)
//                            startActivity(intent)
//                        }
//                        rvCars.adapter = adapter
//                    }
//                }
//            }
//        }
//    }
//}