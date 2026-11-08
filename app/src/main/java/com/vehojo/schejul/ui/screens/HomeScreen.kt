package com.vehojo.schejul.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
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
import com.vehojo.schejul.Lesson
import com.vehojo.schejul.R
import com.vehojo.schejul.ui.components.Header
import com.vehojo.schejul.ui.components.LessonBox
import com.vehojo.schejul.ui.theme.Roboto
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
    var now by remember{ mutableStateOf(LocalTime.now()) }
    var showConfirmDelete by remember { mutableStateOf(false) }
    val sortedLesson = lessons.sortedBy { it.selectedNum }
    val currentLesson = lessons.firstOrNull { lesson ->
        val start = LocalTime.parse(lesson.startTime)
        val end = LocalTime.parse(lesson.endTime)
        lesson.date == LocalDate.now().toString() && now >= start && now < end
    }
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
    ) {
        Header()
        NextLesson(
            lessons = lessons
        )
        Spacer(modifier = Modifier.height(15.dp))
        Box(modifier = Modifier.weight(1f).fillMaxWidth()) {
            LazyColumn {
                items(sortedLesson) { lesson ->
                    val currentLessonHS = lesson.id == currentLesson?.id
                    LessonBox(
                        startTime = lesson.startTime,
                        endTime = lesson.endTime,
                        nameLesson = lesson.name,
                        classroomNum = if (lesson.classroom.isBlank()) {
                            ""
                        } else{
                            "Кабинет: ${lesson.classroom}"
                        },
                        lessonNum = lesson.selectedNum.toString(),
                        boxCol = if (currentLessonHS) {
                            MaterialTheme.colorScheme.tertiary
                        } else {
                            MaterialTheme.colorScheme.onSurface
                        },
                        onDelete = { onDeleteLesson(lesson) },
                        showConfirmDelete = showConfirmDelete,
                        onShowConfirmDelete = {
                            lessonDelete = lesson
                            showConfirmDelete = true
                        }
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                }
            }
            AddLessonBut(onNextScreen = onNextScreen)
        }
    }

    if (showConfirmDelete) {
        Box(modifier = Modifier.fillMaxSize()
            .background(Color(0x99000000))
            .clickable(
                interactionSource = null,
                indication = null
            ){
                showConfirmDelete = false
                lessonDelete = null
            },
            contentAlignment = Alignment.Center) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
                    .clickable(
                        interactionSource = null,
                    indication = null
                    ) {}
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
                            onClick = {
                                showConfirmDelete = false
                                lessonDelete = null
                            },
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
    val todayExam = lessons.filter {it.date == today}
    val lessonCount = todayExam.size
    var now by remember{ mutableStateOf(LocalTime.now()) }
    val lessonLastEnd = todayExam.maxByOrNull { LocalTime.parse(it.endTime) }?.endTime
    val currentLesson = lessons.firstOrNull { lesson ->
        val start = LocalTime.parse(lesson.startTime)
        val end = LocalTime.parse(lesson.endTime)
        now >= start && now < end
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
    val lessonDesc = when {
        lessonCount % 100 in 11..14 -> "уроков"
        lessonCount % 10 == 1 -> "урок"
        lessonCount % 100 in 2..4 -> "урока"
        else -> "уроков"
    }

    LaunchedEffect(Unit) {
        while (true) {
            now = LocalTime.now()
            delay(1_000L)
        }
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
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(horizontal = 16.dp)) {
        Text(
            text = "Сегодня",
            fontFamily = Roboto,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = if (lessonCount == 0) {
                "Нет уроков"
            } else {
                "$lessonCount $lessonDesc до $lessonLastEnd"
            },
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
    Row {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(end = 20.dp, bottom = 16.dp),
            horizontalAlignment = Alignment.End
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = onNextScreen,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.tertiary,
                    contentColor = MaterialTheme.colorScheme.surface
                ),
                contentPadding = PaddingValues(0.dp),
                modifier = Modifier
                    .size(65.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_plus),
                    modifier = Modifier.size(30.dp),
                    contentDescription = "Plus"
                )
            }
            Spacer(modifier = Modifier.navigationBarsPadding().height(30.dp))
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
        AddLessonBut(onNextScreen = {})
    }
}