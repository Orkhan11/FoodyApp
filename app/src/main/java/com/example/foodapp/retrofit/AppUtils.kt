package com.example.foodapp.retrofit

class AppUtils {

    companion object{
        private const val BASE_URL = "http://kasimadalan.pe.hu/"

        fun getFoodDao():FoodDao{
            return RetrofitClient.getClient(BASE_URL).create(FoodDao::class.java)
        }
    }
}