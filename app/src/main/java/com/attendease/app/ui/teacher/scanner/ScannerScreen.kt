package com.attendease.app.ui.teacher.scanner

import androidx.compose.foundation.background
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.attendease.app.ui.theme.*

@Composable
fun ScannerScreen(
    courseFirestoreId: String,
    courseName: String,
    courseRoom: String,
    courseTime: String,
    viewModel: ScannerViewModel = hiltViewModel(),
    onBack: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    Scaffold { padding ->
        LazyColumn(modifier = Modifier.fillMaxSize().padding(padding)) {

            // TopBar
            item {
                Box(
                    modifier = Modifier.fillMaxWidth()
                        .background(Brush.linearGradient(listOf(BluePrimary, PurpleMid, PurpleAccent)))
                        .padding(top = 48.dp, start = 24.dp, end = 24.dp, bottom = 28.dp)
                ) {
                    Column {
                        TextButton(onClick = onBack, colors = ButtonDefaults.textButtonColors(contentColor = Color.White.copy(.85f))) {
                            Icon(Icons.Default.ChevronLeft, null, modifier = Modifier.size(18.dp))
                            Text("Back to Classes", fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
                        }
                        Text("QR Scanner", color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.ExtraBold)
                        Text("$courseName · $courseRoom · $courseTime", color = Color.White.copy(.75f), fontSize = 13.sp)
                    }
                }
            }

            // Scanner area
            item {
                Card(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 24.dp),
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(containerColor = BgLight)
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth().padding(40.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        when (state.phase) {
                            ScanPhase.IDLE -> {
                                Box(
                                    modifier = Modifier.size(100.dp).clip(CircleShape).background(Color.White),
                                    contentAlignment = Alignment.Center
                                ) { Icon(Icons.Default.QrCodeScanner, null, tint = TextMuted, modifier = Modifier.size(48.dp)) }
                                Spacer(Modifier.height(20.dp))
                                Text("Ready to Scan", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                                Text("Tap the button below to scan a student QR code", fontSize = 13.sp, color = TextMuted, modifier = Modifier.padding(top = 6.dp))
                            }
                            ScanPhase.SCANNING -> {
                                CircularProgressIndicator(color = BluePrimary, modifier = Modifier.size(64.dp), strokeWidth = 4.dp)
                                Spacer(Modifier.height(20.dp))
                                Text("Scanning...", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                                Text("Hold the QR code steady", fontSize = 13.sp, color = TextMuted, modifier = Modifier.padding(top = 6.dp))
                            }
                            ScanPhase.RESULT -> {}
                        }
                    }
                }
            }

            // Result
            state.result?.let { result ->
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = if (result.isEnrolled) Color(0xFFF0FDF4) else RedBg),
                        border = androidx.compose.foundation.BorderStroke(2.dp, if (result.isEnrolled) GreenSuccess else RedDanger)
                    ) {
                        Row(modifier = Modifier.padding(18.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                            Box(
                                modifier = Modifier.size(40.dp).clip(CircleShape)
                                    .background(if (result.isEnrolled) GreenSuccess else RedDanger),
                                contentAlignment = Alignment.Center
                            ) { Icon(if (result.isEnrolled) Icons.Default.Check else Icons.Default.Close, null, tint = Color.White, modifier = Modifier.size(20.dp)) }
                            Column {
                                Text(result.studentName, fontWeight = FontWeight.Bold, fontSize = 15.sp)
                                Text(
                                    if (result.isEnrolled) "✓ Enrolled — Marked Present (${result.studentId})"
                                    else "✗ Not enrolled in this class",
                                    fontSize = 12.sp, fontWeight = FontWeight.SemiBold,
                                    color = if (result.isEnrolled) GreenSuccess else RedDanger
                                )
                            }
                        }
                    }
                }
            }

            // Button
            item {
                Spacer(Modifier.height(20.dp))
                Button(
                    onClick = {
                        // في التطبيق الحقيقي: اقرأ QR بالكاميرا وابعث الكود هنا
                        // viewModel.onQrScanned(scannedQrCode, courseFirestoreId)
                        // للتجربة نستخدم كود وهمي:
                        viewModel.onQrScanned("QR_STU001", courseFirestoreId)
                    },
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp).height(52.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = BluePrimary),
                    enabled = state.phase != ScanPhase.SCANNING
                ) {
                    Text(
                        when (state.phase) {
                            ScanPhase.SCANNING -> "Scanning..."
                            ScanPhase.RESULT   -> "Scan Next Student"
                            ScanPhase.IDLE     -> "Start Scan"
                        },
                        fontSize = 16.sp, fontWeight = FontWeight.Bold
                    )
                }
            }

            // Scanned list
            if (state.scannedList.isNotEmpty()) {
                item {
                    Text("Scanned Today (${state.scannedList.size})",
                        modifier = Modifier.padding(start = 20.dp, top = 24.dp, bottom = 12.dp),
                        fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }
                items(state.scannedList) { s ->
                    Card(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 5.dp),
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(1.dp)
                    ) {
                        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                            Box(
                                modifier = Modifier.size(44.dp).clip(RoundedCornerShape(12.dp)).background(GreenSuccess),
                                contentAlignment = Alignment.Center
                            ) { Text(s.studentName.split(" ").take(2).map { it.first() }.joinToString(""), color = Color.White, fontWeight = FontWeight.ExtraBold, fontSize = 15.sp) }
                            Column(Modifier.weight(1f)) {
                                Text(s.studentName, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                                Text(s.studentId, fontSize = 12.sp, color = TextMuted)
                            }
                            Text("✓ Present", color = GreenSuccess, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
                item { Spacer(Modifier.height(16.dp)) }
            }
        }
    }
}
