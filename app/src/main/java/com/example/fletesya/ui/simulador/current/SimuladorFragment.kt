package com.example.fletesya.ui.simulador.current

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.InputDevice
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.fletesya.R
import com.example.fletesya.data.fletesyaAPI
import kotlinx.android.synthetic.main.simulador_fragment.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import org.kodein.di.bindings.WithContext

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

        button.setOnClickListener { CoroutineScope(IO).launch {
            fakeApiRequest()
        }
        }
        /* TODO: Use the ViewModel
        val apiService = fletesyaAPI()

        GlobalScope.launch(Dispatchers.Main) {
            val SimuladorFragment = apiService.listado().await()
          //  texxxt.text = SimuladorFragment.toString()
        }*/
    }



    private val RESULT_1 = "Resultado 1111111"
    private val RESULT_2 = "Resultado 2222222"


    private suspend fun setTextOnMain(input: String) {
        withContext(Main){
            setNewText(input)
        }

    }

    private fun setNewText(input: String) {
        val newText = texxxt.text.toString() + "\n$input"
        texxxt.text = newText
    }

    private suspend fun fakeApiRequest() {
        val result1 = getResult()
        print("debug: ${result1}")
        setTextOnMain(result1)

        val result2 = getResult2()
        print("debug: ${result2}")
        setTextOnMain(result2)
    }

    private suspend fun getResult(): String{
        logThread("getResult")
        delay(1000)
        return RESULT_1
    }

    private suspend fun getResult2(): String{
        logThread("getResult2")
        delay(1000)
        return RESULT_2
    }


    private fun logThread(methodName: String) {
        println("debug: ${methodName}: ${Thread.currentThread().name}")
    }

}
