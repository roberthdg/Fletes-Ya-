
package com.example.fletesya

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fletesya.data.Request.RequestAPI
import com.example.fletesya.data.Response.loginResponse
import com.example.fletesya.data.Response.subastaResponse
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.oferta_fragment.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class loginActivity : AppCompatActivity() {

    private val retrofit = Retrofit.Builder().baseUrl("https://fletesya.cl/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val postsApi = retrofit.create(RequestAPI::class.java)
    private val loginRes = postsApi.loginCall("asdf", "huehuehue")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginButton.setOnClickListener{
            login()
        }
    }

    fun login() {

        loginRes.enqueue(object : Callback<loginResponse> {

            override fun onFailure(call: Call<loginResponse>, t: Throwable) {
                println("wait a minute boi: "+ t.toString())
            }

            override fun onResponse(call: Call<loginResponse>, response: Response<loginResponse>){
                val sResponse = response.body()
                println("subasta response: "+ sResponse!!.user.fecha_registro.toString())
            }

        })
    }

}


