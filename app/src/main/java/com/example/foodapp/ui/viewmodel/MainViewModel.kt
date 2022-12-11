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
class MainViewModel @Inject constructor(var foodRepository: FoodRepository) :ViewModel() {

    var personsList = MutableLiveData<List<Foods>>()


    init {
        loadFoods()
    }

    fun loadFoods(){
        CoroutineScope(Dispatchers.Main).launch {
            personsList.value = foodRepository.getAllFoods()
        }
    }

     fun searchFood(searchText :String) {
         CoroutineScope(Dispatchers.Main).launch {
           personsList.value =  foodRepository.getAllFoods().filter {
                 it.name.lowercase().contains(searchText.lowercase())
             }
         }
     }

}