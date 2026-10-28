package com.vehojo.schejul.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vehojo.schejul.R
import com.vehojo.schejul.ui.components.Header
import com.vehojo.schejul.ui.components.LessonBox
import com.vehojo.schejul.ui.components.NavBar
import com.vehojo.schejul.ui.theme.Blue
import com.vehojo.schejul.ui.theme.Primary
import com.vehojo.schejul.ui.theme.Roboto
import com.vehojo.schejul.ui.theme.SchejulTheme
import com.vehojo.schejul.ui.theme.Secondary

@Preview(showBackground = true)
@Composable
fun HomeScreen() {
    SchejulTheme {
        Box(modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()) {
            Column() {
                Header()
                NextLesson()
                Spacer(modifier = Modifier.height(25.dp))
                LessonBox(
                    startTime = "08:00",
                    endTime = "08:40",
                    nameLesson = "История",
                    classroomNum = "Кабинет 13",
                    lessonNum = "1",
                )
                Spacer(modifier = Modifier.weight(1f))
                AddLesson()
                Spacer(modifier = Modifier.height(15.dp))
                NavBar(
                    homeColor = MaterialTheme.colorScheme.tertiary,
                    newColor = MaterialTheme.colorScheme.secondary,
                    settingsColor = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NextLesson() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 35.dp, end = 35.dp)
    ) {
        Spacer(modifier = Modifier.height(45.dp))
        Row() {
            Text(
                text = "Следующий урок".uppercase(),
                fontFamily = Roboto,
                fontWeight = FontWeight.SemiBold,
                fontSize = 11.sp,
                color = MaterialTheme.colorScheme.secondary
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "1-й урок · 08:00-08:40",
                fontFamily = Roboto,
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.tertiary
            )
        }
        Spacer(modifier = Modifier.height(7.dp))
        Text(
            text = "История",
            fontFamily = Roboto,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = R.drawable.icon_map),
                tint = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.size(20.dp),
                contentDescription = "Map"
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = "История",
                fontFamily = Roboto,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.secondary
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                painter = painterResource(id = R.drawable.icon_time),
                tint = MaterialTheme.colorScheme.tertiary,
                modifier = Modifier.size(20.dp),
                contentDescription = "Clock"
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = "Через 32 минуты",
                fontFamily = Roboto,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.secondary
            )
        }
    }
    Spacer(modifier = Modifier.height(40.dp))
    Row(verticalAlignment = Alignment.CenterVertically) {
        Spacer(modifier = Modifier.width(15.dp))
        Text(
            text = "Сегодня",
            fontFamily = Roboto,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "1 уроков · до 8:40",
            fontFamily = Roboto,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.secondary
        )
        Spacer(modifier = Modifier.width(15.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun AddLesson() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .clickable{}
            .padding(horizontal = 15.dp)
            .height(50.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(100.dp))
            .background(Blue)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = R.drawable.icon_plus),
                tint = MaterialTheme.colorScheme.surface,
                modifier = Modifier.size(20.dp),
                contentDescription = "New Lesson"
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Добавить урок",
                fontFamily = Roboto,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.surface
            )
        }
    }
}