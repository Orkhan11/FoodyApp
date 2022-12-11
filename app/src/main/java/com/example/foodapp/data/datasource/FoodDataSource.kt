package com.example.foodapp.data.datasource

import com.example.foodapp.data.entity.FoodCard
import com.example.foodapp.data.entity.Foods
import com.example.foodapp.retrofit.FoodDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FoodDataSource(var foodDao: FoodDao) {

    suspend fun getAllFoods(): List<Foods> =
        withContext(Dispatchers.IO) {
            foodDao.getAllFoods().foods
        }

    suspend fun getFoodFromCard(userName: String): List<FoodCard> =
        withContext(Dispatchers.IO) {
            foodDao.getFoodFromCard(userName).foods_cart

        }

    suspend fun deleteFoodFromCart(cartId: Int, userName: String) =
        foodDao.deleteFoodFromCart(cartId, userName)

    suspend fun insertFood(
        name: String,
        image: String,
        price: Int,
        category: String,
        orderAmount: Int,
        userName: String
    ) = foodDao.insertFood(name, image, price, category, orderAmount, userName)


}