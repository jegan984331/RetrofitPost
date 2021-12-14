package com.example.retrofitpost.View

import android.content.Context
import android.content.SharedPreferences

object PrefsHelper {
    private lateinit var prefs: SharedPreferences
    private const val PREFS_NAME = "params"

//    const val ID_USER = "id_user"
//    const val TOKEN = "token"

    fun init(context: Context) {
        prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun Show(key: String, value: String): String? {
        return prefs.getString(key, value)
    }


    fun Featch(key: String, value: String) {
        val prefsEditor: SharedPreferences.Editor = prefs.edit()
        with(prefsEditor) {
            putString(key, value)
            commit()
        }
    }
    fun remove(key:String) {
        prefs.edit().remove(key).apply()
    }
}