package com.vehojo.schejul.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.vehojo.schejul.R
import com.vehojo.schejul.ui.components.Header
import com.vehojo.schejul.ui.components.LessonBox
import com.vehojo.schejul.ui.components.NavBar
import com.vehojo.schejul.ui.theme.Roboto
import com.vehojo.schejul.ui.theme.SchejulTheme

@Preview
@Composable
fun HomeScreen() {
    SchejulTheme {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize()
        ) {
            Header()
            NextLesson()
            Spacer(modifier = Modifier.height(15.dp))
            LessonBox(
                startTime = "08:00",
                endTime = "08:40",
                nameLesson = "История",
                classroomNum = "Кабинет 13",
                lessonNum = "1",
                boxCol = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.weight(1f))
            AddLessonBut()
            Spacer(modifier = Modifier.height(15.dp))
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
}

@Preview(showBackground = true)
@Composable
fun NextLesson() {
    Spacer(modifier = Modifier.height(25.dp))
    Column(
        modifier = Modifier
            .padding(horizontal = 15.dp)
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(24.dp))
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.height(18.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
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
            Spacer(modifier = Modifier.height(18.dp))
        }
    }
    Spacer(modifier = Modifier.height(25.dp))
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
fun AddLessonBut() {
    Column (modifier = Modifier.padding(horizontal = 15.dp)) {
        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.tertiary,
                contentColor = MaterialTheme.colorScheme.surface
            ),
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth()
        ) {
            Icon(
                painter = painterResource(id = R.drawable.icon_plus),
                modifier = Modifier.size(20.dp),
                contentDescription = "Plus"
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = "Добавить урок",
                fontFamily = Roboto,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp
            )
        }
    }
}