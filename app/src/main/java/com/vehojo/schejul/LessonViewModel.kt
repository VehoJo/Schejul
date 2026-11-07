package com.vehojo.schejul

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class LessonViewModel(application: Application) : AndroidViewModel(application) {
    private val lessonDAO = SchejulDatabase
        .getDatabase(application)
        .lessonDao()

    val lessons: StateFlow<List<Lesson>> =
        lessonDAO.getAllLessons().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    fun addLesson(lesson: Lesson) {
        viewModelScope.launch {
            lessonDAO.insertLesson(lesson)
        }
    }

    fun deleteLesson(lesson: Lesson) {
        viewModelScope.launch {
            lessonDAO.deleteLesson(lesson)
        }
    }

    fun updateLesson(lesson: Lesson) {
        viewModelScope.launch {
            lessonDAO.updateLesson(lesson)
        }
    }
}