package com.stefanhristov.www.workoutnotes

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_display_workout.*

class DisplayWorkout : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_workout)

        val workoutName = intent.getStringExtra("workoutObject")
        val mworkout = RecordSingleton.findWorkout(workoutName)
        display_listView.adapter = DisplayCustomAdapter(this, mworkout)

        nameTextView.text = mworkout.name
        categoryTextView.text = mworkout.category


    }




}
