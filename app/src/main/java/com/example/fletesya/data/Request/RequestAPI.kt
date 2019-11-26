package com.example.fletesya.data.Request


import com.example.fletesya.data.Response.*
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


interface RequestAPI {

    @GET("latest")
    fun ratesListado(): Call<ratesResponse>

    @GET("listado")
    fun subastaCall(): Call<subastaResponse>

    @POST("registrar")
    @FormUrlEncoded
    fun refreshCall(
        @Field("correo") correo: String ,
        @Field("refreshToken") refreshToken: String
    ): Call<loginResponse>

    @POST("login")
    @FormUrlEncoded
    fun loginCall(
        @Field("correo") correo: String ,
        @Field("password") password: String
    ): Call<loginResponse>

    @POST("token")
    @FormUrlEncoded
    fun refreshCall(
        @Field("token") token: String
    ): Call<refreshResponse>



}