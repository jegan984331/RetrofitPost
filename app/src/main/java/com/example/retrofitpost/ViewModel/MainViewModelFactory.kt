package com.example.retrofitpost.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.retrofitpost.Repository.Repositiory


class MainViewModelFactory(private val repositiory: Repositiory):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewmodel(repositiory) as T
    }

}
