package com.stefanhristov.www.workoutnotes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.row_display_workout.view.*

/**
 * Created by Stefan on 15-Mar-18.
 */
class DisplayCustomAdapter(context: Context, workout: Workout) : BaseAdapter(){

    private val mContext: Context
    private val mWorkout = workout
    private val exercises = mutableListOf<Exercise>()

    init {
        mContext = context
        for(s in mWorkout.exercises){
            exercises.add(RecordSingleton.findExercise(s))
        }
    }

    override fun getCount(): Int {
        return mWorkout.numberOfExercises
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getItem(p0: Int): Any {
        return "positive"
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val layoutInflater = LayoutInflater.from(mContext)
        val rowDisplay = layoutInflater.inflate(R.layout.row_display_workout, p2, false)
        rowDisplay.exerciseNumberTV.text = "Exercise "+(p0+1)
        rowDisplay.exerciseNameTV.text = mWorkout.exercises[p0]

        val exercise = RecordSingleton.findExercise(mWorkout.exercises[p0])

        if (exercise.trackSets){
            rowDisplay.setsTV.visibility = View.VISIBLE
            rowDisplay.setsTV.text = exercise.sets.toString() + " Sets"
        }

        if (exercise.trackReps){
            rowDisplay.repsTV.visibility = View.VISIBLE
            rowDisplay.repsTV.text = exercise.reps.toString() + " Reps"
        }

        if (exercise.trackWeight){
            rowDisplay.weightsTV.visibility = View.VISIBLE
            rowDisplay.weightsTV.text = exercise.weights.toString() + "kg Weights"
        }

        if (exercise.trackSpeed){
            rowDisplay.speedTV.visibility = View.VISIBLE
            rowDisplay.speedTV.text = exercise.speed.toString() + "km/h Speed"
        }

        if (exercise.trackTime){
            rowDisplay.timeTV.visibility = View.VISIBLE
            rowDisplay.timeTV.text = exercise.timee.toString() + "min Time"
        }



        return rowDisplay
    }
}