package com.example.retrofitpost.Service


import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.retrofitpost.R

import com.example.retrofitpost.View.Notificationclick
class AlaramRicever:BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
         val i = Intent(context, Notificationclick::class.java)
         intent!!.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

        val pendingIntent=PendingIntent.getActivity(context,0,i,0)
        val builder =NotificationCompat.Builder(context!!,"FoxAndroid")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle("Alaram Manager")
            .setContentText("hey this is jegan")
            .setAutoCancel(true)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
        val NotificationManager =NotificationManagerCompat.from(context)
        NotificationManager.notify(123,builder.build())

    }

}