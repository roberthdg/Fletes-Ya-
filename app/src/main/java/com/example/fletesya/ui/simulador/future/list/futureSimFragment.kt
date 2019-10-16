package com.example.fletesya.ui.simulador.future.list

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.fletesya.R

class futureSimFragment : Fragment() {

    companion object {
        fun newInstance() = futureSimFragment()
    }

    private lateinit var viewModel: FutureSimViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.future_sim_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FutureSimViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
