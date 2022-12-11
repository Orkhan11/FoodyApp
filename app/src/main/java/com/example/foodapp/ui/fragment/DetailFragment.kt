package com.example.foodapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.foodapp.R
import com.example.foodapp.data.entity.Foods
import com.example.foodapp.databinding.FragmentDetailBinding
import com.example.foodapp.ui.viewmodel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private lateinit var viewModel: DetailViewModel
    private lateinit var food: Foods
    private var price: Int = 0
    private lateinit var bundle: DetailFragmentArgs

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val bundleArgs: DetailFragmentArgs by navArgs()
        bundle = bundleArgs
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        binding.detailFragment = this



        viewModel.foodList.observe(viewLifecycleOwner) { foods ->
            food = bundle.foods
            food.amount = 1
            addImage(food.image)

            food.price = foods.filter {
                it.id == bundle.foods.id
            }[0].price

            price = food.price
            binding.food = food
        }
        return binding.root
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: DetailViewModel by viewModels()
        viewModel = tempViewModel

    }

    fun clickToUpdate() {

        viewModel.update(
            food.name,
            food.image,
            food.price,
            food.category,
            food.amount,
            "orkhan.guliyev"
        )
        Navigation.findNavController(requireView()).navigate(R.id.detailToCard)

    }


    private fun addImage(pictureName: String) {
        val url = "http://kasimadalan.pe.hu/foods/images/$pictureName"
        Glide.with(this).load(url).into(binding.imageDetail)
    }

    fun minusClick() {

        if (food.amount > 1) {
            food.amount--
            food.price = price * food.amount
        }
        binding.food = food

    }

    fun plusClick() {
        food.amount++
        food.price = price * food.amount
        binding.food = food

    }


}