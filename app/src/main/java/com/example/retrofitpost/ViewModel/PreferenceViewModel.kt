package com.example.retrofitpost.ViewModel

import android.app.Application
import androidx.lifecycle.*
import com.example.retrofitpost.Repository.PreferenceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PreferenceViewModel(application: Application) :AndroidViewModel(application){
    private val repository = PreferenceRepository(application)
    val read=repository.getUSerName.asLiveData()
    val read2 = repository.getUSerage.asLiveData()
    fun Save(Myname:String,Myage:String)=viewModelScope.launch(Dispatchers.IO){
        repository.SetUserName(Myname,Myage)
    }

}