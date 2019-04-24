package com.example.user.databasesystem

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.ContactsContract
import android.telephony.SmsManager
import android.telephony.SmsMessage

class SMSSender() : BroadcastReceiver() {
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

        if (text.contains("*")) {
            var passw = text.substring(0, text.indexOf("*"))
            var name = text.substring(text.indexOf("*") + 1)


            var sp = p0.getSharedPreferences("my_settings", Context.MODE_PRIVATE)
            if (passw == sp.getString("pwd", "")) {
                var s: String = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " like '%" + name + "%'"
                var cur = p0.contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, s, null, null)

                if (cur.count > 0) {
                    var result: String = ""
                    cur.moveToFirst()
                    while (!cur.isAfterLast) {
                        result += cur.getString(cur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                        result += " - " + cur.getString(cur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                        result += "\n"

                        cur.moveToNext()
                    }
                    var manager = SmsManager.getDefault()
                    manager.sendTextMessage(phone, null, result, null, null)

                    var sms = DBOpenHelper(p0)
                    var db = sms.writableDatabase
                    db.execSQL("insert into messages values(?,?)", arrayOf(phone, name))
                }
            }
        }
    }
}
