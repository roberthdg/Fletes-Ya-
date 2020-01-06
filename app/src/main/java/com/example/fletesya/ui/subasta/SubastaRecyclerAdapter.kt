package com.example.fletesya.ui.subasta

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.fletesya.R
import com.example.fletesya.data.Model.Subasta
import kotlinx.android.synthetic.main.subasta_item.view.*

class SubastaRecyclerAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    var items: List<Subasta> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SubastaViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.subasta_item, parent, false)
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

    fun setSubastas(items: List<Subasta>){
        this.items = items
        notifyDataSetChanged()
    }

    class SubastaViewHolder constructor(
        itemView: View
    ):RecyclerView.ViewHolder(itemView){
        val tituloSubasta = itemView.titulo_subasta
        val fotoSubasta = itemView.foto_subasta

        fun bind(subasta: Subasta) {
            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(subasta.foto)
                .into(fotoSubasta)

            tituloSubasta.setText(subasta.titulo)
        }

    }

}