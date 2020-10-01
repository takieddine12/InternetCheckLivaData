@file:Suppress("DEPRECATION")

package com.manager.learningkotlin

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.*
import android.os.IBinder
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.util.concurrent.BrokenBarrierException

class InternetChecker(var context: Context) : LiveData<Boolean>(){
    private  var liveData: MutableLiveData<Boolean>? = null

    init {
        liveData = MutableLiveData()
    }


    override fun onActive() {
        super.onActive()
        val intentFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        context.registerReceiver(receiver,intentFilter)
    }

    override fun onInactive() {
        super.onInactive()
        context.unregisterReceiver(receiver)
    }


    var receiver = object : BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
           val connectivityManager  = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connectivityManager.activeNetworkInfo
            val isConnected = networkInfo != null && networkInfo.isConnectedOrConnecting
            if(isConnected){
                when(networkInfo?.type){
                        ConnectivityManager.TYPE_MOBILE -> postValue(true)
                        ConnectivityManager.TYPE_ETHERNET -> postValue(true)
                        ConnectivityManager.TYPE_WIFI -> postValue(true)
                       else -> postValue(false)

                    }

            } else {
                postValue(false )
            }
        }
    }



}