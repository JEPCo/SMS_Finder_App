package com.example.user.databasesystem

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_call_back.*
import kotlinx.android.synthetic.main.fragment_time.*

class CallBackAct : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_call_back)

        btn_time.setOnClickListener {
            var t = TimeFragment()
            t.show(supportFragmentManager,"Time")
        }
    }

    fun getTime(h: Int, m: Int)
    {
        tvcb.text = h.toString() + ":" + m.toString()
    }
}
