package com.example.prodan

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.prodan.data.Data
import com.example.prodan.databinding.CarouselItemBinding

class adaptercarousel (val context: Context, var data: List<Data>,
               private val funcionX : (Data) ->Unit ) :
    RecyclerView.Adapter<adaptercarousel.ViewHolder>()
{

    class ViewHolder(val binding: CarouselItemBinding, funcionZ: (Int) -> Unit  ) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                funcionZ(adapterPosition)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = CarouselItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view) {
            funcionX(data[it])
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            name.text = data[position].attributes.name
        }
        Glide.with(context)
            .load(data[position].attributes.image?.data?.attributes?.formats?.small?.url)
            .into(holder.binding.image)

    }

    override fun getItemCount(): Int {
        return data.size
    }
}