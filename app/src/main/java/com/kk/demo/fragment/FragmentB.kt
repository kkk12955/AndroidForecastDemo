package com.kk.demo.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.kk.demo.R
import com.kk.demo.databinding.FragmentBBinding

class FragmentB : Fragment() {

    private lateinit var mBinding: FragmentBBinding

    private var startTime = ""
    private var endTime = ""
    private var value = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_b, container, false)
        arguments?.let {
            startTime = FragmentBArgs.fromBundle(it).startTime
            endTime = FragmentBArgs.fromBundle(it).endTime
            value = FragmentBArgs.fromBundle(it).value
        }
        initialView()
        return mBinding.root
    }

    private fun initialView() {
        //set data
        mBinding.apply {
            fragmentBDataStartTime.text = startTime
            fragmentBDataEndTime.text = endTime
            fragmentBDataValue.text = value
        }
    }

}