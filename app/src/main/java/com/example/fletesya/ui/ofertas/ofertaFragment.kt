package com.example.fletesya.ui.ofertas

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.fletesya.R
import com.example.fletesya.activities.MainActivity
import com.example.fletesya.data.Model.Oferta
import com.example.fletesya.data.Request.RetrofitClient
import com.example.fletesya.data.Response.ofertaResponse
import com.example.fletesya.data.Response.subastaResponse
import com.example.fletesya.ui.TopSpacingItemDecoration
import com.example.fletesya.ui.subasta.SubastaRecyclerAdapter
import kotlinx.android.synthetic.main.oferta_fragment.*
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ofertaFragment : Fragment() {

    companion object {
        fun newInstance() = ofertaFragment()
    }

    lateinit var recyclerView: RecyclerView

    private lateinit var ofertaAdapter: OfertaRecyclerAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        val rootView = inflater.inflate(R.layout.subasta_fragment, container, false)

        recyclerView = rootView.findViewById(R.id.recyclerSubasta)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            val topSpacingDecorator = TopSpacingItemDecoration(30)
            addItemDecoration(topSpacingDecorator)
            ofertaAdapter = OfertaRecyclerAdapter()
            adapter = ofertaAdapter
        }

        RetrofitClient.instance.ofertaCall().enqueue(object : Callback<ofertaResponse> {
            override fun onFailure(call: Call<ofertaResponse>, t: Throwable) {
                println(t.toString())
            }
            override fun onResponse(call: Call<ofertaResponse>, response: Response<ofertaResponse>){
                val sResponse = response.body()
                ofertaAdapter.setOfertas(sResponse!!.ofertas)
            }
        })
        return rootView
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
    }

}
