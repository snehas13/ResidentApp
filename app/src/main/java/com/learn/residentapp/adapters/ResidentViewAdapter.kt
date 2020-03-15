package com.learn.residentapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.learn.domain.entities.Resident
import androidx.databinding.DataBindingUtil
import com.learn.residentapp.databinding.ResidentItemBinding

class ResidentViewAdapter(val listener : ItemClickListener) : RecyclerView.Adapter<ResidentViewAdapter.ViewHolder>() {

    private val items:MutableList<Resident> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ResidentItemBinding.inflate(inflater,parent,false)
        return ViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun update(residents: List<Resident>) {
        items.clear()
        items.addAll(residents)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    interface ItemClickListener {
        fun onItemClick(resident: Resident)
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        var binding : ResidentItemBinding? = DataBindingUtil.bind(itemView)

        fun bind(resident: Resident) {
            binding?.resident = resident

            itemView.setOnClickListener {
                listener.onItemClick(resident)
            }
        }
    }
}