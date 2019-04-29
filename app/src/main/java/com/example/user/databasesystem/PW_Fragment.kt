package com.example.user.databasesystem


import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_pw.view.*
import java.util.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class PW_Fragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var v = inflater.inflate(R.layout.fragment_pw, container, false)
        v.pwd_save.setOnClickListener {
            if (v.pwd_password.text.toString().length < 4)
                Toast.makeText(activity, "Minimum is 4 char", Toast.LENGTH_LONG).show()
            else {
                if (v.pwd_password.text.toString() != v.pwd_confirm.text.toString())
                    Toast.makeText(activity, "PW is not match", Toast.LENGTH_LONG).show()
                else {
                    var sp = activity!!.getSharedPreferences("my_settings", Context.MODE_PRIVATE)
                    var edi = sp.edit()
                    edi.putString("pwd", v.pwd_password.text.toString())
                    edi.commit()
                    Toast.makeText(activity, "Saved!!", Toast.LENGTH_LONG).show()
                }
            }
        }

        v.rdo_ar.setOnClickListener {
            var loc = Locale("ar")
            Locale.setDefault(loc)

            var conf = Configuration()
            conf.locale = loc

            activity.resources.updateConfiguration(conf, activity.resources.displayMetrics)
            DBOpenHelper.test = 1
            var i = Intent(activity, MainActivity::class.java)
            startActivity(i)
        }

        v.rdo_en.setOnClickListener {
            var loc = Locale("en")
            Locale.setDefault(loc)

            var conf = Configuration()
            conf.locale = loc

            activity.resources.updateConfiguration(conf, activity.resources.displayMetrics)
            DBOpenHelper.test = 1
            var i = Intent(activity, MainActivity::class.java)
            startActivity(i)
        }
        return v
    }


}
