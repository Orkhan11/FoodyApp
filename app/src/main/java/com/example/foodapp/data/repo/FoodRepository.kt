package com.example.foodapp.data.repo

import com.example.foodapp.data.datasource.FoodDataSource

class FoodRepository(var foodDataSource: FoodDataSource) {

    suspend fun insertFood(
        name: String,
        image: String,
        price: Int,
        category: String,
        orderAmount: Int,
        userName: String
    ) = foodDataSource.insertFood(name, image, price, category, orderAmount, userName)

    suspend fun getAllFoods() = foodDataSource.getAllFoods()

    suspend fun getFoodFromCard(userName: String) = foodDataSource.getFoodFromCard(userName)

    suspend fun deleteFoodFromCart(cartId: Int, userName: String) =
        foodDataSource.deleteFoodFromCart(cartId, userName)


}