package com.manager.learningkotlin

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.NotificationCompat
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var callback : ((ImageView ) -> Unit)? = null
    private var isValidImgCallback : ((Boolean) -> Unit)? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


       InternetChecker(this).observe(this, Observer { isConnected ->
           if(isConnected){
               text.text = "There is internet"
           } else
               text.text = "There is no internet "

       })
    }


}
