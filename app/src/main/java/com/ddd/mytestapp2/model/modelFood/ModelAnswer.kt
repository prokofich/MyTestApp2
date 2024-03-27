package com.ddd.mytestapp2.model.modelFood

data class ModelAnswer(
    val number: Int,
    val offset: Int,
    val results: List<Food>,
    val totalResults: Int
)