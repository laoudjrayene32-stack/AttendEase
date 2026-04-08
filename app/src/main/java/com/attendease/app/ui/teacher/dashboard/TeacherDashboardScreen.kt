package com.attendease.app.ui.teacher.dashboard

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.attendease.app.data.model.Course
import com.attendease.app.data.model.CourseStatus
import com.attendease.app.ui.theme.*

@Composable
fun TeacherDashboardScreen(
    viewModel: TeacherDashboardViewModel = hiltViewModel(),
    onCourseClick: (Course) -> Unit,
    onStatsClick: () -> Unit,
    onLogout: () -> Unit
) {
    val courses   by viewModel.courses.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    Scaffold(
        bottomBar = {
            NavigationBar(containerColor = Color.White, tonalElevation = 8.dp) {
                NavigationBarItem(selected = true,  onClick = {},          icon = { Icon(Icons.Default.GridView, null) },  label = { Text("Classes") })
                NavigationBarItem(selected = false, onClick = onStatsClick, icon = { Icon(Icons.Default.BarChart, null) },  label = { Text("Statistics") })
                NavigationBarItem(selected = false, onClick = onLogout,    icon = { Icon(Icons.Default.Logout, null, tint = RedDanger) }, label = { Text("Logout", color = RedDanger) })
            }
        }
    ) { padding ->
        LazyColumn(modifier = Modifier.fillMaxSize().padding(padding)) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Brush.linearGradient(listOf(BluePrimary, PurpleMid, PurpleAccent)))
                        .padding(top = 48.dp, start = 24.dp, end = 24.dp, bottom = 28.dp)
                ) {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.Top) {
                        Column {
                            Text("Welcome Back!", color = Color.White.copy(.7f), fontSize = 12.sp)
                            Text("Prof. Sarah Johnson", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.ExtraBold)
                            Text("📅 ${viewModel.todayDate}", color = Color.White.copy(.75f), fontSize = 13.sp)
                        }
                        Box(
                            modifier = Modifier.size(44.dp).clip(CircleShape)
                                .background(Color.White.copy(.25f))
                                .border(2.dp, Color.White.copy(.4f), CircleShape),
                            contentAlignment = Alignment.Center
                        ) { Text("SJ", color = Color.White, fontWeight = FontWeight.ExtraBold, fontSize = 15.sp) }
                    }
                }
            }

            item {
                Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 16.dp), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    StatCard(Modifier.weight(1f), Icons.Default.MenuBook,   PurpleBg, BluePrimary,  "${courses.size}", "CLASSES TODAY")
                    StatCard(Modifier.weight(1f), Icons.Default.TrendingUp, GreenBg,  GreenSuccess, "89%",             "AVG ATTENDANCE")
                }
            }

            item {
                Text("Today's Schedule", modifier = Modifier.padding(horizontal = 20.dp, vertical = 4.dp), fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }

            if (isLoading) {
                item {
                    Box(modifier = Modifier.fillMaxWidth().padding(32.dp), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(color = BluePrimary)
                    }
                }
            } else {
                items(courses) { course ->
                    CourseCard(course = course, onClick = { onCourseClick(course) })
                }
            }

            item { Spacer(Modifier.height(16.dp)) }
        }
    }
}

@Composable
fun StatCard(modifier: Modifier = Modifier, icon: ImageVector, iconBg: Color, iconTint: Color, value: String, label: String) {
    Card(modifier = modifier, shape = RoundedCornerShape(18.dp), elevation = CardDefaults.cardElevation(2.dp), colors = CardDefaults.cardColors(containerColor = Color.White)) {
        Column(modifier = Modifier.padding(18.dp)) {
            Box(modifier = Modifier.size(36.dp).clip(RoundedCornerShape(10.dp)).background(iconBg), contentAlignment = Alignment.Center) {
                Icon(icon, null, tint = iconTint, modifier = Modifier.size(18.dp))
            }
            Text(value, fontSize = 28.sp, fontWeight = FontWeight.ExtraBold, modifier = Modifier.padding(top = 4.dp))
            Text(label, fontSize = 10.sp, fontWeight = FontWeight.SemiBold, color = TextMuted, letterSpacing = 0.5.sp)
        }
    }
}

@Composable
fun CourseCard(course: Course, onClick: () -> Unit) {
    val (statusColor, statusBg, statusText) = when (course.status) {
        CourseStatus.DONE     -> Triple(GreenSuccess,  GreenBg,              "Completed")
        CourseStatus.LIVE     -> Triple(YellowWarning, Color(0xFFFEF3C7),   "🔴 Live")
        CourseStatus.UPCOMING -> Triple(BluePrimary,   PurpleBg,             "Upcoming")
    }

    Card(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 6.dp).clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.Top) {
                Text(course.name, fontWeight = FontWeight.Bold, fontSize = 15.sp, modifier = Modifier.weight(1f))
                Surface(shape = RoundedCornerShape(20.dp), color = statusBg) {
                    Text(statusText, modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp), color = statusColor, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                }
            }
            Spacer(Modifier.height(10.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                IconText(Icons.Default.AccessTime, course.time)
                IconText(Icons.Default.LocationOn, course.room)
                IconText(Icons.Default.Person,     "${course.totalStudents} students")
            }
            if (course.status != CourseStatus.UPCOMING) {
                Spacer(Modifier.height(10.dp))
                HorizontalDivider(color = BorderColor)
                Spacer(Modifier.height(10.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("Attendance recorded", fontSize = 12.sp, color = TextMuted)
                    Text("${course.presentCount}/${course.totalStudents} Present", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = GreenSuccess)
                }
            }
            Spacer(Modifier.height(10.dp))
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                Icon(Icons.Default.QrCodeScanner, null, tint = BluePrimary, modifier = Modifier.size(14.dp))
                Text("Tap to open QR scanner", color = BluePrimary, fontSize = 13.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun IconText(icon: ImageVector, text: String) {
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
        Icon(icon, null, tint = TextMuted, modifier = Modifier.size(13.dp))
        Text(text, fontSize = 12.sp, color = TextMuted, fontWeight = FontWeight.Medium)
    }
}
