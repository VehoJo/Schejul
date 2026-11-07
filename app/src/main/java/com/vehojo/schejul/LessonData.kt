package com.vehojo.schejul

import android.content.Context
import androidx.room3.Dao
import androidx.room3.Database
import androidx.room3.Delete
import androidx.room3.Entity
import androidx.room3.Insert
import androidx.room3.PrimaryKey
import androidx.room3.Query
import androidx.room3.Room
import androidx.room3.RoomDatabase
import androidx.room3.Update
import androidx.sqlite.driver.AndroidSQLiteDriver
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "lessons")
data class Lesson(
    val selectedNum: Int,
    val date: String,
    val startTime: String,
    val endTime: String,
    val name: String,
    val classroom: String,

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)

@Dao
interface LessonDAO {
    @Query("SELECT * FROM lessons ORDER BY date, startTime")
    fun getAllLessons(): Flow<List<Lesson>>
    @Insert
    suspend fun insertLesson(lesson: Lesson): Long
    @Update
    suspend fun updateLesson(lesson: Lesson)
    @Delete
    suspend fun deleteLesson(lesson: Lesson)
}

@Database(
    entities = [Lesson::class],
    version = 1,
    exportSchema = false
)
abstract class SchejulDatabase: RoomDatabase() {
    abstract fun lessonDao(): LessonDAO
    companion object {
        @Volatile
        private var INSTANSE: SchejulDatabase? = null
        fun getDatabase(context: Context): SchejulDatabase {
            return INSTANSE?: synchronized(this) {
                INSTANSE?: Room.databaseBuilder(
                    context.applicationContext,
                    SchejulDatabase::class.java,
                    "schejul_database"
                ).setDriver(AndroidSQLiteDriver()).build().also{
                    INSTANSE = it
                }
            }
        }
    }
}