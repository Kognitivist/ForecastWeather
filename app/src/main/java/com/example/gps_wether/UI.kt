package com.example.gps_wether


import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

import java.util.*

@Preview(showBackground = true, showSystemUi = true)
@OptIn(DelicateCoroutinesApi::class)
@Composable
fun Ui(){

    val date = Data()
    val date1 = remember {
        mutableStateOf(date.getDateToDay())
    }
    val date2 = remember {
        mutableStateOf(date.getTomorrowDay())
    }
    val date3 = remember {
        mutableStateOf(date.getAfterDay())
    }
    val dayOfWeek = remember {
        mutableStateOf(date.getDayOfWeek())
    }


    val gradient = Brush.verticalGradient(0.2f to colorResource(id = R.color.color1),
        0.8f to colorResource(id = R.color.color2))

    val district = remember {
        mutableStateOf("...")
    }

    val morningTemp = remember {
        mutableStateOf("...")
    }
    val dayTemp = remember {
        mutableStateOf("...")
    }
    val eveningTemp = remember {
        mutableStateOf("...")
    }
    val nightTemp = remember {
        mutableStateOf("...")
    }

    val morningPrecipitation = remember {
        mutableStateOf("...")
    }
    val dayPrecipitation = remember {
        mutableStateOf("...")
    }
    val eveningPrecipitation = remember {
        mutableStateOf("...")
    }
    val nightPrecipitation = remember {
        mutableStateOf("...")
    }

    val morningWind = remember {
        mutableStateOf("...")
    }
    val dayWind = remember {
        mutableStateOf("...")
    }
    val eveningWind = remember {
        mutableStateOf("...")
    }
    val nightWind = remember {
        mutableStateOf("...")
    }

    val weatherElements = mutableMapOf("morningTemp" to morningTemp,
        "dayTemp" to dayTemp,"eveningTemp" to eveningTemp,"nightTemp" to nightTemp,
        "morningPrecipitation" to morningPrecipitation, "dayPrecipitation" to dayPrecipitation,
        "eveningPrecipitation" to eveningPrecipitation, "nightPrecipitation" to nightPrecipitation,
        "morningWind" to morningWind,"dayWind" to dayWind,"eveningWind" to eveningWind,
        "nightWind" to nightWind, "district" to district)

    val alpha = remember {mutableStateOf(0.0f)}
    val enabledButton1 = remember { mutableStateOf(false)}
    val enabledButton2 = remember { mutableStateOf(true)}
    val enabledButton3 = remember { mutableStateOf(true)}



    Column(
        modifier = Modifier
            .fillMaxSize(1f)
            .background(gradient)
    ) {
        //Строка с логотипом
        Row(
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(top = 100.dp),
            horizontalArrangement = Arrangement.Center,
        ) {
            Button(modifier = Modifier
                .padding(all = 10.dp)
                .size(size = 110.dp),
                shape = RoundedCornerShape(size = 100.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.indigo)),
                onClick = {
                    GlobalScope.launch {
                        parsingWeather(MyLocation.getMyLocLatitude(),
                            MyLocation.getMyLocLongitude(),
                            weatherElements,1)
                    }
                    Log.d("myLog",MyLocation.getMyLocLatitude().toString())
                    enabledButton1.value = false
                    enabledButton2.value = true
                    enabledButton3.value = true
                    alpha.value = 1.0f
            }) {
                Image(
                        painter = painterResource(id = R.drawable.icon),
                        contentDescription = "icon",
                        modifier = Modifier
                            .size(width = 100.dp, height = 100.dp)
                            .background(colorResource(id = R.color.indigo)),
                    )
            }
        }
        //район
        Row(modifier = Modifier
            .fillMaxWidth(1f)
            .padding(top = 40.dp, start = 10.dp, end = 10.dp)
            .height(80.dp)
            .alpha(alpha.value),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically) {
            Text(text = district.value,
                fontWeight = FontWeight(400),
                color = Color.Black, fontSize = 20.sp)

        }
        //кнопки
        Row(modifier = Modifier.fillMaxWidth(1f)
            .alpha(alpha.value),
            horizontalArrangement = Arrangement.SpaceAround) {
            Button(
                shape = RoundedCornerShape(size = 100.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorResource(id = R.color.color3),
                    contentColor = colorResource(id = R.color.text)),
                enabled = enabledButton1.value,
                onClick = {
                    GlobalScope.launch {
                        parsingWeather(MyLocation.getMyLocLatitude(),
                            MyLocation.getMyLocLongitude(),
                            weatherElements,1)
                }
                    enabledButton1.value = false
                    enabledButton2.value = true
                    enabledButton3.value = true
                }) {
                Text(text = "Сегодня\n${date1.value}", textAlign = TextAlign.Center)
            }
            Button(
                shape = RoundedCornerShape(size = 100.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorResource(id = R.color.color3),
                    contentColor = colorResource(id = R.color.text)),
                enabled = enabledButton2.value,
                onClick = {
                GlobalScope.launch {
                    parsingWeather(MyLocation.getMyLocLatitude(),
                        MyLocation.getMyLocLongitude(),
                        weatherElements,2)
                }
                    enabledButton2.value = false
                    enabledButton1.value = true
                    enabledButton3.value = true
                }) {
                Text(text = "Завтра\n${date2.value}", textAlign = TextAlign.Center)
            }
            Button(
                shape = RoundedCornerShape(size = 100.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorResource(id = R.color.color3),
                    contentColor = colorResource(id = R.color.text)),
                enabled = enabledButton3.value,
                onClick = {
                GlobalScope.launch {
                    parsingWeather(MyLocation.getMyLocLatitude(),
                        MyLocation.getMyLocLongitude(),
                        weatherElements,3)
                }
                    enabledButton3.value = false
                    enabledButton2.value = true
                    enabledButton1.value = true
                }) {
                Text(text = "${dayOfWeek.value}\n${date3.value}", textAlign = TextAlign.Center)
            }

        }
        //таблица
        Row(
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(top = 50.dp, start = 10.dp, end = 10.dp)
                .height(180.dp)
                .alpha(alpha.value),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.fillMaxHeight(1f),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(text = "")
                Text(text = "Утром",
                    fontWeight = FontWeight(700),
                    color = Color.Black)
                Text(text = "Днём",
                    fontWeight = FontWeight(700),
                    color = Color.Black)
                Text(text = "Вечером",
                    fontWeight = FontWeight(700),
                    color = Color.Black)
                Text(text = "Ночью",
                    fontWeight = FontWeight(700),
                    color = Color.Black)
            }
            Column(
                modifier = Modifier.fillMaxHeight(1f),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Температура",
                    fontWeight = FontWeight(700),
                    color = Color.Black)
                Text(text = morningTemp.value,
                    color = Color.Black)
                Text(text = dayTemp.value,
                    color = Color.Black)
                Text(text = eveningTemp.value,
                    color = Color.Black)
                Text(text = nightTemp.value,
                    color = Color.Black)
            }
            Column(
                modifier = Modifier.fillMaxHeight(1f),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Осадки",
                    fontWeight = FontWeight(700),
                    color = Color.Black)
                Text(text = morningPrecipitation.value,
                    color = Color.Black)
                Text(text = dayPrecipitation.value,
                    color = Color.Black)
                Text(text = eveningPrecipitation.value,
                    color = Color.Black)
                Text(text = nightPrecipitation.value,
                    color = Color.Black)
            }
            Column(
                modifier = Modifier.fillMaxHeight(1f),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Ветер",
                    fontWeight = FontWeight(700),
                    color = Color.Black)
                Text(text = morningWind.value,
                    color = Color.Black)
                Text(text = dayWind.value,
                    color = Color.Black)
                Text(text = eveningWind.value,
                    color = Color.Black)
                Text(text = nightWind.value,
                    color = Color.Black)
            }
        }
    }




}