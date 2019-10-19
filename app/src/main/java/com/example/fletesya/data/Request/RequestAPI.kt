package com.example.fletesya.data.Request


import com.example.fletesya.data.Response.ratesResponse
import com.example.fletesya.data.Response.subastaResponse
import retrofit2.Call
import retrofit2.http.GET

interface RequestAPI {

    @GET("latest")
    fun ratesListado(): Call<ratesResponse>

    @GET("listado")
    fun subastaLisatdo(): Call<subastaResponse>

}