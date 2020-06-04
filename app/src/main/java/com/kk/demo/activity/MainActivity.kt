package com.kk.demo.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.kk.demo.R
import com.kk.demo.conf.SharedPref
import com.kk.demo.databinding.ActivityMainBinding
import com.kk.demo.conf.SharedPref.get
import com.kk.demo.conf.SharedPref.set


class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mNavController : NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        initialView()
        checkFirstOpen()
    }

    private fun initialView(){
        //init navigation
        mNavController = this.findNavController(R.id.main_view_fragment)
        NavigationUI.setupActionBarWithNavController(this, mNavController)
    }

    private fun checkFirstOpen(){
        // use sharedPreferences to check first open or not
        val mPref = SharedPref.customPrefs(this, "parameterSetting")
        if(mPref[SharedPref.SharedPreferenceKey.isFirstStart, false]!!){
            Toast.makeText(this, R.string.main_activity_toast_welcome_txw, Toast.LENGTH_SHORT).show()
        } else {
            mPref[SharedPref.SharedPreferenceKey.isFirstStart] = true
        }
    }
}