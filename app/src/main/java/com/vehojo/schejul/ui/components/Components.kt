package com.vehojo.schejul.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vehojo.schejul.R
import com.vehojo.schejul.ui.theme.Blue
import com.vehojo.schejul.ui.theme.Primary
import com.vehojo.schejul.ui.theme.Roboto
import com.vehojo.schejul.ui.theme.Secondary
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@Preview(showBackground = true)
@Composable
fun Header() {
    val currentDate = remember { LocalDate.now() }
    val beautifulDate = remember { DateTimeFormatter.ofPattern("EEEE, d MMMM", Locale.forLanguageTag("ru-RU")) }
    val useDate = currentDate.format(beautifulDate)
    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(start = 20.dp, end = 30.dp, top = 10.dp)) {
        Icon(
            painter = painterResource(id = R.drawable.avatar_schejul_monochrome),
            tint = Blue,
            modifier = Modifier.size(50.dp),
            contentDescription = "App Icon"
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column() {
            Text(
                text = "Schejul",
                fontSize = 22.sp,
                fontFamily = Roboto,
                fontWeight = FontWeight.Bold,
                color = Primary,
            )
            Text(
                text = "$useDate",
                fontSize = 12.sp,
                fontFamily = Roboto,
                fontWeight = FontWeight.Normal,
                color = Secondary,
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        IconButton(onClick = {
            //Open settings
        }) {
            Icon(
                painter = painterResource(id = R.drawable.icon_settings),
                tint = Secondary,
                contentDescription = "Settings",
                modifier = Modifier
                    .size(30.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Footer() {
    Row(verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .padding(horizontal = 15.dp)) {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                painter = painterResource(id = R.drawable.icon_home),
                tint = Blue,
                modifier = Modifier.size(30.dp),
                contentDescription = "Home page"
            )
            Text(
                text = "Главная",
                fontSize = 12.sp,
                fontFamily = Roboto,
                fontWeight = FontWeight.SemiBold,
                color = Blue,
            )
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                painter = painterResource(id = R.drawable.icon_plus),
                tint = Secondary,
                modifier = Modifier.size(30.dp),
                contentDescription = "New Schedule"
            )
            Text(
                text = "Добавить",
                fontFamily = Roboto,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                color = Secondary,
            )
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                painter = painterResource(id = R.drawable.icon_settings),
                tint = Secondary,
                modifier = Modifier.size(30.dp),
                contentDescription = "Settings page"
            )
            Text(
                text = "Настройки",
                fontFamily = Roboto,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                color = Secondary,
            )
        }
    }
}

@Composable
fun LessonBox(
    startTime: String,
    endTime: String,
    nameLesson: String,
    classroomNum: String,
    lessonNum: String,
) {
    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 25.dp, end = 25.dp)
    ) {
        Column() {
            Text(
                text = startTime,
                fontFamily = Roboto,
                fontWeight = FontWeight.SemiBold,
                fontSize = 13.sp,
                color = Primary
            )
            Text(
                text = endTime,
                fontFamily = Roboto,
                fontWeight = FontWeight.Normal,
                fontSize = 11.sp,
                color = Secondary
            )
        }
        Spacer(modifier = Modifier.width(32.dp))
        Column() {
            Text(
                text = nameLesson,
                fontFamily = Roboto,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                color = Primary
            )
            Text(
                text = classroomNum,
                fontFamily = Roboto,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                color = Secondary
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = lessonNum,
            fontFamily = Roboto,
            fontWeight = FontWeight.Normal,
            fontSize = 11.sp,
            color = Secondary
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LessonBoxPreview() {
    LessonBox(
        startTime = "08:00",
        endTime = "08:40",
        nameLesson = "История",
        classroomNum = "Кабинет 13",
        lessonNum = "1",
    )
}