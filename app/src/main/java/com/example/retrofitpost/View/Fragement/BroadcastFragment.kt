package com.example.retrofitpost.View.Fragement

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.retrofitpost.R
import com.example.retrofitpost.Service.Airplane
import com.example.retrofitpost.View.PrefsHelper

class BroadcastFragment : Fragment() {
    lateinit var receiver: Airplane
    lateinit var key:EditText
    lateinit var vale:EditText
    lateinit var showvalue:TextView
    lateinit var ADD:Button
    lateinit var SHOW:Button
    lateinit var DElETE:Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_broadcast, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        receiver = Airplane()
        key =view.findViewById(R.id.textView)
        vale =view.findViewById(R.id.textView2)
        showvalue =view.findViewById(R.id.editText)
        ADD =view.findViewById(R.id.button)
        SHOW =view.findViewById(R.id.button2)
        DElETE =view.findViewById(R.id.button3)
        PrefsHelper.init(activity?.applicationContext!!)


        ONOFFAIRPLAINMODE()

       ADD.setOnClickListener(View.OnClickListener {
           PrefsHelper.Featch(key.text.toString(),vale.text.toString())
           Toast.makeText(activity,"ADD SUCESSFULLY",Toast.LENGTH_SHORT).show()
       })
        SHOW.setOnClickListener(View.OnClickListener {
           showvalue.setText(PrefsHelper.Show(key.text.toString(),vale.text.toString()))
            Toast.makeText(activity,"SHOW SUCESSFULLY",Toast.LENGTH_SHORT).show()
        })
        DElETE.setOnClickListener(View.OnClickListener {
            PrefsHelper.remove(key.text.toString())
            showvalue.setText("").toString()
            Toast.makeText(activity,"DELETE SUCESSFULLY",Toast.LENGTH_SHORT).show()
        })

    }

    private fun ONOFFAIRPLAINMODE(){
        IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED).also {
            activity?.application!!.registerReceiver(receiver, it)
        }
    }
    override fun onStop() {
        super.onStop()
        activity?. unregisterReceiver(receiver)
    }
}