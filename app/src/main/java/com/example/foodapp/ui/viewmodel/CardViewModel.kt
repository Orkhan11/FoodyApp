package com.example.foodapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodapp.data.entity.FoodCard
import com.example.foodapp.data.repo.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CardViewModel @Inject constructor(var foodRepository: FoodRepository):ViewModel() {

    var foodCardList = MutableLiveData<List<FoodCard>>()


    fun getFoodByUsername(userName: String) {
        CoroutineScope(Dispatchers.Main).launch {
            foodCardList.value = foodRepository.getFoodFromCard(userName)
        }
    }

    fun delete(cartId: Int, userName: String) {
        CoroutineScope(Dispatchers.Main).launch {
            foodRepository.deleteFoodFromCart(cartId, userName)
            getFoodByUsername(userName)

        }


    }
}