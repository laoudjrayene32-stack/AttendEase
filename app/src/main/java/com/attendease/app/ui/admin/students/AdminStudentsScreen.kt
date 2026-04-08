package com.attendease.app.ui.admin.students

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.attendease.app.ui.admin.teachers.AdminBottomNav
import com.attendease.app.ui.theme.*

data class StudentDisplay(val name: String, val sid: String, val cls: String, val present: Boolean)

@Composable
fun AdminStudentsScreen(onNavigate: (String) -> Unit) {
    val students = listOf(
        StudentDisplay("Ali Benmoussa",  "STU001", "Mathematics 101", true),
        StudentDisplay("Rania Hadj",     "STU002", "Mathematics 101", true),
        StudentDisplay("Karim Zerrouk",  "STU003", "Mathematics 101", false),
        StudentDisplay("Nour Belhadj",   "STU004", "Mathematics 101", true),
        StudentDisplay("Sami Laib",      "STU006", "Physics 201",     true),
        StudentDisplay("Imane Fares",    "STU007", "Physics 201",     true),
        StudentDisplay("Rachid Khelif",  "STU008", "Physics 201",     false),
    )

    Scaffold(bottomBar = { AdminBottomNav(2, onNavigate, {}) }) { padding ->
        LazyColumn(modifier = Modifier.fillMaxSize().padding(padding)) {
            item {
                Box(
                    modifier = Modifier.fillMaxWidth()
                        .background(Brush.linearGradient(listOf(BluePrimary, PurpleMid, PurpleAccent)))
                        .padding(top = 48.dp, start = 24.dp, end = 24.dp, bottom = 28.dp)
                ) {
                    Column {
                        Text("Students",              color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.ExtraBold)
                        Text("All enrolled students", color = Color.White.copy(.75f), fontSize = 13.sp)
                    }
                }
            }
            item { Text("${students.size} Students", modifier = Modifier.padding(start = 20.dp, top = 20.dp, bottom = 12.dp), fontSize = 16.sp, fontWeight = FontWeight.Bold) }
            items(students) { s ->
                Card(modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 5.dp), shape = RoundedCornerShape(14.dp), colors = CardDefaults.cardColors(containerColor = Color.White), elevation = CardDefaults.cardElevation(1.dp)) {
                    Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(14.dp)) {
                        Box(
                            modifier = Modifier.size(44.dp).clip(RoundedCornerShape(12.dp))
                                .background(if (s.present) GreenSuccess else RedDanger),
                            contentAlignment = Alignment.Center
                        ) { Text(s.name.split(" ").take(2).map { it.first() }.joinToString(""), color = Color.White, fontWeight = FontWeight.ExtraBold, fontSize = 14.sp) }
                        Column(Modifier.weight(1f)) {
                            Text(s.name, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                            Text("${s.sid} · ${s.cls}", fontSize = 12.sp, color = TextMuted)
                        }
                        Surface(shape = RoundedCornerShape(20.dp), color = if (s.present) GreenBg else RedBg) {
                            Text(if (s.present) "Present" else "Absent",
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                                color = if (s.present) GreenSuccess else RedDanger,
                                fontSize = 11.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
            item { Spacer(Modifier.height(16.dp)) }
        }
    }
}
