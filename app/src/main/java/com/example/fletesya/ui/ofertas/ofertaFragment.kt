package com.example.fletesya.ui.ofertas

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity

import com.example.fletesya.R
import com.example.fletesya.data.Request.RetrofitClient
import com.example.fletesya.data.Response.ofertaResponse
import com.example.fletesya.data.Response.subastaResponse
import kotlinx.android.synthetic.main.oferta_fragment.*
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ofertaFragment : Fragment() {

    companion object {
        fun newInstance() = ofertaFragment()
    }

    private lateinit var viewModel: ofertaViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        (activity as AppCompatActivity).supportActionBar?.title = "Ofertas"
        return inflater.inflate(R.layout.oferta_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
        viewModel = ViewModelProviders.of(this).get(ofertaViewModel::class.java)
        // TODO: Use the ViewModel

        RetrofitClient.instance.ofertaCall().enqueue(object : Callback<ofertaResponse> {
            override fun onFailure(call: Call<ofertaResponse>, t: Throwable) {
                println("wait a minute boi: "+ t.toString())
            }
            override fun onResponse(call: Call<ofertaResponse>, response: Response<ofertaResponse>){
                val sResponse = response.body()
                var result1 = "\n"
                var i = 0

                sResponse!!.ofertas.forEach({
                    result1 = result1+"-"+sResponse.ofertas[i].titulo+" "+sResponse.ofertas[i].foto+"\n\n"
                    i=i+1 })

                CoroutineScope(Dispatchers.Main).launch {
                    tituloOfertas.text = result1
                }
            }
        })
    }

}
