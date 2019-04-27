package com.example.user.databasesystem


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_smsdb.view.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class SMSDBFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var v =  inflater.inflate(R.layout.fragment_smsdb, container, false)
        var obj = DBOpenHelper(context)
        var db = obj.readableDatabase
        var cur = db.rawQuery("select * from messages", null)
        if (cur.count >0)
        {
            var list = ArrayList<String>()
            cur.moveToFirst()
            while (!cur.isAfterLast)
            {
                list.add(cur.getString(0) + "\n" + cur.getString(1))
                cur.moveToNext()
            }

            var adp = ArrayAdapter(activity, R.layout.list_layout, list)
            v.lv.adapter = adp
            
            v.lv.setOnItemClickListener { adapterView, view, i, l ->

                DBOpenHelper.msg = list[i]
                var obj = PopUpFragment()
                obj.show(activity.supportFragmentManager, "Test")

            }
        }




        return v
    }


}
