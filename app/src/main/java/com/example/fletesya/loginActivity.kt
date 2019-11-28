
package com.example.fletesya

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.media.session.MediaSession
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.fletesya.data.Preferences.MyPreferences
import com.example.fletesya.data.Preferences.TokenManager
import com.example.fletesya.data.Request.RequestAPI
import com.example.fletesya.data.Request.RetrofitClient
import com.example.fletesya.data.Response.loginResponse
import com.example.fletesya.data.Response.subastaResponse
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.oferta_fragment.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.internal.lockAndWaitNanos
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


//private val tokenManager = TokenManager.getInstance(prefs = SharedPreferences)

class loginActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        loginButton.setOnClickListener{
            login(this)
        }

    }

    fun login(context: Context) {

        val correo = emailText.text.toString()

        val password = passText.text.toString()

        println(password+correo)

        RetrofitClient.instance.loginCall(correo, password).enqueue(object : Callback<loginResponse> {

            val preferences = MyPreferences(context) // val tokenManager = TokenManager

            override fun onFailure(call: Call<loginResponse>, t: Throwable) {
                println("wait a minute boi: "+ t.toString())
            }

            override fun onResponse(call: Call<loginResponse>, response: Response<loginResponse>){

                if(response.code()==200) {
                    val sResponse = response.body()

                    val user = Gson().toJson(sResponse!!.user)

                    preferences.saveUserData("user", user)  //  tokenManager.saveToken()
                    preferences.saveToken("ACCESS_TOKEN", sResponse.accessToken)
                    preferences.saveToken("REFRESH_TOKEN", sResponse.refreshToken)

                    val intent = Intent(context, MainActivity::class.java)

                    startActivity(intent)
                }
                else {
                    println("error: "+ response.code())
                    Toast.makeText(context,"Datos incorrectos", Toast.LENGTH_LONG).show()
                }
            }
        })
    }
}


