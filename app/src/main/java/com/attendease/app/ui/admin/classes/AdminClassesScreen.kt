package com.attendease.app.ui.admin.classes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.attendease.app.data.model.*
import com.attendease.app.ui.admin.teachers.AdminBottomNav
import com.attendease.app.ui.teacher.dashboard.CourseCard
import com.attendease.app.ui.theme.*

@Composable
fun AdminClassesScreen(onNavigate: (String) -> Unit) {
    val courses = listOf(
        Course(1, "cid1", "Mathematics 101", "Prof. Sarah Johnson", "A-204", "08:00-09:30", 28, 25, CourseStatus.DONE),
        Course(2, "cid2", "Physics 201",     "Dr. Ahmed Benali",    "B-112", "10:00-11:30", 24, 20, CourseStatus.LIVE),
        Course(3, "cid3", "Chemistry 301",   "Ms. Fatima Zohra",    "C-305", "13:00-14:30", 30,  0, CourseStatus.UPCOMING),
        Course(4, "cid4", "Advanced Math",   "Prof. Sarah Johnson", "A-210", "15:00-16:30", 20,  0, CourseStatus.UPCOMING),
    )

    Scaffold(bottomBar = { AdminBottomNav(1, onNavigate, {}) }) { padding ->
        LazyColumn(modifier = Modifier.fillMaxSize().padding(padding)) {
            item {
                Box(
                    modifier = Modifier.fillMaxWidth()
                        .background(Brush.linearGradient(listOf(BluePrimary, PurpleMid, PurpleAccent)))
                        .padding(top = 48.dp, start = 24.dp, end = 24.dp, bottom = 28.dp)
                ) {
                    Column {
                        Text("Classes",               color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.ExtraBold)
                        Text("All scheduled classes", color = Color.White.copy(.75f), fontSize = 13.sp)
                    }
                }
            }
            item { Text("${courses.size} Classes Today", modifier = Modifier.padding(start = 20.dp, top = 20.dp, bottom = 12.dp), fontSize = 16.sp, fontWeight = FontWeight.Bold) }
            items(courses) { course -> CourseCard(course = course, onClick = {}) }
            item { Spacer(Modifier.height(16.dp)) }
        }
    }
}
