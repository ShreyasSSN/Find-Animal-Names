package com.example.findanimals

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface RetrofitAPI {


    @GET("/v1/animals")
//    @Headers("x-api-key: lqJfI2kHc2s/ngQOrc1j2g==Y3KNFW74YNlvYgUS")
    fun getAnimalDetails(
        @Header("x-api-key") apiKey : String,
        @Query("name") name : String
    ) : Call<List<Animal>>
}