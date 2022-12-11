package com.example.foodapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.foodapp.R
import com.example.foodapp.data.entity.Foods
import com.example.foodapp.databinding.FilterCardBinding
import java.util.stream.Collectors

class SubAdapter(var mContext: Context, var list: List<Foods>) :
    RecyclerView.Adapter<SubAdapter.FilterCardHolder>() {

    inner class FilterCardHolder(var binding: FilterCardBinding) : ViewHolder(binding.root)


    private lateinit var listener :OnItemClickListener


    fun setClickListener(listener: OnItemClickListener){
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterCardHolder {
        val binding = FilterCardBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return FilterCardHolder(binding)
    }

    override fun onBindViewHolder(holder: FilterCardHolder, position: Int) {
        val list = list.stream().map {
            it.category
        }.distinct().collect(Collectors.toList())
        val binding = holder.binding
        binding.textView2.text = list[position]
        binding.imageView2.setImageResource(
            mContext.resources.getIdentifier(
                list[position].lowercase(),
                "drawable",
                mContext.packageName
            ))


        binding.root.setOnClickListener {
            listener.onClicked(list[position])
        }

    }

    override fun getItemCount(): Int {
        return list.stream().map {
            it.category
        }.distinct().collect(Collectors.toList()).size

    }

    interface  OnItemClickListener{
        fun onClicked(categoryName :String)
    }



}

