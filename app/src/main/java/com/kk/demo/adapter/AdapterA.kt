package com.kk.demo.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kk.demo.databinding.ItemTemperatureADataLayoutBinding
import com.kk.demo.databinding.ItemTemperatureAImgLayoutBinding
import com.kk.demo.model.C0032.Temperature
import com.kk.demo.model.C0032.Time
import java.util.*
import kotlin.collections.HashMap

class AdapterA(
    private var mContext: Context?,
    private var mCallback: CallBack,
    private var mList: MutableList<Time> = ArrayList()
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface CallBack {
        fun checkDataA(data: Time)
    }


    // type 1 is data from temperature
    // type 2 is imageView
    val TYPE_ONE = 1
    val TYPE_TWO = 2
    private var mPosition: HashMap<Int, Int> = HashMap()
    private lateinit var contentABinding: ItemTemperatureADataLayoutBinding
    private lateinit var contentBBinding: ItemTemperatureAImgLayoutBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            TYPE_ONE -> {
                contentABinding = ItemTemperatureADataLayoutBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return ViewHolder1(contentABinding)
            }
            TYPE_TWO -> {
                contentBBinding = ItemTemperatureAImgLayoutBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return ViewHolder2(contentBBinding)
            }
            else -> {
                throw IllegalArgumentException()
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (mPosition[position] == TYPE_TWO) {
            (holder as ViewHolder2).bind()
        } else {
            (holder as ViewHolder1).bind(mList[position / 2], mCallback)
        }
    }

    override fun getItemCount(): Int {
        return mPosition.size
    }

    override fun getItemViewType(position: Int): Int {
        return mPosition[position]!!
    }

    fun setData(mData: Temperature) {
        this.mList.clear()
        this.mList.addAll(mData.records.location[0].weatherElement[0].time)
        mPosition = HashMap();
        for (i in 0 until mList.size) {
            mPosition[i * 2] = TYPE_ONE
            mPosition[i * 2 + 1] = TYPE_TWO
        }
        notifyDataSetChanged()
    }

    class ViewHolder1(private val mBinding: ItemTemperatureADataLayoutBinding) :
        RecyclerView.ViewHolder(mBinding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(data: Time,  mCallback: CallBack) {
            mBinding.itemTemperatureAStartTimeTxw.text = data.startTime
            mBinding.itemTemperatureAEndTimeTxw.text = data.endTime
            mBinding.itemTemperatureAValueTxw.text = data.parameter.parameterName + data.parameter.parameterUnit
            mBinding.root.setOnClickListener { mCallback.checkDataA(data) }
        }
    }


    class ViewHolder2(private val mBinding: ItemTemperatureAImgLayoutBinding) :
        RecyclerView.ViewHolder(mBinding.root) {

        fun bind() {

        }

    }
}