package com.example.retrofitpost.View.Fragement

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.mynewmodule.MainModule
import com.example.retrofitpost.R
import com.example.retrofitpost.Service.AlaramRicever

import com.google.android.material.timepicker.MaterialTimePicker
import java.util.*


class AlaramFragment : Fragment() {

    lateinit var timePicker: MaterialTimePicker
    lateinit var calender: Calendar
    lateinit var alaramManager: AlarmManager
    lateinit var pendingIntent: PendingIntent
    lateinit var SHOWTIME: TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_alaram, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        lateinit var SeclectBTN: Button
        lateinit var SetBTN: Button
        lateinit var CanceltBTN: Button
        lateinit var aarBTN: Button
        SeclectBTN = view.findViewById(R.id.buttonSelectAlarm)
        SetBTN = view.findViewById(R.id.buttonSetAlarm)
        CanceltBTN = view.findViewById(R.id.buttonCancelAlarm)
        SHOWTIME = view.findViewById(R.id.TIME)
        aarBTN = view.findViewById(R.id.aar)

        CreateNOtification()

        SeclectBTN.setOnClickListener(View.OnClickListener {
            SelectTime()
        })
        SetBTN.setOnClickListener(View.OnClickListener {
            SetAlaram()

        })
        CanceltBTN.setOnClickListener(View.OnClickListener {
            CancelAlaram()
        })
     aarBTN.setOnClickListener(View.OnClickListener {
         if (isServiceRunning()) {
             Toast.makeText(activity, "Running ", Toast.LENGTH_SHORT).show()
         }
         else{
             Toast.makeText(activity, "not running", Toast.LENGTH_SHORT).show()
         }
     })

    }


    private fun CancelAlaram() {
        alaramManager = activity?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(activity, AlaramRicever::class.java)
        pendingIntent = PendingIntent.getBroadcast(activity, 0, intent, 0)
        alaramManager.cancel(pendingIntent)
        Toast.makeText(activity, "ALARAM CANCEL!!", Toast.LENGTH_SHORT).show()
    }

    private fun SetAlaram() {
        alaramManager = activity?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(activity, AlaramRicever::class.java)
        pendingIntent = PendingIntent.getBroadcast(activity, 0, intent, 0)
        alaramManager.setRepeating(
            AlarmManager.RTC_WAKEUP, calender.timeInMillis,
            AlarmManager.INTERVAL_DAY, pendingIntent
        )
        Toast.makeText(activity, "ALARAM SUCCESFULLY SET!!", Toast.LENGTH_SHORT).show()
    }


    private fun SelectTime() {
        timePicker = MaterialTimePicker.Builder()
            .setHour(12)
            .setMinute(0)
            .setTitleText("SELECT ALARAM")
            .build()
        timePicker.show(activity?.supportFragmentManager!!, "FoxAndroid")
        timePicker.addOnPositiveButtonClickListener {
            if (timePicker.hour >= 12) {
                SHOWTIME.text = String.format("%2d", timePicker.hour - 12) + ":" + String.format(
                    "%2d",
                    timePicker.minute
                ) + "PM"
            } else {
                SHOWTIME.text = String.format("%2d", timePicker.hour) + ":" + String.format(
                    "%2d",
                    timePicker.minute
                ) + "AM"

            }
            calender = Calendar.getInstance()
            calender[Calendar.HOUR_OF_DAY] = timePicker.hour
            calender[Calendar.MINUTE] = timePicker.minute
            calender[Calendar.SECOND] = 0
            calender[Calendar.MILLISECOND] = 0
        }
    }

    private fun CreateNOtification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name: CharSequence = "Android Reminder"
            val description = "Alaram Manager"
            val impartance = NotificationManager.IMPORTANCE_HIGH
            val Channel = NotificationChannel("FoxAndroid", name, impartance)
            Channel.description = description
            val notificationManager = activity?.getSystemService(NotificationManager::class.java)
            notificationManager?.createNotificationChannel(Channel)
        }
    }


}