 package com.mobiledevelopmentworks.lab1

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import java.lang.Math.*
import java.util.stream.Collectors
import kotlin.random.Random

@Suppress("TYPE_INFERENCE_ONLY_INPUT_TYPES_WARNING")
class PlaygroundTasks {
    // Частина 1

// Дано рядок у форматі "Student1 - Group1; Student2 - Group2; ..."

    var studentsStr = "Дмитренко Олександр - ІП-84; Матвійчук Андрій - ІВ-83; Лесик Сергій - ІО-82; Ткаченко Ярослав - ІВ-83; Аверкова Анастасія - ІО-83; Соловйов Даніїл - ІО-83; Рахуба Вероніка - ІО-81; Кочерук Давид - ІВ-83; Лихацька Юлія - ІВ-82; Головенець Руслан - ІВ-83; Ющенко Андрій - ІО-82; Мінченко Володимир - ІП-83; Мартинюк Назар - ІО-82; Базова Лідія - ІВ-81; Снігурець Олег - ІВ-81; Роман Олександр - ІО-82; Дудка Максим - ІО-81; Кулініч Віталій - ІВ-81; Жуков Михайло - ІП-83; Грабко Михайло - ІВ-81; Іванов Володимир - ІО-81; Востриков Нікіта - ІО-82; Бондаренко Максим - ІВ-83; Скрипченко Володимир - ІВ-82; Кобук Назар - ІО-81; Дровнін Павло - ІВ-83; Тарасенко Юлія - ІО-82; Дрозд Світлана - ІВ-81; Фещенко Кирил - ІО-82; Крамар Віктор - ІО-83; Іванов Дмитро - ІВ-82"

// Завдання 1
// Заповніть словник, де:
// - ключ – назва групи
// - значення – відсортований масив студентів, які відносяться до відповідної групи

    var studentsGroups = HashMap<String, ArrayList<String>>()

// Ваш код починається тут
    fun task1(){
        val splitedStr = studentsStr.split(";")
        for (i in splitedStr){
            val splitedPair = i.split(" - ")
            if (splitedPair[1] in studentsGroups.keys){
                studentsGroups[splitedPair[1]]!!.add(splitedPair[0])
                continue
            }
            studentsGroups[splitedPair[1]] = arrayListOf(splitedPair[0])
        }
      sortValuesLists()
    }

    private fun sortValuesLists() {
        for (i in studentsGroups.keys){
            val students = studentsGroups[i]
            students!!.sortBy { it.toCharArray().first().toInt()}
            studentsGroups[i] = students

        }
    }
// Ваш код закінчується тут


// Дано масив з максимально можливими оцінками

    val points = arrayOf(12, 12, 12, 12, 12, 12, 12, 16)

// Завдання 2
// Заповніть словник, де:
// - ключ – назва групи
// - значення – словник, де:
//   - ключ – студент, який відносяться до відповідної групи
//   - значення – масив з оцінками студента (заповніть масив випадковими значеннями, використовуючи функцію `randomValue(maxValue: Int) -> Int`)
    fun randomValue(maxValue: Int) : Int {
    return when(Random.nextInt(6)) {
        1-> (kotlin.math.ceil(maxValue.toDouble()) * 0.7).toInt()
        2 -> (kotlin.math.ceil(maxValue.toDouble()) * 0.9).toInt()
        3, 4, 5-> maxValue
        else->{
            0
        }
    }
    }
    val studentPoints = HashMap<String,HashMap<String,ArrayList<Int>>>()

// Ваш код починається тут
    fun task2(){
        for (i in studentsGroups){
            val groupMap = HashMap<String,ArrayList<Int>>()
            for (j in i.value){
                groupMap[j] = generateMarksArray(points.size)
            }
            studentPoints[i.key] = groupMap
        }

    }

    private fun generateMarksArray(num: Int): java.util.ArrayList<Int> {
        val list = ArrayList<Int>()
        for (i in 0 until  num){
            list.add(randomValue(points[i]))
        }
        return list
    }

// Ваш код закінчується тут

// Завдання 3
// Заповніть словник, де:
// - ключ – назва групи
// - значення – словник, де:
//   - ключ – студент, який відносяться до відповідної групи
//   - значення – сума оцінок студента

    var sumPoints = HashMap<String,HashMap<String, Int>>()

// Ваш код починається тут

    fun task3(){
        for(group in studentPoints){
            val studentsMap = HashMap<String, Int>()
            for (student in group.value){
                studentsMap[student.key] = calculateSum(student.value)
            }
            sumPoints[group.key] = studentsMap
        }
    }

    private fun calculateSum(value: java.util.ArrayList<Int>): Int {
        var sum = 0
        for (i in value)
            sum+=i
        return sum
    }
// Ваш код закінчується тут

    // Завдання 4
// Заповніть словник, де:
// - ключ – назва групи
// - значення – середня оцінка всіх студентів групи

    var groupAvg = HashMap<String,Float>()

// Ваш код починається тут

    @RequiresApi(Build.VERSION_CODES.N)
    fun task4(){
        for (group in sumPoints){
            groupAvg[group.key] = calculateAverage((group.value.values.stream().collect(Collectors.toList()) as java.util.ArrayList<Int>?)!!)
        }
    }

    private fun calculateAverage(points: java.util.ArrayList<Int>): Float {
        return (calculateSum(points).toFloat()/points.size)
    }


// Ваш код закінчується тут

// Завдання 5
// Заповніть словник, де:
// - ключ – назва групи
// - значення – масив студентів, які мають >= 60 балів

    var passedPerGroup = HashMap<String, ArrayList<String>>()

// Ваш код починається тут
    fun task5(){
        for (group in sumPoints){
            val passedStudents = ArrayList<String>()
            for (student in group.value){
                if (student.value >= 60) passedStudents.add(student.key)
            }
            passedPerGroup[group.key] = passedStudents
        }
    }

// Ваш код закінчується тут
    fun printResults(){
    Log.d("First Task", studentsGroups.toString())
    Log.d("Second Task", studentPoints.toString())
    Log.d("Third Task", sumPoints.toString())
    Log.d("Fourth Task", groupAvg.toString())
    Log.d("Fifth Task", passedPerGroup.toString())
    }

}