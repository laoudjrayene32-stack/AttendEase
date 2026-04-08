package com.attendease.app.ui.teacher.statistics

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.attendease.app.ui.teacher.dashboard.StatCard
import com.attendease.app.ui.theme.*

@Composable
fun TeacherStatsScreen(onBack: () -> Unit) {
    val days    = listOf("Mon", "Tue", "Wed", "Thu", "Fri")
    val rates   = listOf(82, 91, 78, 95, 89)
    val maxRate = rates.max()

    Scaffold(
        bottomBar = {
            NavigationBar(containerColor = Color.White, tonalElevation = 8.dp) {
                NavigationBarItem(selected = false, onClick = onBack,  icon = { Icon(Icons.Default.GridView, null) }, label = { Text("Classes") })
                NavigationBarItem(selected = true,  onClick = {},      icon = { Icon(Icons.Default.BarChart, null) }, label = { Text("Statistics") })
                NavigationBarItem(selected = false, onClick = onBack,  icon = { Icon(Icons.Default.Logout, null, tint = RedDanger) }, label = { Text("Logout", color = RedDanger) })
            }
        }
    ) { padding ->
        LazyColumn(modifier = Modifier.fillMaxSize().padding(padding)) {
            item {
                Box(
                    modifier = Modifier.fillMaxWidth()
                        .background(Brush.linearGradient(listOf(BluePrimary, PurpleMid, PurpleAccent)))
                        .padding(top = 48.dp, start = 24.dp, end = 24.dp, bottom = 28.dp)
                ) {
                    Column {
                        Text("Statistics",      color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.ExtraBold)
                        Text("Weekly Overview", color = Color.White.copy(.75f), fontSize = 13.sp)
                    }
                }
            }

            item {
                Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 16.dp), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    StatCard(Modifier.weight(1f), Icons.Default.TrendingUp, GreenBg,  GreenSuccess, "89%", "AVG ATTEND.")
                    StatCard(Modifier.weight(1f), Icons.Default.Group,      PurpleBg, BluePrimary,  "48",  "STUDENTS")
                }
            }

            item {
                Text("Weekly Attendance", modifier = Modifier.padding(start = 20.dp, bottom = 12.dp), fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Card(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Row(modifier = Modifier.padding(20.dp), horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.Bottom) {
                        days.forEachIndexed { i, day ->
                            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(1f)) {
                                Text("${rates[i]}%", fontSize = 10.sp, color = TextMuted, fontWeight = FontWeight.Bold)
                                Spacer(Modifier.height(4.dp))
                                Box(
                                    modifier = Modifier.fillMaxWidth()
                                        .height((rates[i] * 80 / maxRate).dp)
                                        .background(
                                            brush = Brush.verticalGradient(listOf(BluePrimary, PurpleAccent)),
                                            shape = RoundedCornerShape(topStart = 6.dp, topEnd = 6.dp)
                                        )
                                )
                                Spacer(Modifier.height(6.dp))
                                Text(day, fontSize = 10.sp, color = TextMuted, fontWeight = FontWeight.SemiBold)
                            }
                        }
                    }
                }
                Spacer(Modifier.height(16.dp))
            }
        }
    }
}
