package com.example.tfgfrontcamarero.ui.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tfgfrontcamarero.R
import com.example.tfgfrontcamarero.data.model.ProductosPedido

class AdapterRecyclerView (private val productosNuevos:  MutableList<ProductosPedido>) :
    RecyclerView.Adapter<AdapterRecyclerView.MyViewHolder>() {

    private var itemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        itemClickListener = listener
    }

    interface OnItemClickListener {
        fun onItemClick(item: MutableList<ProductosPedido>)
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView1: TextView = itemView.findViewById(R.id.textView1)
        val botonArriba: Button = itemView.findViewById(R.id.btnArriba)
        val botonAbajo: Button = itemView.findViewById(R.id.btnAbajo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val productoNuevo = productosNuevos[position]
        var cantidad: Int? = productoNuevo.cantidad
        val nombre: String? = productoNuevo.producto?.nombre
        val preci: Float? = productoNuevo.producto?.precio
        val item:String = "${cantidad.toString()}x $nombre   ${(cantidad!! * preci!!)} €"

        holder.textView1.text = item

        holder.botonArriba.setOnClickListener{
            productoNuevo.cantidad = productoNuevo.cantidad!! + 1
            cantidad = productoNuevo.cantidad
            holder.textView1.text = "${cantidad.toString()}x $nombre   ${(cantidad!! * preci!!)} €"
            productosNuevos[position]=productoNuevo
            itemClickListener?.onItemClick(productosNuevos)
        }

        holder.botonAbajo.setOnClickListener{
            if (productoNuevo.cantidad!! >1){
                productoNuevo.cantidad = productoNuevo.cantidad!! - 1
                cantidad = productoNuevo.cantidad
                holder.textView1.text = "${cantidad.toString()}x $nombre   ${(cantidad!! * preci!!)} €"
                productosNuevos[position]=productoNuevo
                itemClickListener?.onItemClick(productosNuevos)

            }else{
                productoNuevo.cantidad = 0
                cantidad=0
                holder.textView1.text = "${cantidad.toString()}x $nombre   ${(cantidad!! * preci!!)} €"
                productosNuevos[position]=productoNuevo
                itemClickListener?.onItemClick(productosNuevos)

            }

        }



    }

    override fun getItemCount() = productosNuevos.size
}