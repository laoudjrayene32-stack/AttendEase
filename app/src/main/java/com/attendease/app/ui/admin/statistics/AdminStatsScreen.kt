package com.attendease.app.ui.admin.statistics

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
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
import com.attendease.app.ui.teacher.dashboard.StatCard
import com.attendease.app.ui.theme.*

// ✅ حذفنا الدالة الخاطئة اللي كانت تحجب LinearProgressIndicator الأصلية

@Composable
fun AdminStatsScreen(onNavigate: (String) -> Unit) {
    val courses = listOf(
        Course(1, "cid1", "Mathematics 101", "Prof. Sarah Johnson", "A-204", "08:00-09:30", 28, 25, CourseStatus.DONE),
        Course(2, "cid2", "Physics 201",     "Dr. Ahmed Benali",    "B-112", "10:00-11:30", 24, 20, CourseStatus.LIVE),
        Course(3, "cid3", "Chemistry 301",   "Ms. Fatima Zohra",    "C-305", "13:00-14:30", 30,  0, CourseStatus.UPCOMING),
        Course(4, "cid4", "Advanced Math",   "Prof. Sarah Johnson", "A-210", "15:00-16:30", 20,  0, CourseStatus.UPCOMING),
    )

    Scaffold(bottomBar = { AdminBottomNav(3, onNavigate, {}) }) { padding ->
        LazyColumn(modifier = Modifier.fillMaxSize().padding(padding)) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Brush.linearGradient(listOf(BluePrimary, PurpleMid, PurpleAccent)))
                        .padding(top = 48.dp, start = 24.dp, end = 24.dp, bottom = 28.dp)
                ) {
                    Column {
                        Text("Statistics",      color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.ExtraBold)
                        Text("System Overview", color = Color.White.copy(.75f), fontSize = 13.sp)
                    }
                }
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    StatCard(Modifier.weight(1f), Icons.Default.Group,      PurpleBg, BluePrimary,  "3",   "TEACHERS")
                    StatCard(Modifier.weight(1f), Icons.Default.MenuBook,   GreenBg,  GreenSuccess, "4",   "CLASSES")
                    StatCard(Modifier.weight(1f), Icons.Default.TrendingUp, GreenBg,  GreenSuccess, "85%", "AVG")
                    StatCard(Modifier.weight(1f), Icons.Default.Person,     PurpleBg, BluePrimary,  "116", "STUDENTS")
                }
            }

            item {
                Text(
                    "Attendance by Class",
                    modifier = Modifier.padding(start = 20.dp, bottom = 12.dp),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            items(courses) { cls ->
                val pct: Float = if (cls.status != CourseStatus.UPCOMING && cls.totalStudents > 0)
                    (cls.presentCount.toFloat() / cls.totalStudents.toFloat()).coerceIn(0f, 1f)
                else 0f

                val pctDisplay = (pct * 100).toInt()

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 5.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column {
                                Text(cls.name,        fontWeight = FontWeight.Bold, fontSize = 13.sp)
                                Text(cls.teacherName, fontSize = 11.sp, color = TextMuted)
                            }
                            Text(
                                if (cls.status != CourseStatus.UPCOMING) "$pctDisplay%" else "—",
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 16.sp,
                                color = if (cls.status == CourseStatus.UPCOMING) TextMuted else GreenSuccess
                            )
                        }

                        Spacer(Modifier.height(8.dp))

                        LinearProgressIndicator(
                            progress = { pct },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(8.dp),
                            color = BluePrimary,
                            trackColor = BorderColor
                        )
                    }
                }
            }

            item { Spacer(Modifier.height(16.dp)) }
        }
    }
}