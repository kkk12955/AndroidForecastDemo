package com.kk.demo.conf

import android.content.Context
import android.content.SharedPreferences

/*
* Need to put these where you want to use (like Activity,Fragment...etc)
* import com.kk.demo.conf.SharedPref.get
* import com.kk.demo.conf.SharedPref.set
* */
object SharedPref {

    enum class SharedPreferenceKey {
        isFirstStart, // default: false -- first start application set true
    }

    fun customPrefs(context: Context, name: String): SharedPreferences
            = context.getSharedPreferences(name, Context.MODE_PRIVATE)

    inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = this.edit()
        operation(editor)
        editor.apply()
    }

    /**
     * puts a key value pair in shared prefs if doesn't exists, otherwise updates value on given [key]
     */
    operator fun SharedPreferences.set(key: SharedPreferenceKey, value: Any?) {
        when (value) {
            is String? -> edit({ it.putString(key.name, value) })
            is Int -> edit({ it.putInt(key.name, value) })
            is Boolean -> edit({ it.putBoolean(key.name, value) })
            is Float -> edit({ it.putFloat(key.name, value) })
            is Long -> edit({ it.putLong(key.name, value) })
            else -> throw UnsupportedOperationException("Not yet implemented")
        }
    }

    /**
     * finds value on given key.
     * [T] is the type of value
     * @param defaultValue optional default value - will take null for strings, false for bool and -1 for numeric values if [defaultValue] is not specified
     */
    operator inline fun <reified T : Any> SharedPreferences.get(key: SharedPreferenceKey, defaultValue: T? = null): T? {
        return when (T::class) {
            String::class -> getString(key.name, defaultValue as? String) as T?
            Int::class -> getInt(key.name, defaultValue as? Int ?: -1) as T?
            Boolean::class -> getBoolean(key.name, defaultValue as? Boolean ?: false) as T?
            Float::class -> getFloat(key.name, defaultValue as? Float ?: -1f) as T?
            Long::class -> getLong(key.name, defaultValue as? Long ?: -1) as T?
            else -> throw UnsupportedOperationException("Not yet implemented")
        }
    }

}