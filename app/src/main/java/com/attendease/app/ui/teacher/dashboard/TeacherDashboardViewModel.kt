package com.attendease.app.ui.teacher.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.attendease.app.data.model.Course
import com.attendease.app.data.repository.AttendanceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class TeacherDashboardViewModel @Inject constructor(
    private val repo: AttendanceRepository
) : ViewModel() {

    private val _courses   = MutableStateFlow<List<Course>>(emptyList())
    val courses: StateFlow<List<Course>> = _courses

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    val todayDate: String = SimpleDateFormat("EEEE, MMMM d", Locale.ENGLISH).format(Date())

    fun loadCourses(teacherId: String) {
        viewModelScope.launch {
            repo.getCoursesForTeacher(teacherId)
                .onStart { _isLoading.value = true }
                .onEach  { _isLoading.value = false }
                .collect { _courses.value = it }
        }
    }
}
