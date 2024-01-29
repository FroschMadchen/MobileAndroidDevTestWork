package com.ntpro.mobileandroiddevtestwork.presentation.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ntpro.mobileandroiddevtestwork.APP_ACTIVITY
import com.ntpro.mobileandroiddevtestwork.Server
import com.ntpro.mobileandroiddevtestwork.databinding.FragmentTablesBinding
import com.ntpro.mobileandroiddevtestwork.presentation.adapter.Adapter
import java.util.Date

class FragmentTables : Fragment() {
    private var _binding: FragmentTablesBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var adapter: Adapter
    private var list = mutableListOf<Server.Deal>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTablesBinding.inflate(
            layoutInflater,
            container,
            false
        )
        list.add(Server.Deal(21, Date(23),"string",32.5,2.51,Server.Deal.Side.SELL))
        list.add(Server.Deal(51, Date(23),"string",32.5,2.51,Server.Deal.Side.SELL))
        list.add(Server.Deal(71, Date(23),"string",32.5,2.51,Server.Deal.Side.SELL))
        list.add(Server.Deal(81, Date(23),"string",32.5,2.51,Server.Deal.Side.SELL))

        adapter = Adapter(list)
        _binding?.recyclerView?.layoutManager=LinearLayoutManager(APP_ACTIVITY)
        _binding?.recyclerView?.adapter = adapter


        return mBinding.root
    }
}