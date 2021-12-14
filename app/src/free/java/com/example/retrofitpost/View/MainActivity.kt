package com.example.retrofitpost.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.emplyeedata.EmplyeeRvAdapter
import com.example.emplyeedata.EmplyeeclickDeleteinterface
import com.example.emplyeedata.Emplyeeclickinterface
import com.example.retrofitpost.Model.Retrofit.RoomDb.Emplyee
import com.example.retrofitpost.Model.Retrofit.RoomDb.EmplyeeDatabase
import com.example.retrofitpost.R
import com.example.retrofitpost.Repository.Repositiory

import com.example.retrofitpost.ViewModel.MainViewModelFactory
import com.example.retrofitpost.ViewModel.MainViewmodel

import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), Emplyeeclickinterface, EmplyeeclickDeleteinterface {

    private lateinit var  viewmodel:MainViewmodel
    lateinit var textView: TextView
    lateinit var RvEmplyee: RecyclerView
    lateinit var Addbtn: FloatingActionButton
    lateinit var Drawer:ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RvEmplyee=findViewById(R.id.RecyclerEmplyee)
        Addbtn=findViewById(R.id.addbtn)
        textView =findViewById(R.id.text)
        Drawer = findViewById(R.id.nextactivity)


        Drawer.setOnClickListener(View.OnClickListener {
            Toast.makeText(this,"this is free version",Toast.LENGTH_SHORT).show()
//            val intent= Intent(this,com.example.retrofitpost.View.Drawer::class.java)
//            startActivity(intent)
        })


        RvEmplyee.layoutManager= LinearLayoutManager(this)
        val emplyeeRvAdapter = EmplyeeRvAdapter(this,this,this)


        CreateRepositry()



        Addbtn.setOnClickListener {
            val intent = Intent(this, updateActivity::class.java)
            startActivity(intent)
        }



        viewmodel.getpost()
      //  val mypost =Post(3,4,"jegan","what is you name ","Android Development")
      //  viewmodel.pushpost(mypost)
        viewmodel.myreponse.observe(this, Observer { response->
            if (response.isSuccessful){
                textView.text = response.body()?.title.toString()
                Toast.makeText(this,"Successful!!!",Toast.LENGTH_SHORT).show()

            }else{
                textView.text =response.code().toString()
            }
        })

        viewmodel.allEmplyeesDetails().observe(this,{
            list->list?.let {
            RvEmplyee.adapter=emplyeeRvAdapter
         emplyeeRvAdapter.updateEmplyee(list)
        }
        })
    }

    private fun CreateRepositry() {
        val repository=Repositiory(EmplyeeDatabase(this))
        val viewModelFactory=MainViewModelFactory(repository )
        viewmodel = ViewModelProvider(this,viewModelFactory).get(MainViewmodel::class.java)

    }





    override fun onemplyeeclick(emplyee: Emplyee) {
        val intent = Intent(this, updateActivity::class.java)
        intent.putExtra("emplyeeType","Edit")
        intent.putExtra("emplyeename",emplyee.emplyeeName)
        intent.putExtra("emplyeeage",emplyee.emplyeeAge)
        intent.putExtra("emplyeecode",emplyee.emplyeeCode)
        intent.putExtra("emplyeedesignation",emplyee.emplyeeDesignation)
        intent.putExtra("emplyeeid",emplyee.id)
        startActivity(intent)
        this.finish()

    }

    override fun onDeleteiconclick(emplyee: Emplyee) {
        viewmodel.deleteEmplyee(emplyee)
        Toast.makeText(this,"${emplyee.emplyeeName} Deleted",Toast.LENGTH_SHORT).show()
    }
}



