package com.stefanhristov.www.workoutnotes

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.constraint.ConstraintLayout
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_create_new_workout.*

class CreateNewWorkout : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_new_workout)

        loadSpinners()
        loadSlider()
        loadSaveBtn()
    }

    private fun loadCategorySpinner(){
        categorySpinner.adapter = ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, resources.getStringArray(R.array.workout_category))
        categorySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                // categoryDropDown.
            }
        }
    }

    private fun loadSlider(){
        var numEx = 3

        numberExSB.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                numberExTV.text = resources.getString(R.string.number_of_exer) + p1.toString()
                numEx = p1
                //print(textValue.text)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                when (numEx) {
                    0 -> {
                        makeExerciseInvisible(R.id.exOneLayout)
                        makeExerciseInvisible(R.id.exTwoLayout)
                        makeExerciseInvisible(R.id.exThreeLayout)
                        makeExerciseInvisible(R.id.exFourLayout)
                        makeExerciseInvisible(R.id.exFiveLayout)
                    }
                    1 -> {
                        makeExerciseVisible(R.id.exOneLayout)
                        makeExerciseInvisible(R.id.exTwoLayout)
                        makeExerciseInvisible(R.id.exThreeLayout)
                        makeExerciseInvisible(R.id.exFourLayout)
                        makeExerciseInvisible(R.id.exFiveLayout)
                    }
                    2 -> {
                        makeExerciseVisible(R.id.exOneLayout)
                        makeExerciseVisible(R.id.exTwoLayout)
                        makeExerciseInvisible(R.id.exThreeLayout)
                        makeExerciseInvisible(R.id.exFourLayout)
                        makeExerciseInvisible(R.id.exFiveLayout)
                    }
                    3 -> {
                        makeExerciseVisible(R.id.exOneLayout)
                        makeExerciseVisible(R.id.exTwoLayout)
                        makeExerciseVisible(R.id.exThreeLayout)
                        makeExerciseInvisible(R.id.exFourLayout)
                        makeExerciseInvisible(R.id.exFiveLayout)
                    }
                    4 -> {
                        makeExerciseVisible(R.id.exOneLayout)
                        makeExerciseVisible(R.id.exTwoLayout)
                        makeExerciseVisible(R.id.exThreeLayout)
                        makeExerciseVisible(R.id.exFourLayout)
                        makeExerciseInvisible(R.id.exFiveLayout)
                    }
                    5 -> {
                        makeExerciseVisible(R.id.exOneLayout)
                        makeExerciseVisible(R.id.exTwoLayout)
                        makeExerciseVisible(R.id.exThreeLayout)
                        makeExerciseVisible(R.id.exFourLayout)
                        makeExerciseVisible(R.id.exFiveLayout)
                    }
                    else -> {
                        print("otherwise")
                    }
                }
            }

        })
    }

    private fun makeExerciseInvisible(id: Int){
        var layout = findViewById<ConstraintLayout>(id) as ConstraintLayout
        layout.visibility = View.INVISIBLE
    }

    private fun makeExerciseVisible(id: Int){
        var layout = findViewById<ConstraintLayout>(id) as ConstraintLayout
        layout.visibility = View.VISIBLE
    }

    private fun loadSaveBtn(){
        saveBtn.setOnClickListener {
            if (checkNameIsUnique(workoutName.text.toString())) {
                try {
                    var nmbexer = numberExSB.progress
                    val categ = categorySpinner.selectedItem.toString()
                    val myList = mutableListOf<String>()

                    when (nmbexer) {
                        0 -> {
                            // DO nothing
                        }
                        1 -> {
                            myList.add(exOneSpinner.selectedItem.toString())
                        }
                        2 -> {
                            myList.add(exOneSpinner.selectedItem.toString())
                            myList.add(exTwoSpinner.selectedItem.toString())
                        }
                        3 -> {
                            myList.add(exOneSpinner.selectedItem.toString())
                            myList.add(exTwoSpinner.selectedItem.toString())
                            myList.add(exThreeSpinner.selectedItem.toString())
                        }
                        4 -> {
                            myList.add(exOneSpinner.selectedItem.toString())
                            myList.add(exTwoSpinner.selectedItem.toString())
                            myList.add(exThreeSpinner.selectedItem.toString())
                            myList.add(exFourSpinner.selectedItem.toString())

                        }
                        5 -> {
                            myList.add(exOneSpinner.selectedItem.toString())
                            myList.add(exTwoSpinner.selectedItem.toString())
                            myList.add(exThreeSpinner.selectedItem.toString())
                            myList.add(exFourSpinner.selectedItem.toString())
                            myList.add(exFiveSpinner.selectedItem.toString())
                        }
                        else -> {
                            print("otherwise")
                        }
                    }

                    val newWorkout = Workout(workoutName.text.toString(), categ, nmbexer, myList)
                    RecordSingleton.workouts.add(newWorkout)
                    println("Workout Added!")
                    val dialog = AlertDialog.Builder(this).create()
                    dialog.setMessage("Workout Added!")
                    dialog.show()
                    Handler().postDelayed({dialog.dismiss()}, 1000)
                    Handler().postDelayed({finish()}, 1500)


                }
                catch (e: Exception){
                    val alertDialog = AlertDialog.Builder(this).create()
                    alertDialog.setMessage("Something with the parsing")
                    alertDialog.show()
                }
            } else {
                val alertDialog = AlertDialog.Builder(this).create()
                alertDialog.setMessage(resources.getString(R.string.exist_or_empty))
                alertDialog.show()
            }
        }
    }

    private fun checkNameIsUnique(name: String) : Boolean {
        if (name.length == 0){
            return false
        } else {
            for (w in RecordSingleton.workouts){
                if (name.equals(w.name)){
                    return false
                }
            }
            return true
        }
    }

    private fun loadSpinners(){
        val list = RecordSingleton.exercises.map { it.name }
        loadSpinner(exOneSpinner.id, list)
        loadSpinner(exTwoSpinner.id, list)
        loadSpinner(exThreeSpinner.id, list)
        loadSpinner(exFourSpinner.id, list)
        loadSpinner(exFiveSpinner.id, list)
    }

    private fun loadSpinner(id: Int, list: List<String>){
        loadCategorySpinner()
        val spinner = findViewById<Spinner>(id) as Spinner
        spinner.adapter = ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, list)
        categorySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                // categoryDropDown.
            }
        }
    }






}
