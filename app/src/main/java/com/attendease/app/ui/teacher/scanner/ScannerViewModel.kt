package com.attendease.app.ui.teacher.scanner

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.attendease.app.data.model.QrScanResult
import com.attendease.app.data.repository.AttendanceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class ScanPhase { IDLE, SCANNING, RESULT }

data class ScannerUiState(
    val phase: ScanPhase                = ScanPhase.IDLE,
    val result: QrScanResult?           = null,
    val scannedList: List<QrScanResult> = emptyList(),
    val error: String?                  = null
)

@HiltViewModel
class ScannerViewModel @Inject constructor(
    private val repo: AttendanceRepository
) : ViewModel() {

    private val _state = MutableStateFlow(ScannerUiState())
    val state: StateFlow<ScannerUiState> = _state

    fun onQrScanned(qrCode: String, courseFirestoreId: String) {
        if (_state.value.phase == ScanPhase.SCANNING) return
        _state.value = _state.value.copy(phase = ScanPhase.SCANNING, result = null, error = null)
        viewModelScope.launch {
            try {
                val result = repo.processQrScan(qrCode, courseFirestoreId)
                val newList = if (result.isEnrolled &&
                    _state.value.scannedList.none { it.studentId == result.studentId }) {
                    listOf(result) + _state.value.scannedList
                } else _state.value.scannedList
                _state.value = _state.value.copy(phase = ScanPhase.RESULT, result = result, scannedList = newList)
            } catch (e: Exception) {
                _state.value = _state.value.copy(phase = ScanPhase.IDLE, error = "خطأ: ${e.message}")
            }
        }
    }

    fun resetToIdle() {
        _state.value = _state.value.copy(phase = ScanPhase.IDLE, result = null)
    }
}
