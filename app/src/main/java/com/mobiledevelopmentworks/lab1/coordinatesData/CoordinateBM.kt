package com.mobiledevelopmentworks.lab1.coordinatesData

import java.lang.Error

class CoordinateBM {
    lateinit var direction:Direction
    var degree = 0
    var minutes = 0u
    var seconds = 0u

    constructor()

    constructor(direction: Direction, degree: Int, minutes: UInt, seconds: UInt) {
        if(direction == Direction.EAST || direction == Direction.WEST){
            checkLongitudeCoordinates(degree)
        }else checkLatitudeCoordinates(degree)
        checkMinutesAndSeconds(minutes, seconds)
        this.direction = direction
        this.degree = degree
        this.minutes = minutes
        this.seconds = seconds
    }



    private fun checkLatitudeCoordinates(givenLatitudeDegree : Int) {

        if (givenLatitudeDegree < -90 || givenLatitudeDegree > 90){
            throw Error("error degree for latitude")
        }
    }

    private fun checkMinutesAndSeconds(minutes: UInt, seconds: UInt) {
        if (minutes > 59u){
            throw Error("error value of minutes")
        }

        if (seconds > 59u){
            throw Error("error value of minutes")
        }
    }

    private fun checkLongitudeCoordinates(givenLongitudeDegree: Int) {
        if (givenLongitudeDegree < -180 || givenLongitudeDegree > 180){
            throw Error("error degree for latitude")
        }
    }

    fun getCoordinate():String{
        return "\n$degree\u00B0$minutes\'$seconds\" "+ direction.toString().first()+"\n"
    }

    fun getDecimalCoordinate():String{
        val value = if (degree < 0){
            degree - minutes.toDouble()/60 - seconds.toDouble()/3600
        }else degree + minutes.toDouble()/60 + seconds.toDouble()/3600
        

        return "\n"+value + "\u00B0 " + direction.toString().first()+"\n"
    }

    fun getMediumCoordinate(inputCoordinateBM: CoordinateBM): CoordinateBM? {
        if (direction != inputCoordinateBM.direction) return null
        return CoordinateBM(direction, (degree + inputCoordinateBM.degree)/2,
                (minutes+inputCoordinateBM.minutes)/2u,
                (seconds + inputCoordinateBM.seconds)/2u)
    }

     fun calculateMediumCoordinateFromInput
             (firstCoordinateBM: CoordinateBM, secondCoordinateBM: CoordinateBM): CoordinateBM? {
         return firstCoordinateBM.getMediumCoordinate(secondCoordinateBM)
     }
}
