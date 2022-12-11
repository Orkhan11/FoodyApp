package com.example.foodapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.foodapp.R
import com.example.foodapp.databinding.FragmentCardBinding
import com.example.foodapp.ui.adapter.CardAdapter
import com.example.foodapp.ui.viewmodel.CardViewModel
import com.example.foodapp.ui.viewmodel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.stream.Collectors

@AndroidEntryPoint
class CardFragment : Fragment() {
    private lateinit var binding: FragmentCardBinding
    private lateinit var viewModel: CardViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: CardViewModel by viewModels()
        viewModel = tempViewModel
        getCardFoods()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_card, container, false)

        var count = 0

        viewModel.foodCardList.observe(viewLifecycleOwner) { foodCards ->
            val adapter = CardAdapter(requireContext(), foodCards, viewModel)
            binding.rec.layoutManager =
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            binding.rec.adapter = adapter

            count = foodCards.sumOf {
                it.price.toInt()
            }

            binding.totalCount = "$ $count"
        }

        return binding.root
    }


    fun getCardFoods() {
        viewModel.getFoodByUsername("orkhan.guliyev")
    }

}