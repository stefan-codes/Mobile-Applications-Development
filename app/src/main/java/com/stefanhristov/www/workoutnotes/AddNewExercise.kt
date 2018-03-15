package com.stefanhristov.www.workoutnotes

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_add_new_exercise.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.*
import java.nio.channels.FileChannel
import java.nio.charset.Charset

class AddNewExercise : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_exercise)

        loadCheckBoxes()
        loadSaveBtn()
    }

    private fun loadCheckBoxes(){
        loadCheckBox(setsCB, setsET)
        loadCheckBox(repsCB, repsET)
        loadCheckBox(weightCB, weightsET)
        loadCheckBox(speedCB, speedET)
        loadCheckBox(timeCB, timeET)
    }

    private fun loadCheckBox(cb: CheckBox, et: EditText){
        cb.setOnClickListener{
            if (cb.isChecked) {
                et.visibility = View.VISIBLE
            } else {
                et.visibility = View.INVISIBLE
            }
        }
    }

    private fun loadSaveBtn(){
        saveBtn.setOnClickListener{
            if(checkNameIsUnique(nameET.text.toString())){
                try {
                    var reps = 0
                    var sets = 0
                    var weights = 0.0f
                    var speed = 0.0f
                    var timee = 0.0f

                    if(setsCB.isChecked){
                        sets = setsET.text.toString().toInt()
                    }
                    if(repsCB.isChecked){
                        reps = repsET.text.toString().toInt()
                    }
                    if(weightCB.isChecked){
                        weights = weightsET.text.toString().toFloat()
                    }
                    if(speedCB.isChecked){
                        speed = speedET.text.toString().toFloat()
                    }
                    if(timeCB.isChecked){
                        timee = timeET.text.toString().toFloat()
                    }

                    val newExercise = Exercise(
                            nameET.text.toString(),
                            setsCB.isChecked,
                            repsCB.isChecked,
                            weightCB.isChecked,
                            timeCB.isChecked,
                            speedCB.isChecked,
                            sets,
                            reps,
                            weights,
                            speed,
                            timee
                            )

                    RecordSingleton.exercises.add(newExercise)
                    createJsonData(RecordSingleton.exercises)

                    println("Exercise Added!")
                    val pDialog = AlertDialog.Builder(this).create()
                    pDialog.setMessage("Exercise Added!")
                    pDialog.show()
                    Handler().postDelayed({pDialog.dismiss()}, 1000)
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
                Handler().postDelayed({alertDialog.dismiss()}, 2000)
            }
        }
    }

    private fun checkNameIsUnique(name: String) : Boolean {
        if (name.length == 0){
            return false
        } else {
            for (w in RecordSingleton.exercises){
                if (name.equals(w.name)){
                    return false
                }
            }
            return true
        }
    }

    //Saving of data
    private fun createJsonData(mylist: List<Exercise>) {
        var json = JSONObject()
        json.put("exercises", addExercises(mylist))
        save(json.toString())
    }

    private fun save(json: String){
        println(json.toString())
        println("Saved biatch!")
        val output: Writer
        val file = createFile()
        output = BufferedWriter(FileWriter(file))
        output.write(json)
        output.close()
    }

    private fun createFile() : File{
        val fileName = "myRecords"
        val storageDir = baseContext.getFileStreamPath(fileName)
        if(!storageDir.exists()){
            storageDir.mkdir()
        }

        return File.createTempFile(
                fileName,
                ".json",
                storageDir
        )
    }

    private fun addExercises(listOfExercise: List<Exercise>): JSONArray {
        var exercisesJson = JSONArray()
        listOfExercise.forEach {
            exercisesJson.put(
                    JSONArray()
                            .put(it.name)
                            .put(it.trackSets)
                            .put(it.trackReps)
                            .put(it.trackWeight)
                            .put(it.trackSpeed)
                            .put(it.trackTime)
                            .put(it.sets)
                            .put(it.reps)
                            .put(it.weights)
                            .put(it.speed)
                            .put(it.timee)
            )
        }
        return exercisesJson
    }

}
