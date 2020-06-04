package com.kk.demo.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class FragmentAFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(FragmentAViewModel::class.java)) {
            return FragmentAViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}