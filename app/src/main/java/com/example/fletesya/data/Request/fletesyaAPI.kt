/*package com.example.fletesya.data
import com.example.fletesya.ui.simulador.SimuladorFragment
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.http.GET
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val API_KEY = "aegdgfdgsdf"

// https://fletesya.cl/api/listado

interface fletesyaAPI {

    @GET("listado")
    fun listado(
        //variables del get
        //@Query("key") location: String

    ):Deferred<SimuladorFragment>

    companion object {
        operator fun invoke(): fletesyaAPI {
            val requestInterceptor = Interceptor {chain ->
                val url = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("key", API_KEY)
                    .build()

                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()

                return@Interceptor chain.proceed(request)
            }
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://fletesya.cl/api/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(fletesyaAPI::class.java)

        }
    }
}

        */