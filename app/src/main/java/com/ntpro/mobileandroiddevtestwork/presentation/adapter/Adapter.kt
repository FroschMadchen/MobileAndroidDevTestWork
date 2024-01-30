package com.ntpro.mobileandroiddevtestwork.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.ntpro.mobileandroiddevtestwork.APP_ACTIVITY
import com.ntpro.mobileandroiddevtestwork.R
import com.ntpro.mobileandroiddevtestwork.Server
import com.ntpro.mobileandroiddevtestwork.databinding.ItemTableBinding
import java.text.DecimalFormat
import kotlin.math.round


class Adapter(
    private val list: MutableList<Server.Deal>,
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
        val deal = list[position]

        with(holder.binding) {
            id.text = deal.id.toString()
            timeStamp.text = deal.timeStamp.time.toString()
            instrumentName.text = deal.instrumentName
            price.text = DecimalFormat("#.00").format(deal.price)
            amount.text = round(deal.amount).toInt().toString()
            side.text = deal.side.toString()

            if (deal.side == Server.Deal.Side.SELL) {
                price.setTextColor(ContextCompat.getColor(APP_ACTIVITY, R.color.sellColor))
            } else {
                price.setTextColor(ContextCompat.getColor(APP_ACTIVITY, R.color.buyColor))
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ItemViewHolder(val binding: ItemTableBinding) :
        RecyclerView.ViewHolder(binding.root)
}
