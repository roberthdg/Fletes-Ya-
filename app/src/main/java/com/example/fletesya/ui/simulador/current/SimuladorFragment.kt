package com.example.fletesya.ui.simulador.current

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.fletesya.R

class SimuladorFragment : Fragment() {

    companion object {
        fun newInstance() = SimuladorFragment()
    }

    private lateinit var viewModel: SimuladorViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.simulador_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SimuladorViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
