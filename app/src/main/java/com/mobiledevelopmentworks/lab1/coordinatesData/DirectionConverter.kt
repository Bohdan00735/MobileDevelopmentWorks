package com.mobiledevelopmentworks.lab1.coordinatesData

class DirectionConverter {
    private val mapOfDirections = mapOf("North" to Direction.NORTH, "South" to Direction.SOUTH,
    "West" to Direction.WEST, "East" to Direction.EAST)

    fun getDirection(dir:String): Direction? {
        return mapOfDirections[dir]
    }
}