package com.mobiledevelopmentworks.lab1.timeData

import java.sql.Time
import java.util.*

@Suppress("EXPERIMENTAL_UNSIGNED_LITERALS")
class TimeBM (){
    lateinit var startTime:Time
    var hours = 0u
    var minutes = 0u
    var seconds = 0u

    constructor(_hours:UInt,_minutes:UInt, _seconds:UInt) : this() {
        checkTime(_hours,_minutes,_seconds)
        startTime = Time(hours.toInt(), minutes.toInt(), seconds.toInt())
        hours = _hours
        minutes = _minutes
        seconds = _seconds
    }

    constructor(_date : String):this(){
        val splitedDate = _date.split(":")
        hours = splitedDate[0].toUInt()
        minutes = splitedDate[1].toUInt()
        seconds = splitedDate[2].toUInt()

    }

    @ExperimentalUnsignedTypes
    private fun checkTime(_hours: UInt, _minutes: UInt, _seconds: UInt) {
        if (_hours > 23u || _minutes > 59u || _seconds > 59u){
            throw Error("Error format of a time")
        }
    }

    constructor(_date:Date):this(){
        val calendar = Calendar.getInstance()
        calendar.time = _date
        hours = calendar.get(Calendar.HOUR_OF_DAY).toUInt()
        minutes = calendar.get(Calendar.MINUTE).toUInt()
        seconds = calendar.get(Calendar.SECOND).toUInt()
    }

    fun getTimeInLine():String{
        return convertToTwelveFormat()
    }

    @ExperimentalUnsignedTypes
    private fun convertToTwelveFormat(): String {
        if (hours > 12u){
            return addMinutesAndSeconds((hours - 12u).toString() + ":") + ":PM"
        }
        return addMinutesAndSeconds("$hours:")+ ":AM"
    }

    private fun addMinutesAndSeconds(line: String): String {
        return "$line$minutes:$seconds"
    }

    fun getSumFromStart(timeBM: TimeBM): TimeBM {
        return TimeBM(normaliseSumOfTime
        (hours + timeBM.hours,minutes + timeBM.minutes,seconds + timeBM.seconds))
    }

    fun getDifferenceWithStart(timeBM: TimeBM): TimeBM {
        return TimeBM(normaliseDiffOfTime
        (hours - timeBM.hours,minutes - timeBM.minutes,seconds - timeBM.seconds));
    }

    fun getSumOfTimes(firstTimeBM: TimeBM, secondTimeBM: TimeBM): TimeBM {
        return TimeBM(normaliseSumOfTime
        (firstTimeBM.hours + secondTimeBM.hours,
                firstTimeBM.minutes + secondTimeBM.minutes,
                firstTimeBM.seconds + secondTimeBM.seconds))
    }

    fun getDiffOfTimes(firstTimeBM: TimeBM, secondTimeBM: TimeBM): TimeBM {
        return TimeBM(normaliseDiffOfTime
        (firstTimeBM.hours - secondTimeBM.hours,
                firstTimeBM.minutes - secondTimeBM.minutes,
                firstTimeBM.seconds - secondTimeBM.seconds))
    }

    private fun normaliseDiffOfTime(thisHours: UInt, thisMinutes: UInt, thisSeconds: UInt): String {
        var returnSeconds = thisSeconds
        var returnMinutes = thisMinutes
        var returnHours = thisHours
        if(thisSeconds <0u){
            returnSeconds += 60u
            returnMinutes++
        }
        if (returnMinutes < 0u){
            returnMinutes += 60u
            returnHours++
        }

        if (returnHours < 0u){
            returnHours+= 24u
        }
        return "$returnHours:$returnMinutes:$returnSeconds"
    }

    private fun normaliseSumOfTime(thisHours: UInt, thisMinutes: UInt, thisSeconds: UInt): String {
        var returnSeconds = thisSeconds
        var returnMinutes = thisMinutes
        var returnHours = thisHours
        if(thisSeconds > 59u){
            returnSeconds -= 60u
            returnMinutes++
        }
        if (returnMinutes > 59u){
            returnMinutes -= 60u
            returnHours++
        }

        if (returnHours > 23u){
            returnHours-= 24u
        }
        return "$returnHours:$returnMinutes:$returnSeconds"
    }


}

