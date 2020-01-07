package com.example.fletesya.ui.subasta

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fletesya.R
import com.example.fletesya.activities.MainActivity
import com.example.fletesya.data.Request.RetrofitClient
import com.example.fletesya.data.Response.subastaResponse
import com.example.fletesya.ui.TopSpacingItemDecoration
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SubastaFragment : Fragment() {

    lateinit var recyclerView: RecyclerView

    private lateinit var subastaAdapter: SubastaRecyclerAdapter

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
        val rootView = inflater.inflate(R.layout.subasta_fragment, container, false)

        recyclerView = rootView.findViewById(R.id.recyclerSubasta)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            val topSpacingDecorator = TopSpacingItemDecoration(30)
            addItemDecoration(topSpacingDecorator)
            subastaAdapter = SubastaRecyclerAdapter()
            adapter = subastaAdapter
        }

        RetrofitClient.instance.subastaCall().enqueue(object : Callback<subastaResponse> {
            override fun onFailure(call: Call<subastaResponse>, t: Throwable) {
                println(t.toString())
            }
            override fun onResponse(call: Call<subastaResponse>, response: Response<subastaResponse>){
                val sResponse = response.body()
                subastaAdapter.setSubastas(sResponse!!.subastas)
            }
        })

        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
    }

}




