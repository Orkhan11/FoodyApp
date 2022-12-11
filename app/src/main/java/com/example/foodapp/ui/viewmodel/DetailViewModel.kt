package com.example.foodapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodapp.data.entity.Foods
import com.example.foodapp.data.repo.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailViewModel @Inject constructor(var foodRepository: FoodRepository) : ViewModel() {

    var foodList = MutableLiveData<List<Foods>>()

    fun update(
        name: String,
        image: String,
        price: Int,
        category: String,
        orderAmount: Int,
        userName: String
    ) {
        CoroutineScope(Dispatchers.Main).launch {
            foodRepository.insertFood(name, image, price, category, orderAmount, userName)
        }
    }

    init {
        loadFoods()
    }

    fun loadFoods() {
        CoroutineScope(Dispatchers.Main).launch {
            foodList.value = foodRepository.getAllFoods()
        }
    }




}