package com.example.fletesya.data.Request


import com.example.fletesya.data.Response.ratesResponse
import com.example.fletesya.data.Response.subastaResponse
import com.example.fletesya.data.Response.loginResponse
import com.example.fletesya.data.Response.regResponse
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

    @POST("auth/token")
    @FormUrlEncoded
    fun crearUsuario(
        @Field("correo") correo: String ,
        @Field("password") password: String,
        @Field("tipo") tipo: String
    ): Call<regResponse>



}