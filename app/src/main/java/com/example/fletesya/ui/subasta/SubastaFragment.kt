package com.example.fletesya.ui.subasta

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.fletesya.R
import com.example.fletesya.data.Model.subastaListado
import com.example.fletesya.data.Request.RetrofitClient
import com.example.fletesya.data.Response.subastaResponse
import com.example.fletesya.ui.simulador.SimuladorViewModel
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.oferta_fragment.*
import kotlinx.android.synthetic.main.simulador_fragment.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException


class SubastaFragment : Fragment() {

    companion object {
        fun newInstance() = SubastaFragment()
    }

    private lateinit var viewModel: SimuladorViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar?.title = "Subastas"
        return inflater.inflate(R.layout.subasta_fragment, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
        viewModel = ViewModelProviders.of(this).get(SimuladorViewModel::class.java)

        RetrofitClient.instance.subastaCall().enqueue(object : Callback<subastaResponse> {
            override fun onFailure(call: Call<subastaResponse>, t: Throwable) {
                println("wait a minute boi: "+ t.toString())
            }
            override fun onResponse(call: Call<subastaResponse>, response: Response<subastaResponse>){
                val sResponse = response.body()
                var result1 = "\n"
                var i = 0

                sResponse!!.subastas.forEach( {
                    result1 = result1+"Subasta #"+sResponse.subastas[i].titulo+" "+ sResponse.subastas[i].foto+"\n\n"
                    i=i+1
                })

                CoroutineScope(Dispatchers.Main).launch {
                    setTextOnMain(result1)
                }
            }
        })
    }

    private fun setNewText(input: String) {
        val newText = titulo.text.toString() + "\n$input"
        titulo.text = newText
    }


    private suspend fun setTextOnMain(input: String) {
        withContext(Dispatchers.Main){
            setNewText(input)
        }


    }


}




