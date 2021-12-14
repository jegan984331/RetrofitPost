package com.example.retrofitpost.View

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

import com.example.retrofitpost.R

class view_perference : AppCompatActivity() {
    private val sharedPrefFile = "kotlinAndroid"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_perference)


        val outputId = findViewById<TextView>(R.id.textViewShowId)
        val outputName = findViewById<TextView>(R.id.textViewShowName)
        val outputGender = findViewById<TextView>(R.id.textViewShowGender)
        val outputDes = findViewById<TextView>(R.id.textViewShowDesignation)
        val btnClear = findViewById<Button>(R.id.clear)


        val sharedPreferences: SharedPreferences? =
            this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)

        val sharedIdValue = sharedPreferences!!.getInt("id_key", 0)
        val sharedNameValue = sharedPreferences.getString("name_key", "NAME")
        val sharedGenderValue = sharedPreferences.getString("gender_key", "GENDER")
        val sharedDEsValue = sharedPreferences.getString("DES_key", "DES")

        if (sharedIdValue.equals(0) && sharedNameValue.equals("defaultname")) {
            outputName.setText("default name: ${sharedNameValue}").toString()
            outputId.setText("default id: ${sharedIdValue}").toString()
            outputGender.setText("default gender: ${sharedGenderValue}").toString()
            outputDes.setText("default DES: ${sharedDEsValue}").toString()
        } else {
            outputName.setText(sharedNameValue).toString()
            outputId.setText(sharedIdValue.toString())
            outputGender.setText(sharedGenderValue.toString())
            outputDes.setText(sharedDEsValue.toString())
        }
        btnClear.setOnClickListener(View.OnClickListener {
            val editor = sharedPreferences.edit()
            editor.clear()
            editor.apply()
            outputName.setText("").toString()
            outputGender.setText("").toString()
            outputDes.setText("").toString()
            outputId.setText("".toString())
            Toast.makeText(this, "DELETE DETAILS SUCCESFULL!!", Toast.LENGTH_SHORT).show()


        })

    }
}


