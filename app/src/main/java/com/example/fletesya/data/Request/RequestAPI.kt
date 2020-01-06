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

    @GET("subastas/listado")
    fun subastaCall(): Call<subastaResponse>

    @GET("ofertas/listado")
    fun ofertaCall(): Call<ofertaResponse>




}