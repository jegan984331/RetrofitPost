package com.example.retrofitpost.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitpost.Model.Retrofit.Post
import com.example.retrofitpost.Model.Retrofit.RoomDb.Emplyee
import com.example.retrofitpost.Repository.Repositiory

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewmodel(private val repositry:Repositiory ): ViewModel() {

    val myreponse: MutableLiveData<Response<Post>> = MutableLiveData()


    fun getpost() {
        viewModelScope.launch {
            val response: Response<Post> = repositry.getpost()
            myreponse.value = response
        }
    }

    fun pushpost(post: Post) {
        viewModelScope.launch {
            val response= repositry.pustpost(post)
            myreponse.value = response
        }
    }

    fun deleteEmplyee(emplyee: Emplyee) {
        viewModelScope.launch {
            repositry.delete(emplyee)
        }
    }
        fun updateEmplyeeDetails(emplyee: Emplyee) {
            viewModelScope.launch  {
            repositry.update(emplyee)
        }
        }

        fun addEmplyeeDetails(emplyee: Emplyee) {
            viewModelScope.launch  {
            repositry.insert(emplyee)
        }

    }    fun allEmplyeesDetails() = repositry.allEmplyee()
}



