package com.stefanhristov.www.workoutnotes

import com.google.gson.annotations.SerializedName

/**
 * Created by Stefan on 15-Mar-18.
 */
class Workout constructor(name: String, category: String, numberExercise: Int, exercises: List<String>){
    @SerializedName("name") val name = name
    @SerializedName("category") val category = category
    @SerializedName("numberOfExercises") val numberOfExercises = numberExercise
    @SerializedName("exercises") val exercises = exercises
}