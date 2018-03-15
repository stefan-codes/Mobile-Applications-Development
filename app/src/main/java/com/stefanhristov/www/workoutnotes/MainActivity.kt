package com.stefanhristov.www.workoutnotes

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.FileInputStream
import java.nio.channels.FileChannel
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        try {
            loadJsonData()
            println("Loaded something!")
        }
        catch (e: Exception){
            println("well couldnt load")
        }
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                main_listView.visibility = View.INVISIBLE
                message.visibility = View.VISIBLE
                message.setText(R.string.title_home_message)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_workouts -> {
                message.visibility = View.INVISIBLE
                main_listView.adapter = CustomAdapter(this)
                main_listView.visibility = View.VISIBLE
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_new_workout -> {
                val intent =  Intent(this, CreateNewWorkout::class.java)
                startActivity(intent)
            }
            R.id.navigation_new_exercise -> {
                val intent =  Intent(this, AddNewExercise::class.java)
                startActivity(intent)
            }

            //R.id.navigation_notifications -> {
            //    message.setText("gg")
            //    for (e in RecordSingleton.exercises){
            //        println(e.name)
            //    }
            //    return@OnNavigationItemSelectedListener true
            //}
        }
        false
    }

    //Loading of data
    private fun loadJsonData(){
        parseJson()
    }

    private fun readFile(): String{
        val file = baseContext.getFileStreamPath("myRecords/myRecords.json")

        val stream = FileInputStream(file)

        var json = ""
        stream.use { stream ->
            val fileChannel = stream.channel
            val mappedByteBuffer = fileChannel.map(
                    FileChannel.MapMode.READ_ONLY,
                    0,
                    fileChannel.size()
            )
            json = Charset.defaultCharset().decode(mappedByteBuffer).toString()
        }
        return json
    }

    private fun parseJson(){
        var jsonObject = JSONObject(readFile())

        RecordSingleton.exercises = getExercises(jsonObject.getJSONArray("exercises"))

    }

    private fun getExercises(jsonArray: JSONArray): MutableList<Exercise> {

        var exers = mutableListOf<Exercise>()
        var x = 0
        while (x < jsonArray.length()){
            exers.add(Exercise(
                    jsonArray.getJSONArray(x).getString(0),
                    jsonArray.getJSONArray(x).getBoolean(1),
                    jsonArray.getJSONArray(x).getBoolean(2),
                    jsonArray.getJSONArray(x).getBoolean(3),
                    jsonArray.getJSONArray(x).getBoolean(4),
                    jsonArray.getJSONArray(x).getBoolean(5),
                    jsonArray.getJSONArray(x).getInt(6),
                    jsonArray.getJSONArray(x).getInt(7),
                    jsonArray.getJSONArray(x).getString(8).toFloat(),
                    jsonArray.getJSONArray(x).getString(9).toFloat(),
                    jsonArray.getJSONArray(x).getString(10).toFloat()
            ))
            x++
        }
        return exers
    }

}
