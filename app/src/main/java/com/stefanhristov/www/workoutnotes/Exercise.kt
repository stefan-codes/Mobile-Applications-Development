package com.stefanhristov.www.workoutnotes
import com.google.gson.annotations.SerializedName


/**
 * Created by Stefan on 14-Mar-18.
 */
class Exercise constructor(n: String,  tracksets: Boolean, trackreps: Boolean, trackweights: Boolean, tracktime: Boolean, trackspeed: Boolean, sets: Int, reps: Int, weights: Float, speed: Float, timee: Float){
    @SerializedName("name") val name : String = n
    @SerializedName("trackTime") val trackTime : Boolean = tracktime
    @SerializedName("trackReps") val trackReps : Boolean = trackreps
    @SerializedName("trackSets") val trackSets : Boolean = tracksets
    @SerializedName("trackWeight") val trackWeight : Boolean = trackweights
    @SerializedName("trackSpeed") val trackSpeed : Boolean = trackspeed
    @SerializedName("sets") var sets : Int = sets
    @SerializedName("reps") var reps : Int = reps
    @SerializedName("weights") var weights : Float = weights
    @SerializedName("speed") var speed : Float = speed
    @SerializedName("timee") var timee : Float = timee
}