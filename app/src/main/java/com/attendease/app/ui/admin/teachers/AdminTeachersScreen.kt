package com.attendease.app.ui.admin.teachers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.attendease.app.utils.Routes
import com.attendease.app.ui.theme.*

@Composable
fun AdminTeachersScreen(onLogout: () -> Unit, onNavigate: (String) -> Unit) {
    val teachers = listOf(
        Triple("Prof. Sarah Johnson", "Mathematics · sarah@school.edu", "4 classes"),
        Triple("Dr. Ahmed Benali",    "Physics · ahmed@school.edu",     "3 classes"),
        Triple("Ms. Fatima Zohra",    "Chemistry · fatima@school.edu",  "2 classes"),
    )

    Scaffold(bottomBar = { AdminBottomNav(0, onNavigate, onLogout) }) { padding ->
        LazyColumn(modifier = Modifier.fillMaxSize().padding(padding)) {
            item {
                Box(
                    modifier = Modifier.fillMaxWidth()
                        .background(Brush.linearGradient(listOf(BluePrimary, PurpleMid, PurpleAccent)))
                        .padding(top = 48.dp, start = 24.dp, end = 24.dp, bottom = 28.dp)
                ) {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.Top) {
                        Column {
                            Text("Teachers",          color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.ExtraBold)
                            Text("Faculty management", color = Color.White.copy(.75f), fontSize = 13.sp)
                        }
                        Box(
                            modifier = Modifier.size(44.dp).clip(RoundedCornerShape(50)).background(Color.White.copy(.25f)),
                            contentAlignment = Alignment.Center
                        ) { Icon(Icons.Default.Shield, null, tint = Color.White, modifier = Modifier.size(22.dp)) }
                    }
                }
            }
            item { Text("${teachers.size} Teachers", modifier = Modifier.padding(start = 20.dp, top = 20.dp, bottom = 12.dp), fontSize = 16.sp, fontWeight = FontWeight.Bold) }
            items(teachers) { (name, sub, badge) ->
                AdminListItem(initials = name.split(" ").take(2).map { it.first() }.joinToString(""), title = name, subtitle = sub, badge = badge)
            }
            item { Spacer(Modifier.height(16.dp)) }
        }
    }
}

@Composable
fun AdminListItem(initials: String, title: String, subtitle: String, badge: String) {
    Card(modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 5.dp), shape = RoundedCornerShape(14.dp), colors = CardDefaults.cardColors(containerColor = Color.White), elevation = CardDefaults.cardElevation(1.dp)) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(14.dp)) {
            Box(
                modifier = Modifier.size(44.dp).clip(RoundedCornerShape(12.dp))
                    .background(Brush.linearGradient(listOf(BluePrimary, PurpleAccent))),
                contentAlignment = Alignment.Center
            ) { Text(initials, color = Color.White, fontWeight = FontWeight.ExtraBold, fontSize = 15.sp) }
            Column(Modifier.weight(1f)) {
                Text(title,    fontWeight = FontWeight.Bold, fontSize = 14.sp)
                Text(subtitle, color = TextMuted, fontSize = 12.sp, modifier = Modifier.padding(top = 2.dp))
            }
            Surface(shape = RoundedCornerShape(20.dp), color = PurpleBg) {
                Text(badge, modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp), color = BluePrimary, fontSize = 11.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun AdminBottomNav(currentTab: Int, onNavigate: (String) -> Unit, onLogout: () -> Unit) {
    NavigationBar(containerColor = Color.White, tonalElevation = 8.dp) {
        NavigationBarItem(selected = currentTab == 0, onClick = { onNavigate(Routes.ADMIN_TEACHERS) }, icon = { Icon(Icons.Default.Group, null) },    label = { Text("Teachers") })
        NavigationBarItem(selected = currentTab == 1, onClick = { onNavigate(Routes.ADMIN_CLASSES)  }, icon = { Icon(Icons.Default.MenuBook, null) },  label = { Text("Classes") })
        NavigationBarItem(selected = currentTab == 2, onClick = { onNavigate(Routes.ADMIN_STUDENTS) }, icon = { Icon(Icons.Default.Person, null) },    label = { Text("Students") })
        NavigationBarItem(selected = currentTab == 3, onClick = { onNavigate(Routes.ADMIN_STATS)    }, icon = { Icon(Icons.Default.BarChart, null) },  label = { Text("Stats") })
        NavigationBarItem(selected = false,            onClick = onLogout,                             icon = { Icon(Icons.Default.Logout, null, tint = RedDanger) }, label = { Text("Logout", color = RedDanger) })
    }
}
