package com.example.fletesya.data.Request

import android.app.Application
import android.content.Context
import android.util.Base64
import com.example.fletesya.data.Preferences.MyPreferences
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*




object RetrofitClient {

    private val AUTH = "Basic "+ Base64.encodeToString("asdf:huehuehue".toByteArray(), Base64.NO_WRAP)

    val logginInterceptor = HttpLoggingInterceptor()

    private const val BASE_URL = "https://fletesya.cl/api/"

    val interceptor = run{
        val httpLoggingInterceptor = HttpLoggingInterceptor ()
        httpLoggingInterceptor.apply {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }
    }

/*    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val original = chain.request()

            val requestBuilder = original.newBuilder()
                .addHeader("Authorization","Bearer mjkhjghj")
                .method(original.method, original.body)

            val request = requestBuilder.build()
            chain.proceed(request)
        }.addInterceptor(interceptor).build()*/

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(ServiceInterceptor()).addInterceptor(interceptor).build()

    val instance: RequestAPI by lazy{
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        retrofit.create(RequestAPI::class.java)
    }

}

class ServiceInterceptor : Interceptor {

  //  val context = Application()

   // val preferences = MyPreferences(context)

    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val original = chain.request()

        val requestBuilder = original.newBuilder()
            .addHeader("Authorization","Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6ImFzZGYiLCJpZCI6NTUsImlhdCI6MTU3NDU3MTM5NiwiZXhwIjoxNTc0NTczMTk2fQ.WDabhGKDD8jgXG20LjAJ1nASivgMRJgOYzjERMGvUGY")
            .method(original.method, original.body)

        return chain.proceed(requestBuilder.build())
    }

}