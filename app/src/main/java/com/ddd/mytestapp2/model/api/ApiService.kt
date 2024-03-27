package com.ddd.mytestapp2.model.api

import com.ddd.mytestapp2.model.modelFood.ModelAnswer
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("complexSearch?number=10&apiKey=9d1ebc7d8c1e4e76a926752ac1eb9ac2")
    suspend fun getListFood(@Query("query") query: String):Response<ModelAnswer>

}