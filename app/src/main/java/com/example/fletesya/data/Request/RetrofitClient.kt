package com.example.fletesya.data.Request

import android.util.Base64
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

object RetrofitClient {

    private val AUTH = "Basic "+ Base64.encodeToString("asdf:huehuehue".toByteArray(), Base64.NO_WRAP)

    private const val BASE_URL = "https://fletesya.cl/api/"

    private val okHttpClient = OkHttpClient.Builder()
            .addInterceptor { chain ->
            val original = chain.request()

            val requestBuilder = original.newBuilder()
                .addHeader("Autorization","")
                .method(original.method, original.body)

            val request = requestBuilder.build()
                chain.proceed(request)
    }.build()



    val instance: RequestAPI by lazy{
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        retrofit.create(RequestAPI::class.java)
    }


}