package com.example.user.databasesystem

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.util.*

class DBOpenHelper(context: Context) : SQLiteOpenHelper(context, "sms.db", null, 1) {

    val SMSList: List<SMSItem>
        get() {
            val smsItems = ArrayList<SMSItem>()
            val selectQuery = "SELECT  * FROM messages"
            val db = this.writableDatabase
            val cursor = db.rawQuery(selectQuery, null)
            if (cursor.moveToFirst()) {
                do {
                    val smsitem = SMSItem()
                    smsitem.smsId = cursor.getInt(0)
                    smsitem.smsPhone = cursor.getString(1)
                    smsitem.smsmsg = cursor.getString(2)
                    smsItems.add(smsitem)
                } while (cursor.moveToNext())
            }
            db.close()
            return smsItems

        }

    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL("create table messages(id integer PRIMARY KEY AUTOINCREMENT, phone text, msg text)")

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }

    companion object {
        var msg: String =""
        var test: Int = 0
    }

    fun deleteSMS(smsItem: SMSItem) {
        val db = this.writableDatabase
        db.delete("messages", "id = ?",
                arrayOf(smsItem.smsId.toString()))
        db.close()
    }
}