package com.example.fletesya.ui.simulador

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity

import com.example.fletesya.R
import com.example.fletesya.data.Model.logoListado
import com.example.fletesya.data.Model.subastaListado
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.simulador_fragment.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import okhttp3.*
import java.io.IOException
import com.squareup.picasso.Picasso


class SimuladorFragment : Fragment() {

    companion object {
        fun newInstance() = SimuladorFragment()
    }

    private lateinit var viewModel: SimuladorViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        super.onCreate(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = "Subastas (gsonBuilder)"
        return inflater.inflate(R.layout.simulador_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SimuladorViewModel::class.java)

        button.setOnClickListener { CoroutineScope(IO).launch {
            fetchJson()
        }
        }
        /*  TODO: Use the ViewModel
        val apiService = fletesyaAPI()

        GlobalScope.launch(Dispatchers.Main) {
            val SimuladorFragment = apiService.listado().await()
            texxxt.text = SimuladorFragment.toString()
        }

        */
    }

   private fun setNewText(input: String) {
        val newText = titulo.text.toString() + "\n$input"
        titulo.text = newText
    }


    private suspend fun setTextOnMain(input: String) {
        withContext(Main){
            setNewText(input)
        }


    }


    suspend fun fetchJson(){
        println("attempting to fetch JSON")

        val url = "https://fletesya.cl/api/listado"

        val request = Request.Builder().url(url).build()

        val client = OkHttpClient()

        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                println(body)

                val gson = GsonBuilder().create()

                val subastas = gson.fromJson(body, subastaListado::class.java)

                val logos = gson.fromJson(body, logoListado::class.java)

                var result1 = "\n"
                var i = 0

                subastas.data.forEach( {
                    result1 = result1+"Subasta #"+subastas.data[i].id_subasta.toString()+" desde: "+ subastas.data[i].comuna_origen +" hasta "+ subastas.data[i].comuna_destino+"\n\n"
                    // print("debug: ${result1}")
                    i=i+1
                })


                val result2 = logos.toString()
                print("debug: ${result2}")

                CoroutineScope(IO).launch {
                setTextOnMain(result1)
               // setTextOnMain(result2)
                }



            }
            override fun onFailure(call: Call, e: IOException) {
                println(e.toString())
                println("BOIOIII")
            }
        })
    }

}




