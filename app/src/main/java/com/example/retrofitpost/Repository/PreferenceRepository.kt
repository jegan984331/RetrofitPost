package com.example.retrofitpost.Repository

import android.content.Context
import android.util.Log
import androidx.datastore.DataStore

import androidx.datastore.preferences.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException


class PreferenceRepository(context: Context) {
    private object preferencekey {
        val name = preferencesKey<String>("my_name")
        val age = preferencesKey<String>("my_age")
    }

    private val datastore: DataStore<Preferences> =
        context.createDataStore(name = Companion.PREFERENCE_NAME)

    suspend fun SetUserName(UserName: String, Userage: String) {
        datastore.edit { preferences ->
            preferences[preferencekey.name] = UserName
        }
        datastore.edit { preferences ->
            preferences[preferencekey.age] = Userage
        }
    }

    val getUSerName: Flow<String> = datastore.data.catch { exception ->
        if (exception is IOException) {
            Log.d("DataStore", exception.message.toString())
            emit(emptyPreferences())
        } else {
            throw exception
        }
    }.map { preference ->
        val MyName: String = preference[preferencekey.name] ?: "none"
        MyName
    }
    val getUSerage: Flow<String> = datastore.data.catch { exception ->
        if (exception is IOException) {
            Log.d("DataStore", exception.message.toString())
            emit(emptyPreferences())
        } else {
            throw exception
        }
    }.map { preference ->
        val Myage: String = preference[preferencekey.age] ?: "age"
        Myage
    }

    companion object {
        const val PREFERENCE_NAME = "my_preference"
    }
}