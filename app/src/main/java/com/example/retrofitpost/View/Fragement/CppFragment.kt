package com.example.retrofitpost.View.Fragement

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.retrofitpost.R
import com.example.retrofitpost.Service.Airplane


@Suppress("KotlinJniMissingFunction")
class CppFragment : Fragment() {

    companion object {
        init {
            System.loadLibrary("retrofitpost")
        }
    }

    lateinit var show: TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cpp, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        show = view.findViewById(R.id.text2)
        show.text = stringFromJNI()


    }

    external fun stringFromJNI(): String
}