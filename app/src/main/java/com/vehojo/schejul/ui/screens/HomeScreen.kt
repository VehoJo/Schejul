package com.vehojo.schejul.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.vehojo.schejul.Lesson
import com.vehojo.schejul.R
import com.vehojo.schejul.ui.components.Header
import com.vehojo.schejul.ui.components.LessonBox
import com.vehojo.schejul.ui.components.NavBar
import com.vehojo.schejul.ui.theme.Roboto
import com.vehojo.schejul.ui.theme.SchejulTheme
import kotlinx.coroutines.delay
import java.time.Duration
import java.time.LocalDate
import java.time.LocalTime

@Composable
fun HomeScreen(
    onNextScreen: () -> Unit,
    onDeleteLesson: (Lesson) -> Unit,
    lessons: List<Lesson>,
) {
    var lessonDelete by remember {
        mutableStateOf<Lesson?>(null)
    }
    var showConfirmDelete by remember { mutableStateOf(false) }
    val sortedLesson = lessons.sortedBy { it.selectedNum }
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
    ) {
        Header()
        NextLesson(lessons = lessons)
        Spacer(modifier = Modifier.height(15.dp))
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(sortedLesson) { lesson ->
                LessonBox(
                    startTime = lesson.startTime,
                    endTime = lesson.endTime,
                    nameLesson = lesson.name,
                    classroomNum = "Кабинет: ${lesson.classroom}",
                    lessonNum = lesson.selectedNum.toString(),
                    boxCol = MaterialTheme.colorScheme.onSurface,
                    onDelete = {onDeleteLesson(lesson)},
                    showConfirmDelete = showConfirmDelete,
                    onShowConfirmDelete = {
                        lessonDelete = lesson
                        showConfirmDelete = true}
                )
                Spacer(modifier = Modifier.height(15.dp))
            }
        }
        AddLessonBut(onNextScreen = onNextScreen)
        Spacer(modifier = Modifier.height(15.dp))
        NavBar(
            onNextScreen = onNextScreen,
            homeColor = MaterialTheme.colorScheme.tertiary,
            newColor = MaterialTheme.colorScheme.secondary,
            settingsColor = MaterialTheme.colorScheme.secondary,
            homeFW = FontWeight.SemiBold,
            newFW = FontWeight.Normal,
            settingsFW = FontWeight.Normal
        )
    }

    if (showConfirmDelete) {
        Box(modifier = Modifier.fillMaxSize().background(Color(0x99000000)),
            contentAlignment = Alignment.Center) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
            ) {
                Column(
                    modifier = Modifier
                        .clip(RoundedCornerShape(28.dp))
                        .background(MaterialTheme.colorScheme.background)
                        .padding(horizontal = 24.dp)
                ) {
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        text = "Удалить урок?",
                        fontFamily = Roboto,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 24.sp,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding()
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Вы точно хотите удалить урок?",
                        fontFamily = Roboto,
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    Row {
                        Spacer(modifier = Modifier.weight(1f))
                        Button(
                            onClick = {showConfirmDelete = false},
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.background,
                                contentColor = MaterialTheme.colorScheme.secondary
                            ),
                        ) {
                            Text(
                                text = "Отмена",
                                fontFamily = Roboto,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 14.sp,
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(
                            onClick = {lessonDelete?.let { lesson ->
                                onDeleteLesson(lesson)
                            }

                                lessonDelete = null
                                showConfirmDelete = false},
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.error,
                                contentColor = MaterialTheme.colorScheme.background
                            ),
                        ) {
                            Text(
                                text = "Удалить",
                                fontFamily = Roboto,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 14.sp,
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                }
            }
        }
    }
}

@Composable
fun NextLesson(lessons: List<Lesson>) {
    val today = LocalDate.now().toString()
    var now by remember{ mutableStateOf(LocalTime.now()) }
    val currentLesson = lessons.firstOrNull { lesson ->
        val start = LocalTime.parse(lesson.startTime)
        val end = LocalTime.parse(lesson.endTime)
        now >= start && now < end
    }
    LaunchedEffect(Unit) {
        while (true) {
            now = LocalTime.now()
            delay(1_000L)
        }
    }
    val nextLesson = lessons
        .filter { it.date == today }
        .filter { LocalTime.parse(it.startTime).isAfter(now) }
        .minByOrNull { LocalTime.parse(it.startTime) }
    val minutesToNextLesson = nextLesson?.let { lesson ->
        Duration.between(
            now, LocalTime.parse(lesson.startTime)
        ).toMinutes()
    }

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
                    text = "текущий урок".uppercase(),
                    fontFamily = Roboto,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 11.sp,
                    color = MaterialTheme.colorScheme.secondary
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = currentLesson?.let { lesson ->
                        "${lesson.selectedNum}-й урок · ${lesson.startTime}-${lesson.endTime}"
                    } ?: "Нет урока",
                    fontFamily = Roboto,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.tertiary
                )
            }
            Spacer(modifier = Modifier.height(7.dp))
            Text(
                text = currentLesson?.name ?: "Нет урока",
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
                    text = nextLesson?.name ?: "Нет урока",
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
                    text = "Через $minutesToNextLesson мин.",
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

@Composable
fun AddLessonBut(onNextScreen: () -> Unit) {
    Column(modifier = Modifier.padding(horizontal = 15.dp)) {
        Button(
            onClick = onNextScreen,
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

@Preview
@Composable
fun HomeScreenPreview() {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
    ) {
        Header()
        NextLesson(lessons = emptyList())
        Spacer(modifier = Modifier.height(15.dp))
        LessonBox(
            startTime = "08:00",
            endTime = "08:40",
            nameLesson = "История",
            classroomNum = "Кабинет 13",
            lessonNum = "1",
            boxCol = MaterialTheme.colorScheme.onSurface,
            onDelete = {},
            showConfirmDelete = false,
            onShowConfirmDelete = {}
        )
        Spacer(modifier = Modifier.weight(1f))
        AddLessonBut(onNextScreen = {})
        Spacer(modifier = Modifier.height(15.dp))
        NavBar(
            onNextScreen = {},
            homeColor = MaterialTheme.colorScheme.tertiary,
            newColor = MaterialTheme.colorScheme.secondary,
            settingsColor = MaterialTheme.colorScheme.secondary,
            homeFW = FontWeight.SemiBold,
            newFW = FontWeight.Normal,
            settingsFW = FontWeight.Normal
        )
    }
}