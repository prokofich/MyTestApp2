package com.ddd.mytestapp2.model.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "FoodEntity")
data class FoodEntity(
    @PrimaryKey val id:Long = 0,
    @ColumnInfo(name = "name")val name:String,
    @ColumnInfo(name = "desc")val urlImage:String,
    @ColumnInfo(name = "catalog")val catalog:String,
)