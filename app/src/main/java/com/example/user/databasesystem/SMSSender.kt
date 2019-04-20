package com.example.user.databasesystem

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.SmsMessage

class SMSSender : BroadcastReceiver() {
    override fun onReceive(p0: Context, p1: Intent) {
        var pdus = p1.extras.get("pdus") as Array<Any>
        var text: String = ""
        var phone: String = ""

        for (x in 0..pdus.size - 1)
        {
            var msg = SmsMessage.createFromPdu(pdus[x] as ByteArray)
            phone = msg.originatingAddress
            text = msg.displayMessageBody
        }
    }
}
