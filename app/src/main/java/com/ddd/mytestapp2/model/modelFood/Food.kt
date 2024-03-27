package com.ddd.mytestapp2.model.modelFood

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Food(
    val id: Int,
    val image: String,
    val imageType: String,
    val title: String
):Parcelable