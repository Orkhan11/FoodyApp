package com.example.foodapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.foodapp.data.entity.FoodCard
import com.example.foodapp.data.entity.Foods
import com.example.foodapp.databinding.AddCardBinding
import com.example.foodapp.ui.viewmodel.CardViewModel
import com.google.android.material.snackbar.Snackbar

class CardAdapter(var mContext: Context,var list: List<FoodCard>, var viewModel: CardViewModel)
    :RecyclerView.Adapter<CardAdapter.AddCardHolder>(){


    inner class AddCardHolder(var binding: AddCardBinding) :ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddCardHolder {
        val binding = AddCardBinding.inflate(LayoutInflater.from(mContext),parent,false)
        return AddCardHolder(binding)
    }

    override fun onBindViewHolder(holder: AddCardHolder, position: Int) {
        val food = list[position]
        var bindingCard = holder.binding

        bindingCard.titleMeal.text = food.name
        bindingCard.amountCard.text = food.price
        addImage(food.image,holder)
        bindingCard.amount.text = "Number : ${food.orderAmount}"

        bindingCard.imageView.setOnClickListener {
            Snackbar.make(it,"Do you want to delete ${food.name} ?", Snackbar.LENGTH_LONG)
                .setAction("YES"){
                    viewModel.delete(food.cartId,"orkhan.guliyev")
                }.show()
        }


    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun addImage(pictureName: String, holder: AddCardHolder) {
        val url = "http://kasimadalan.pe.hu/foods/images/$pictureName"
        Glide.with(mContext).load(url).into(holder.binding.mealView)
    }
}