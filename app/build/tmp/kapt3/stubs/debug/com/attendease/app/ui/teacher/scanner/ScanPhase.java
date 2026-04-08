package com.attendease.app.ui.teacher.scanner;

import androidx.lifecycle.ViewModel;
import com.attendease.app.data.model.QrScanResult;
import com.attendease.app.data.repository.AttendanceRepository;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.StateFlow;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005\u00a8\u0006\u0006"}, d2 = {"Lcom/attendease/app/ui/teacher/scanner/ScanPhase;", "", "(Ljava/lang/String;I)V", "IDLE", "SCANNING", "RESULT", "app_debug"})
public enum ScanPhase {
    /*public static final*/ IDLE /* = new IDLE() */,
    /*public static final*/ SCANNING /* = new SCANNING() */,
    /*public static final*/ RESULT /* = new RESULT() */;
    
    ScanPhase() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public static kotlin.enums.EnumEntries<com.attendease.app.ui.teacher.scanner.ScanPhase> getEntries() {
        return null;
    }
}