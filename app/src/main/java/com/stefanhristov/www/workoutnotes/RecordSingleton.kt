package com.stefanhristov.www.workoutnotes

/**
 * Created by Stefan on 15-Mar-18.
 */
object RecordSingleton {
    var exercises = mutableListOf<Exercise>()
    var workouts = mutableListOf<Workout>()

    fun findExercise(n: String): Exercise{
        for (e in exercises){
            if (e.name.equals(n)){
                return e
            }
        }
        throw IllegalArgumentException("Not found!")
    }

    fun findWorkout(n: String): Workout{
        for (w in workouts){
            if (w.name.equals(n)){
                return w
            }
        }
        throw IllegalArgumentException("Not found!")
    }
}