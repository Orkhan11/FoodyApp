package com.example.foodapp.di

import com.example.foodapp.data.datasource.FoodDataSource
import com.example.foodapp.data.repo.FoodRepository
import com.example.foodapp.retrofit.AppUtils
import com.example.foodapp.retrofit.FoodDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideFoodRepository(foodDataSource: FoodDataSource) : FoodRepository {
        return  FoodRepository(foodDataSource)
    }

    @Provides
    @Singleton
    fun provideFoodDataSource(foodDao: FoodDao) : FoodDataSource {
        return  FoodDataSource(foodDao)
    }

    @Provides
    @Singleton
    fun provideFoodDao() : FoodDao {
        return  AppUtils.getFoodDao()
    }



}