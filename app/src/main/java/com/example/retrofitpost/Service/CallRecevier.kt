package com.example.retrofitpost.Service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telecom.TelecomManager
import android.telephony.TelephonyManager
import android.view.Gravity
import android.widget.Toast

class CallRecevier:BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
      if (intent!!.getStringExtra(TelephonyManager.EXTRA_STATE)==
              TelephonyManager.EXTRA_STATE_OFFHOOK){
          ShowToast(context!!,"Phone Call Started....!")
      }else if (intent.getStringExtra(TelephonyManager.EXTRA_STATE)==
          TelephonyManager.EXTRA_STATE_IDLE){
          ShowToast(context!!,"Phone Call Ended....!")
      }else if (intent.getStringExtra(TelephonyManager.EXTRA_STATE)==
          TelephonyManager.EXTRA_STATE_RINGING){
          ShowToast(context!!,"INcomming Call....!")
      }
    }
    fun ShowToast(c:Context, msg:String){
        val toast=Toast.makeText(c,msg,Toast.LENGTH_LONG)
        toast.setGravity(Gravity.CENTER,0,0)
        toast.show()
    }
}