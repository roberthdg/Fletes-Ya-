package com.example.fletesya.data.Request

import android.app.Application
import android.content.Context
import com.example.fletesya.App
import com.example.fletesya.data.Preferences.MyPreferences
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.*
import javax.inject.Inject


object RetrofitClient {

    private const val BASE_URL = "https://fletesya.cl/api/"

    val interceptor = run{
        val httpLoggingInterceptor = HttpLoggingInterceptor ()
        httpLoggingInterceptor.apply {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }
    }

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

class ServiceInterceptor : Interceptor{

  val preferences = MyPreferences(App.getContext())

    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val original = chain.request()

        val requestBuilder = original.newBuilder()
            .addHeader("Authorization", "Bearer "+preferences.getToken("ACCESS_TOKEN"))
            .method(original.method, original.body)

        return chain.proceed(requestBuilder.build())
    }

/*    @Throws(IOException::class)
    override fun authenticate(route: Route?, response: okhttp3.Response): Request? {
        var requestAvailable: Request? = null
        try{
            requestAvailable = response?.request?.newBuilder()
                                ?.addHeader("Authorization", "Bearer "+preferences.getToken("ACCESS_TOKEN")).build()
            return requestAvailable

        }
    }*/


}
