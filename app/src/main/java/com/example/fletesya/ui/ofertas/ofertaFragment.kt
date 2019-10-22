package com.example.fletesya.ui.ofertas

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

import com.example.fletesya.R
import com.example.fletesya.data.Request.RequestAPI
import com.example.fletesya.data.Response.ratesResponse
import com.example.fletesya.data.Response.subastaResponse
import kotlinx.android.synthetic.main.oferta_fragment.*
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ofertaFragment : Fragment() {

    companion object {
        fun newInstance() = ofertaFragment()
    }

    private val retrofit = Retrofit.Builder().baseUrl("https://fletesya.cl/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val postsApi = retrofit.create(RequestAPI::class.java)
    private val subastaResponse = postsApi.subastaLisatdo()

    private lateinit var viewModel: ofertaViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        (activity as AppCompatActivity).supportActionBar?.title = "Ofertas (Retrofit)"
        return inflater.inflate(R.layout.oferta_fragment, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
        viewModel = ViewModelProviders.of(this).get(ofertaViewModel::class.java)
        // TODO: Use the ViewModel

        subastaResponse.enqueue(object : Callback<subastaResponse> {
            override fun onFailure(call: Call<subastaResponse>, t: Throwable) {
                println("wait a minute boi: "+ t.toString())
            }
            override fun onResponse(call: Call<subastaResponse>, response: Response<subastaResponse>){
                val sResponse = response.body()
                println("subasta response: "+ sResponse!!.logos.toString())

                var result1 = "\n"
                var i = 0

                sResponse.logos.forEach( {
                    result1 = result1+"-"+sResponse.logos[i].logo+"\n\n"
                    i=i+1 })

                CoroutineScope(Dispatchers.Main).launch {
                    tituloOfertas.text = result1
                }
            }
        })
    }
/*
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }
*/
}
