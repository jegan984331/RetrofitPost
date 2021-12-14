package com.example.retrofitpost.View.Fragement

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.retrofitpost.R
import com.example.retrofitpost.View.view_perference
import com.example.retrofitpost.ViewModel.MainViewmodel
import com.example.retrofitpost.ViewModel.PreferenceViewModel


class PreferrenceFragment : Fragment() {
     private lateinit var preferenceViewModel: PreferenceViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_preferrence, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val inputId = view.findViewById<EditText>(R.id.name)
        val inputId2 = view.findViewById<EditText>(R.id.age)
        val savebtn = view.findViewById<Button>(R.id.Save)
        val shoetxt = view.findViewById<TextView>(R.id.show)
        val shoetxt2 = view.findViewById<TextView>(R.id.show2)
       preferenceViewModel =ViewModelProvider(this).get(PreferenceViewModel::class.java)
        preferenceViewModel.read.observe(viewLifecycleOwner,{Myname->
            shoetxt.text=Myname
        })
        preferenceViewModel.read2.observe(viewLifecycleOwner,{Myage->
            shoetxt2.text=Myage
        })
        savebtn.setOnClickListener(View.OnClickListener {
            val Myname:String=inputId.text.toString()
            val Myage:String=inputId2.text.toString()
            preferenceViewModel.Save(Myname,Myage)
        })

}
}