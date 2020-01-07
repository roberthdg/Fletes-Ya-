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

        return inflater.inflate(R.layout.simulador_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
        viewModel = ViewModelProviders.of(this).get(SimuladorViewModel::class.java)

        button.setOnClickListener { CoroutineScope(IO).launch {
            fetchJson()
        }
        }
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

                val gson = GsonBuilder().create()

                val data = gson.fromJson(body, subastaListado::class.java)

                CoroutineScope(IO).launch {
                setTextOnMain(data.toString())
                }

            }
            override fun onFailure(call: Call, e: IOException) {
                println(e.toString())
            }
        })
    }

}




