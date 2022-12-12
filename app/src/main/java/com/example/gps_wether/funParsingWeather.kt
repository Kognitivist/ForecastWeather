package com.example.gps_wether


import androidx.compose.runtime.MutableState
import kotlinx.coroutines.*
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements


@OptIn(DelicateCoroutinesApi::class)
suspend fun parsingWeather(latitude: Double = 40.0,
                           longitude: Double = 40.0,
                           weatherElements:MutableMap<String, MutableState<String>>,
                           numberTable: Int) = coroutineScope{

    launch(newSingleThreadContext("Parsing_Thread")) {

        val doc: Document =
            Jsoup.connect("https://yandex.ru/pogoda/details/10-day-weather?lat=$latitude&lon=$longitude").get()
        val cards: Elements = doc.getElementsByTag("h1")
        val district = cards[0]
        weatherElements["district"]!!.value =district.text().substring(20)

        val tables: Elements = doc.getElementsByTag("tbody")
        val firstTable: Element = tables[numberTable-1]
        val morningData: Element = firstTable.children()[0]
        weatherElements["morningTemp"]!!.value = morningData.children()[0].text().substring(6, 9)
            .replace("у", "", true)
            .replace("т", "", true)
            .replace(" ", "", true)
            .replace("…", "", true)
        weatherElements["morningPrecipitation"]!!.value = morningData.children()[2].text()
        weatherElements["morningWind"]!!.value = morningData.children()[5].text().substring(0, 3) + " м/с"
        val dayData: Element = firstTable.children()[1]
        weatherElements["dayTemp"]!!.value =
            dayData.children()[0].text().substring(5, 8)
                .replace("д", "", true)
                .replace("н", "", true)
                .replace(" ", "", true)
                .replace("…", "", true)
        weatherElements["dayPrecipitation"]!!.value = dayData.children()[2].text()
        weatherElements["dayWind"]!!.value = dayData.children()[5].text().substring(0, 3) + " м/с"
        val eveningData: Element = firstTable.children()[2]
        weatherElements["eveningTemp"]!!.value =
            eveningData.children()[0].text().substring(8, 11)
                .replace("в", "", true)
                .replace("е", "", true)
                .replace(" ", "", true)
                .replace("…", "", true)
        weatherElements["eveningPrecipitation"]!!.value = eveningData.children()[2].text()
        weatherElements["eveningWind"]!!.value = eveningData.children()[5].text().substring(0, 3) + " м/с"
        val nightData: Element = firstTable.children()[3]
        weatherElements["nightTemp"]!!.value =
            nightData.children()[0].text().substring(6, 9)
                .replace("н", "", true)
                .replace("о", "", true)
                .replace(" ", "", true)
                .replace("…", "", true)
        weatherElements["nightPrecipitation"]!!.value = nightData.children()[2].text()
        weatherElements["nightWind"]!!.value= nightData.children()[5].text().substring(0, 3) + " м/с"
    }

}



