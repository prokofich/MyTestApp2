package com.ddd.mytestapp2.model.database

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [FoodEntity::class],version = 5)
abstract class AppDatabase:RoomDatabase() {
    abstract fun databaseFoodDao():FoodDao
}