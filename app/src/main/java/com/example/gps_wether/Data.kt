package com.example.gps_wether

import java.text.SimpleDateFormat
import java.util.*

class Data {
    private val calendar: Calendar = Calendar.getInstance()
    private val sdf = SimpleDateFormat("dd-MM-yyyy")
    private val dOW = SimpleDateFormat("EEEE")

    fun getDateToDay(): String {
        val today = calendar.time
        return sdf.format(today)
    }
    fun getTomorrowDay(): String{
        calendar.add(Calendar.DAY_OF_YEAR, 1)
        val tomorrow = calendar.time
        return sdf.format(tomorrow)
    }
    fun getAfterDay(): String{
        calendar.add(Calendar.DAY_OF_YEAR, 1)
        val afterDay = calendar.time
        return sdf.format(afterDay)
    }
    fun getDayOfWeek(): String{
        val dayOW = calendar.time
        var dayOfWeek:String = dOW.format(dayOW)
//        when (dOW.format(dayOW)){
//            "Monday" -> dayOfWeek = "Понедельник"
//            "Tuesday" -> dayOfWeek = "Вторник"
//            "Wednesday" -> dayOfWeek = "Среда"
//            "Thursday" -> dayOfWeek = "Четверг"
//            "Friday" -> dayOfWeek = "Пятница"
//            "Saturday" -> dayOfWeek = "Суббота"
//            "Sunday" -> dayOfWeek = "Воскресенье"
//        }
        return dayOfWeek.capitalize()
    }

}