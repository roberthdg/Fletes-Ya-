package com.example.fletesya.data.Request


import com.example.fletesya.data.Response.ratesResponse
import com.example.fletesya.data.Response.subastaResponse
import com.example.fletesya.data.Response.loginResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface RequestAPI {

    @GET("latest")
    fun ratesListado(): Call<ratesResponse>

    @GET("listado")
    fun subastaLisatdo(): Call<subastaResponse>

    @POST("login")
    @FormUrlEncoded
    fun loginCall(
        @Field("correo") correo: String ,
        @Field("password") password: String
    ): Call<loginResponse>

}