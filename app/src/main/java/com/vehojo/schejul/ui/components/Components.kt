package com.vehojo.schejul.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vehojo.schejul.R
import com.vehojo.schejul.ui.theme.Roboto
import com.vehojo.schejul.ui.theme.SchejulTheme
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
            .background(MaterialTheme.colorScheme.background)
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(start = 20.dp, end = 20.dp, top = 10.dp)) {
        Icon(
            painter = painterResource(id = R.drawable.avatar_schejul_monochrome),
            tint = MaterialTheme.colorScheme.tertiary,
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
                color = MaterialTheme.colorScheme.primary,
            )
            Text(
                text = "$useDate",
                fontSize = 12.sp,
                fontFamily = Roboto,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colorScheme.secondary,
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        IconButton(onClick = {
            //Open settings
        }) {
            Icon(
                painter = painterResource(id = R.drawable.icon_settings),
                tint = MaterialTheme.colorScheme.secondary,
                contentDescription = "Settings",
                modifier = Modifier
                    .size(30.dp)
            )
        }
    }
}

@Composable
fun NavBar(
    homeColor: Color,
    newColor: Color,
    settingsColor: Color,
    homeFW: FontWeight,
    newFW: FontWeight,
    settingsFW: FontWeight) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surface)
            .fillMaxWidth()
            .height(80.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.clickable {}
        ) {
            Icon(
                painter = painterResource(id = R.drawable.icon_home),
                tint = homeColor,
                modifier = Modifier.size(30.dp),
                contentDescription = "Home page"
            )
            Text(
                text = "Главная",
                fontSize = 12.sp,
                fontFamily = Roboto,
                fontWeight = homeFW,
                color = homeColor,
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.clickable {}
        ) {
            Icon(
                painter = painterResource(id = R.drawable.icon_plus),
                tint = newColor,
                modifier = Modifier.size(30.dp),
                contentDescription = "New Schedule"
            )
            Text(
                text = "Добавить",
                fontFamily = Roboto,
                fontWeight = newFW,
                fontSize = 12.sp,
                color = newColor,
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.clickable {}
        ) {
            Icon(
                painter = painterResource(id = R.drawable.icon_settings),
                tint = settingsColor,
                modifier = Modifier.size(30.dp),
                contentDescription = "Settings page"
            )
            Text(
                text = "Настройки",
                fontFamily = Roboto,
                fontWeight = settingsFW,
                fontSize = 12.sp,
                color = settingsColor,
            )
        }
    }
    Spacer(modifier = Modifier
        .background(MaterialTheme.colorScheme.surface)
        .fillMaxWidth()
        .navigationBarsPadding())
}
@Composable
fun LessonBox(
    startTime: String,
    endTime: String,
    nameLesson: String,
    classroomNum: String,
    lessonNum: String,
    boxCol: Color
) {
    Box(contentAlignment = Alignment.Center,
        modifier = Modifier
            .padding(horizontal = 15.dp)
            .height(55.dp)
            .background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(12.dp))) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp)
                .fillMaxWidth()
        ) {
            Column() {
                Text(
                    text = startTime,
                    fontFamily = Roboto,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = endTime,
                    fontFamily = Roboto,
                    fontWeight = FontWeight.Normal,
                    fontSize = 11.sp,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
            Spacer(modifier = Modifier.width(20.dp))
            Box(
                modifier = Modifier
                    .size(width = 3.dp, height = 30.dp)
                    .background(color = boxCol, shape = RoundedCornerShape(2.dp)))
            Spacer(modifier = Modifier.width(10.dp))
            Column() {
                Text(
                    text = nameLesson,
                    fontFamily = Roboto,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = classroomNum,
                    fontFamily = Roboto,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = lessonNum,
                fontFamily = Roboto,
                fontWeight = FontWeight.Normal,
                fontSize = 11.sp,
                color = MaterialTheme.colorScheme.secondary
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LessonBoxPreview() {
    SchejulTheme {
        LessonBox(
            startTime = "08:00",
            endTime = "08:40",
            nameLesson = "История",
            classroomNum = "Кабинет 13",
            lessonNum = "1",
            boxCol = MaterialTheme.colorScheme.tertiary
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NavBarPreview() {
    SchejulTheme {
        NavBar(
            homeColor = MaterialTheme.colorScheme.tertiary,
            newColor = MaterialTheme.colorScheme.secondary,
            settingsColor = MaterialTheme.colorScheme.secondary,
            homeFW = FontWeight.SemiBold,
            newFW = FontWeight.Normal,
            settingsFW = FontWeight.Normal
        )
    }
}