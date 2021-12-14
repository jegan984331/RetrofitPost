package com.example.retrofitpost.Repository

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.preferencesKey
import com.example.retrofitpost.Model.Retrofit.Post
import com.example.retrofitpost.Model.Retrofit.Retrofitinstance
import com.example.retrofitpost.Model.Retrofit.RoomDb.Emplyee
import com.example.retrofitpost.Model.Retrofit.RoomDb.EmplyeeDatabase
import retrofit2.Response

class Repositiory(private val emplyeeDao: EmplyeeDatabase) {


    suspend fun getpost(): Response<Post> {
        return Retrofitinstance.api.getpost()
    }

    suspend fun pustpost(post: Post): Response<Post> {
        return Retrofitinstance.api.pushpost(post)
    }

    fun allEmplyee() = emplyeeDao.getEmplyeeDao().getAllEmplyee()

    suspend fun insert(emplyee: Emplyee) =
        emplyeeDao.getEmplyeeDao().insert(emplyee)


    suspend fun update(emplyee: Emplyee) =
        emplyeeDao.getEmplyeeDao().update(emplyee)


    suspend fun delete(emplyee: Emplyee) =
        emplyeeDao.getEmplyeeDao().delete(emplyee)


}