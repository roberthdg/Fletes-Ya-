package com.example.fletesya.ui.ofertas

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.fletesya.R
import com.example.fletesya.data.Request.RequestAPI
import com.example.fletesya.data.Response.subastaResponse
import kotlinx.android.synthetic.main.oferta_fragment.*
import kotlinx.android.synthetic.main.simulador_fragment.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ofertaFragment : Fragment() {

    companion object {
        fun newInstance() = ofertaFragment()
    }

    private val retrofit = Retrofit.Builder().baseUrl("https://api.exchangeratesapi.io/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val postsApi = retrofit.create(RequestAPI::class.java)
    private val ratesResponse = postsApi.ratesListado()

    private lateinit var viewModel: ofertaViewModel



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.oferta_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ofertaViewModel::class.java)
        // TODO: Use the ViewModel
        ratesResponse.enqueue(object : Callback<subastaResponse> {
            override fun onFailure(call: Call<subastaResponse>, t: Throwable) {
                println("lllllllllooooserrrr")
            }
            override fun onResponse(call: Call<subastaResponse>, response: Response<subastaResponse>){
                val mResponse = response.body()
                println("USD: ${mResponse!!.rates!!.USD.toString()}")
                println("CAD: ${mResponse!!.rates!!.CAD.toString()}")
                /* CoroutineScope(Dispatchers.Main).launch {
                     usd_text.text = "USD: ${mResponse!!.rates!!.USD.toString()}"
                     cad_text.text = "CAD: ${mResponse!!.rates!!.CAD.toString()}"
                 }*/
            }
        })

    }
}
