package com.example.fletesya.ui.simulador

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity

import com.example.fletesya.R
import com.example.fletesya.data.Model.subastaListado
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.simulador_fragment.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import okhttp3.*
import java.io.IOException
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*


class SimuladorFragment : Fragment() {

    companion object {
        fun newInstance() = SimuladorFragment()
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
        (activity as AppCompatActivity).supportActionBar?.title = "Simulador"
        return inflater.inflate(R.layout.simulador_fragment, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
        viewModel = ViewModelProviders.of(this).get(SimuladorViewModel::class.java)

        button.setOnClickListener { CoroutineScope(IO).launch {
            fetchJson()
        }
        }
        /*  TODO: Use the ViewModel
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

                val data = gson.fromJson(body, subastaListado::class.java)

                var result1 = "\n"
                var i = 0

                data.subastas.forEach( {
                    result1 = result1+"Subasta #"+data.subastas[i].titulo+" "+ data.subastas[i].foto+"\n\n"
                    i=i+1
                })

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




