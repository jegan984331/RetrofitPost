package com.example.retrofitpost.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import com.example.retrofitpost.Model.Retrofit.RoomDb.Emplyee
import com.example.retrofitpost.Model.Retrofit.RoomDb.EmplyeeDatabase
import com.example.retrofitpost.R
import com.example.retrofitpost.Repository.Repositiory

import com.example.retrofitpost.ViewModel.MainViewModelFactory
import com.example.retrofitpost.ViewModel.MainViewmodel


class updateActivity : AppCompatActivity() {
    lateinit var Editname: EditText
    lateinit var Editage: EditText
    lateinit var Editcode: EditText
    lateinit var Editdesignation: Spinner
    lateinit var EditButton: Button
    lateinit var viewModel: MainViewmodel

    var EmolyeeID = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)
        EditButton = findViewById(R.id.btnclick)
        Editname = findViewById(R.id.Etname)
        Editage = findViewById(R.id.Etage)
        Editcode = findViewById(R.id.Etcode)
        Editdesignation = findViewById(R.id.Etdesignation)

        val repository= Repositiory(EmplyeeDatabase(this))
        val viewModelFactory= MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this,viewModelFactory).get(MainViewmodel::class.java)

       //spinner setup
        val Designation = arrayOf("Android Developer","Web Developer","IOS Developer")
        val arrayAdapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,Designation)
        Editdesignation.adapter= arrayAdapter
        Editdesignation.onItemSelectedListener =object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View, position: Int, id: Long) {

            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }







         //Get the intent Value
        val emplyeeType = intent.getStringExtra("emplyeeType")
        if (emplyeeType.equals("Edit")) {
            val EmplyeeName = intent.getStringExtra("emplyeename")
            val EmplyeeAge = intent.getStringExtra("emplyeeage")
            val EmplyeeCode = intent.getStringExtra("emplyeecode")
            //val EmplyeeDesigination = intent.getStringExtra("emplyeedesignation")
            EmolyeeID = intent.getIntExtra("emplyeeid", -1)
            EditButton.setText("Update Data")
            Editname.setText(EmplyeeName)
            Editage.setText(EmplyeeAge)
            Editcode.setText(EmplyeeCode)
             //Editdesignation.
        } else {
            EditButton.setText("Save Data")
        }





        EditButton.setOnClickListener {
            val emplyeename = Editname.text.toString()
            val emplyeeage = Editage.text.toString()
            val emplyeecode = Editcode.text.toString()
            val emplyeedesignation = Editdesignation.selectedItem.toString()
            if (emplyeename.isNotEmpty() && emplyeeage.isNotEmpty() && emplyeecode.isNotEmpty() && emplyeedesignation.isNotEmpty()) {
                viewModel.addEmplyeeDetails(Emplyee(emplyeename,emplyeeage,emplyeecode,emplyeedesignation))
                Toast.makeText(this, "$emplyeename Added", Toast.LENGTH_LONG).show()
                startActivity(Intent(applicationContext, MainActivity::class.java))
            }else{
            if (emplyeeType.equals("Edit")) {
                if (emplyeename.isNotEmpty() && emplyeeage .isNotEmpty() && emplyeecode.isNotEmpty() && emplyeedesignation.isNotEmpty()) {
                    val updatedEmplyee = Emplyee(emplyeename, emplyeeage, emplyeecode, emplyeedesignation)
                    updatedEmplyee.id = EmolyeeID
                    viewModel.updateEmplyeeDetails(updatedEmplyee)
                    Toast.makeText(this, "Note Updated..", Toast.LENGTH_LONG).show()
                }

                }
                startActivity(Intent(applicationContext, MainActivity::class.java))
                this.finish()
                }
            }

            }
        }
