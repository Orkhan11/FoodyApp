package com.example.foodapp.ui.adapter

import android.content.Context
import android.text.Layout.Directions
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.foodapp.data.entity.Foods
import com.example.foodapp.databinding.FoodCardBinding
import com.example.foodapp.ui.fragment.MainFragment
import com.example.foodapp.ui.fragment.MainFragmentDirections

class MainAdapter(var mContext: Context, var list: List<Foods>) :
    RecyclerView.Adapter<MainAdapter.FoodCardHolder>() {

    inner class FoodCardHolder(var binding: FoodCardBinding) : ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodCardHolder {
        val binding: FoodCardBinding =
            FoodCardBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return FoodCardHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodCardHolder, position: Int) {
        val foods = list[position]
        val foodBinding = holder.binding

        foodBinding.root.setOnClickListener {
            val transition =
                MainFragmentDirections.actionMainFragmentToDetailFragment(foods = foods)
            Navigation.findNavController(it).navigate(transition)
        }

        foodBinding.amountCard.text = foods.price.toString()
        foodBinding.titleMeal.text = foods.name
        addImage(foods.image, holder)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun addImage(pictureName: String, holder: FoodCardHolder) {
        val url = "http://kasimadalan.pe.hu/foods/images/$pictureName"
        Glide.with(mContext).load(url).into(holder.binding.mealView)
    }
}