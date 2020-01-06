package com.example.fletesya.ui.ofertas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.fletesya.R
import com.example.fletesya.data.Model.Oferta
import com.example.fletesya.data.Model.Subasta
import kotlinx.android.synthetic.main.oferta_item.view.*
import kotlinx.android.synthetic.main.subasta_item.view.*

class OfertaRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    var items: List<Oferta> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SubastaViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.oferta_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is SubastaViewHolder ->{
                holder.bind(items.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setOfertas(items: List<Oferta>){
        this.items = items
        notifyDataSetChanged()
    }

    class SubastaViewHolder constructor(
        itemView: View
    ):RecyclerView.ViewHolder(itemView){
        val tituloOferta = itemView.titulo_oferta
        val fotoOferta = itemView.foto_oferta

        fun bind(oferta: Oferta) {
            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(oferta.foto)
                .into(fotoOferta)

            tituloOferta.setText(oferta.titulo)
        }

    }

}