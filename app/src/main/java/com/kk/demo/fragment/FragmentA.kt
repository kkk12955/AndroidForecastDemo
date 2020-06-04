package com.kk.demo.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.FragmentNavigatorDestinationBuilder
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kk.demo.R
import com.kk.demo.adapter.AdapterA
import com.kk.demo.databinding.FragmentABinding
import com.kk.demo.model.C0032.Time
import com.kk.demo.viewmodel.FragmentAFactory
import com.kk.demo.viewmodel.FragmentAViewModel

class FragmentA : Fragment(), AdapterA.CallBack {

    private lateinit var mViewModel: FragmentAViewModel
    private lateinit var mViewModelFactory: FragmentAFactory
    private lateinit var mBinding: FragmentABinding
    private lateinit var mAdapter: AdapterA

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mViewModelFactory = FragmentAFactory(requireContext())
        mViewModel = ViewModelProvider(this, mViewModelFactory).get(FragmentAViewModel::class.java)
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_a, container, false)
        mBinding.lifecycleOwner = viewLifecycleOwner
        mAdapter = AdapterA(context, this)
        initialView()
        return mBinding.root
    }

    private fun initialView() {

        //init recycleView
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = RecyclerView.VERTICAL
        mBinding.fragmentARcview.apply {
            adapter = mAdapter
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
        }
        setObserver()
    }

    private fun setObserver() {
        //viewModel
        mViewModel.temperatureList.observe(viewLifecycleOwner, Observer { list ->
            if (list != null) {
                if (!list.records.location[0].weatherElement[0].time.isNullOrEmpty()) {
                    mAdapter.setData(list)
                }
            }
        })
    }

    override fun checkDataA(data: Time) {
        findNavController().navigate(
            FragmentADirections.actionFragmentAToFragmentB(
                data.startTime,
                data.endTime,
                data.parameter.parameterName + data.parameter.parameterUnit
            )
        )
    }
}