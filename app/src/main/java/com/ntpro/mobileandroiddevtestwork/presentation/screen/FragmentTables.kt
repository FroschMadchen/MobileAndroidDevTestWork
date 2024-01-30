package com.ntpro.mobileandroiddevtestwork.presentation.screen


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ntpro.mobileandroiddevtestwork.APP_ACTIVITY
import com.ntpro.mobileandroiddevtestwork.R
import com.ntpro.mobileandroiddevtestwork.Server
import com.ntpro.mobileandroiddevtestwork.databinding.FragmentTablesBinding
import com.ntpro.mobileandroiddevtestwork.presentation.adapter.Adapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FragmentTables : Fragment() {
    private var _binding: FragmentTablesBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var adapter: Adapter
    private lateinit var server: Server
    private var list = mutableListOf<Server.Deal>()

    private var isSortAscending = true
    private var currentSortType: String = "дата изменения сделки"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTablesBinding.inflate(
            layoutInflater,
            container,
            false
        )

        val arraySpinner: Array<String> = arrayOf(
            getString(R.string.time_stamp),
            getString(R.string.instrument_name),
            getString(R.string.price),
            getString(R.string.amount),
            getString(R.string.side),

            )

        server = Server()
        handleDeals()

        adapter = Adapter(list)
        _binding?.recyclerView?.layoutManager = LinearLayoutManager(APP_ACTIVITY)
        _binding?.recyclerView?.adapter = adapter


        val adapter: ArrayAdapter<String> = ArrayAdapter(
            APP_ACTIVITY,
            com.google.android.material.R.layout.support_simple_spinner_dropdown_item,
            arraySpinner
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        _binding?.spinner?.adapter = adapter

        val itemSelectedListener: AdapterView.OnItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
                ) {
                    currentSortType = parent.getItemAtPosition(position) as String

                    applySorting()

                    adapter.notifyDataSetChanged()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
        _binding?.apply {
            spinner.onItemSelectedListener = itemSelectedListener
            btn.setOnClickListener {
                isSortAscending = !isSortAscending
                applySorting()
            }
        }
        return mBinding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    fun applySorting() {
        when (currentSortType) {

            getString(R.string.time_stamp) -> {
                if (isSortAscending) {
                    list.sortBy { it.timeStamp }
                } else {
                    list.sortByDescending { it.timeStamp }
                }
            }

            getString(R.string.instrument_name) -> {
                if (isSortAscending) {
                    list.sortBy { it.instrumentName }
                } else {
                    list.sortByDescending { it.instrumentName }
                }
            }

            getString(R.string.price) ->
                if (isSortAscending) {
                    list.sortBy { it.price }
                } else {
                    list.sortByDescending { it.price }
                }

            getString(R.string.amount) ->
                if (isSortAscending) {
                    list.sortBy { it.amount }
                } else {
                    list.sortByDescending { it.amount }
                }

            getString(R.string.side) -> if (isSortAscending) {
                list.sortBy { it.side == Server.Deal.Side.SELL }
            } else {
                list.sortBy { it.side == Server.Deal.Side.BUY }
            }
        }
        adapter.notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun serverDealsBatch(deals: List<Server.Deal>) {

        list.addAll(deals)
        adapter.notifyDataSetChanged()

    }

    private fun handleDeals() {
        CoroutineScope(Dispatchers.IO).launch {
            server.subscribeToDeals { deals ->

                serverDealsBatch(deals)

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}