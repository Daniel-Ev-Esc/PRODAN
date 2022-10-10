package com.example.prodan

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.prodan.data.pets
import com.example.prodan.databinding.PetItemBinding

class adapter (val context: Context, var data: List<pets>,
               private val funcionX : (pets) ->Unit ) :
    RecyclerView.Adapter<adapter.ViewHolder>()
{

    // Cambio de filtros
    // Recibe una nueva lista y notifica que cambió
    fun filterList(filterList: List<pets>){
        data = filterList
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: PetItemBinding, funcionZ: (Int) -> Unit  ) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                funcionZ(adapterPosition)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = PetItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view) {
            funcionX(data[it])
        }

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            textViewAge.text = data[position].edad
            textViewName.text = data[position].nombre
            imageViewGender.setImageResource(data[position].sexo)
        }
        /*
        holder.binding.textViewId.text = data[position].id
        holder.binding.textViewNombre.text = data[position].nombre*/

        Glide.with(context)
            .load(data[position].imagen)
            .into(holder.binding.imageViewPhoto)

    }

    override fun getItemCount(): Int {
        return data.size
    }

}