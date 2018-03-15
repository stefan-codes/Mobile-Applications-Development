package com.stefanhristov.www.workoutnotes

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.row_main.view.*

/**
 * Created by Stefan on 15-Mar-18.
 */
class CustomAdapter(context: Context) : BaseAdapter(){

    private val mContext: Context
    private val mworkouts = mutableListOf<String>()

    init {
        mContext = context
        for (w in RecordSingleton.workouts){
            mworkouts.add(w.name)
        }
    }

    override fun getCount(): Int {
        return RecordSingleton.workouts.count()
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getItem(p0: Int): Any {
        return "Test"
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val layoutInflater = LayoutInflater.from(mContext)
        val rowMain = layoutInflater.inflate(R.layout.row_main, p2, false)
        rowMain.nameTV.text = RecordSingleton.workouts.get(p0).name
        rowMain.categoryTV.text = RecordSingleton.workouts.get(p0).category

        rowMain.setOnClickListener {
            println(RecordSingleton.workouts.get(p0).name)
            val intent =  Intent(rowMain.context, DisplayWorkout::class.java)
            intent.putExtra("workoutObject", rowMain.nameTV.text.toString())
            println("wtf")
            rowMain.context.startActivity(intent)
        }
        return rowMain
    }
}