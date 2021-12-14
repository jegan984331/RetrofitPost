package com.example.retrofitpost.View.Fragement

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.retrofitpost.R
import com.example.retrofitpost.Service.NewService

class ServiceFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_service, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var start: Button?
        var stop: Button?


        start = view.findViewById<View>(R.id.startButton) as Button

        stop = view.findViewById<View>(R.id.stopButton) as Button

        start.setOnClickListener(View.OnClickListener {
            activity?.startService(Intent(activity, NewService::class.java))
        })
        stop.setOnClickListener(View.OnClickListener {
            activity?.stopService(Intent(activity, NewService::class.java))
        })
    }


}