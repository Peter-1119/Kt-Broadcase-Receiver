package com.example.kt_broadcast_receiver

import android.app.Service
import android.content.Intent
import android.os.Bundle
import android.os.IBinder


class MyService : Service() {

    var flag = false

    private var h = 0
    private  var m:Int = 0
    private  var s:Int = 0
    fun MyService() {}

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
        throw UnsupportedOperationException("Not yet implemented");
    }
    override fun onStartCommand(intent: Intent, flags: Int, startID: Int): Int {
        flag = intent.getBooleanExtra("flag", false)
        Thread {
            while (flag) {
                try {
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                s++
                if (s >= 60) {
                    s = 0
                    m++
                    if (m >= 60) {
                        m = 0
                        h++
                    }
                }
                val intent = Intent("MyMessage")
                val bundle = Bundle()
                bundle.putInt("H", h)
                bundle.putInt("M", m)
                bundle.putInt("S", s)
                intent.putExtras(bundle)
                sendBroadcast(intent)
            }
        }.start()
        return START_STICKY
    }
}