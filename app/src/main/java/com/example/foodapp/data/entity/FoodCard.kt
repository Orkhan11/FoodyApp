package com.example.foodapp.data.entity

import java.time.temporal.TemporalAmount

data class FoodCard(
    var cartId: Int,
    var name: String,
    var image: String,
    var price: String,
    var category :String,
    var orderAmount: String,
    var userName: String
) :java.io.Serializable {}