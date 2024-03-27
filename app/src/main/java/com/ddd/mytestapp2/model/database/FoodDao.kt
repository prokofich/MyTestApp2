package com.ddd.mytestapp2.model.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FoodDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertListFoods(foodEntities: List<FoodEntity>)

    @Query("SELECT * FROM FoodEntity WHERE catalog = :searchText")
    suspend fun getListFoodByCatalog(searchText: String): List<FoodEntity>

}