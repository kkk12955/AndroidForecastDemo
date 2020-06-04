package com.kk.demo.conf

import android.content.Context
import android.content.pm.PackageManager

class Config {

    companion object {

        /**
         * DEV:test on device with cable
         */
        enum class Env constructor(val host: String) {
            DEV("opendata.cwb.gov.tw")
        }

        val VERSION = 1
        val mEnv = Env.DEV

        //use this to get api domain
        fun getEndPoint(): String {
            return getEndPoint(mEnv, VERSION)
        }

        fun getEndPoint(mEnv: Env, version: Int): String {
            return String.format("https://%s/api/v%d/", mEnv.host, version)
        }

        fun getAuthorication(context: Context): String? {
            context.packageManager.getApplicationInfo(context.packageName, PackageManager.GET_META_DATA).apply {
                return metaData.getString("com.kk.demo.Authorization")
            }
        }
    }
}
