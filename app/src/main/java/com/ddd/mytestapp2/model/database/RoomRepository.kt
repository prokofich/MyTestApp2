package com.ddd.mytestapp2.model.database

import android.content.Context
import androidx.room.Room

class RoomRepository(context:Context) {

    val database = Room.databaseBuilder(context, AppDatabase::class.java, "MyDatabase").build()

}