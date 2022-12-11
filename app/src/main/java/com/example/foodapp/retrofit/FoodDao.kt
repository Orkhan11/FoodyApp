package com.example.foodapp.retrofit

import com.example.foodapp.data.entity.CRUDResponse
import com.example.foodapp.data.entity.CardResponse
import com.example.foodapp.data.entity.FoodResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface FoodDao  {

    //Get All Foods from Server
    @GET("foods/getAllFoods.php")
    suspend fun getAllFoods(): FoodResponse


    @POST("foods/insertFood.php")
    @FormUrlEncoded
    suspend fun insertFood(
        @Field("name") name: String,
        @Field("image") image: String,
        @Field("price") price: Int,
        @Field("category") category: String,
        @Field("orderAmount") orderAmount: Int,
        @Field("userName") userName: String
    ): CRUDResponse


    @POST("foods/getFoodsCart.php")
    @FormUrlEncoded
    suspend fun getFoodFromCard(@Field("userName") userName: String): CardResponse

    @POST("foods/deleteFood.php")
    @FormUrlEncoded
    suspend fun deleteFoodFromCart(
        @Field("cartId") cartId: Int,
        @Field("userName") userName: String
    ): CRUDResponse

}