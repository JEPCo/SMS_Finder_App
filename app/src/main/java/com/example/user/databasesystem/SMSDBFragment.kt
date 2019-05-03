package com.example.user.databasesystem


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
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
        var obj = DBOpenHelper(activity!!.baseContext)
        var db = obj.readableDatabase
        var cur = db.rawQuery("select * from messages", null)
        if (cur.count >0) {
            var list = ArrayList<String>()
            var listTest = ArrayList<SMSItem>()
            cur.moveToFirst()
            while (!cur.isAfterLast) {
                list.add(cur.getString(0) + "\n" + cur.getString(1) + "\n" + cur.getString(2))
                listTest.addAll(obj!!.SMSList)
                cur.moveToNext()
            }

            var adp = ArrayAdapter(activity, R.layout.list_layout, list)
            v.lv.adapter = adp

            v.lv.setOnItemClickListener { adapterView, view, i, l ->

                DBOpenHelper.msg = list[i]
                var obj = PopUpFragment()
                obj.show(activity!!.supportFragmentManager, "Test")
            }

            v.lv.setOnItemLongClickListener { adapterView, view, i, l ->
                val builder = AlertDialog.Builder(activity!!)
                builder.setTitle("Delete it?")
                builder.setMessage("It will be deleted from DB too!")
                builder.setPositiveButton("YES") { dialog, which ->
                    obj!!.deleteSMS(listTest[i])
                    list.removeAt(i)
                    adp.notifyDataSetChanged()
                    Toast.makeText(activity, "Deleted!", Toast.LENGTH_SHORT).show()
                }

                builder.setNegativeButton("No") { dialog, which ->
                    Toast.makeText(activity, "You are not agree.", Toast.LENGTH_SHORT).show()
                }

                builder.setNeutralButton("Cancel") { _, _ ->
                    Toast.makeText(activity, "You cancelled the dialog.", Toast.LENGTH_SHORT).show()
                }

                val dialog: AlertDialog = builder.create()
                dialog.show()

                false
            }
        }
        return v
    }


}
