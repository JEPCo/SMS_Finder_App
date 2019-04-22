package com.example.user.databasesystem

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (Build.VERSION.SDK_INT>22)
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECEIVE_SMS, Manifest.permission.SEND_SMS, Manifest.permission.READ_CONTACTS), 95)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode==95)
        {
            if (grantResults[0]==PackageManager.PERMISSION_GRANTED && grantResults[1]==PackageManager.PERMISSION_GRANTED && grantResults[2]==PackageManager.PERMISSION_GRANTED)
                Toast.makeText(this, "Thank you", Toast.LENGTH_LONG).show()
            else
                Toast.makeText(this, "Sorry you can't use this app", Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.item_pass)
        {
            var tr = supportFragmentManager.beginTransaction()
            var obj = PW_Fragment()
            tr.replace(R.id.main_container, obj)
            tr.commit()
        }

        if (item?.itemId == R.id.item_report)
        {
            var tr = supportFragmentManager.beginTransaction()
            var obj = SMSDBFragment()
            tr.replace(R.id.main_container, obj)
            tr.commit()
        }

        return super.onOptionsItemSelected(item)
    }
}
