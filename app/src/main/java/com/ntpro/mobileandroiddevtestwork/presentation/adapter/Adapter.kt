package com.ntpro.mobileandroiddevtestwork.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ntpro.mobileandroiddevtestwork.Server
import com.ntpro.mobileandroiddevtestwork.databinding.ItemTableBinding


class Adapter(
    private val listOfLists: MutableList<Server.Deal>,
) : RecyclerView.Adapter<Adapter.ItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemTableBinding = ItemTableBinding.inflate(
            inflater,
            parent,
            false
        )

        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val list = listOfLists[position]
        with(holder.binding) {
            id.text = list.id.toString()
            timeStamp.text = list.timeStamp.time.toString()
            instrumentName.text = list.instrumentName
            price.text = list.price.toString()
            amount.text = list.amount.toString()
            side.text = list.side.toString()


        }
    }

    override fun getItemCount(): Int {
        return listOfLists.size
    }

    class ItemViewHolder(val binding: ItemTableBinding) :
        RecyclerView.ViewHolder(binding.root)

}
