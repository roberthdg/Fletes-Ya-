/*package com.example.fletesya.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fletesya.data.models.logoListado
import com.example.fletesya.data.models.subastaListado
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.content_row.view.*

class MainAdapter(val listadoSubasta: subastaListado, val logoListado: logoListado): RecyclerView.Adapter<CustomViewHolder>() {

    //Number of items
    override fun getItemCount(): Int {
       return 4
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {

        val LayoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = LayoutInflater.inflate(R.layout.content_row, parent, false)
        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val subasta = listadoSubasta.data.get(position)
        val imagen = logoListado.logos.get(position)

        holder?.view?.textView_subasta_titulo.text = subasta.id_subasta.toString()

        holder.view.subasta_origen.text = subasta.comuna_origen

        val thumbnail = holder?.view?.imageViewLogo

        Picasso.get().load(imagen.logo).into(thumbnail)

    }

}

class CustomViewHolder(val view: View): RecyclerView.ViewHolder(view) {

    companion object {
       val TITLE_KEY="titulo"

    }
    init {
        view.setOnClickListener{
            println("LOS SANTOS")
            val intent = Intent(view.context, DetailsActivity::class.java)

            intent.putExtra(TITLE_KEY, "Segunda pantalla")
            view.context.startActivity(intent)

        }
    }
}

        */