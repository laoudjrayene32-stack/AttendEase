package com.attendease.app.ui.teacher.scanner;

import androidx.lifecycle.ViewModel;
import com.attendease.app.data.model.QrScanResult;
import com.attendease.app.data.repository.AttendanceRepository;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.StateFlow;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B7\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u00a2\u0006\u0002\u0010\nJ\t\u0010\u0013\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007H\u00c6\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\tH\u00c6\u0003J;\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\tH\u00c6\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001b\u001a\u00020\u001cH\u00d6\u0001J\t\u0010\u001d\u001a\u00020\tH\u00d6\u0001R\u0013\u0010\b\u001a\u0004\u0018\u00010\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012\u00a8\u0006\u001e"}, d2 = {"Lcom/attendease/app/ui/teacher/scanner/ScannerUiState;", "", "phase", "Lcom/attendease/app/ui/teacher/scanner/ScanPhase;", "result", "Lcom/attendease/app/data/model/QrScanResult;", "scannedList", "", "error", "", "(Lcom/attendease/app/ui/teacher/scanner/ScanPhase;Lcom/attendease/app/data/model/QrScanResult;Ljava/util/List;Ljava/lang/String;)V", "getError", "()Ljava/lang/String;", "getPhase", "()Lcom/attendease/app/ui/teacher/scanner/ScanPhase;", "getResult", "()Lcom/attendease/app/data/model/QrScanResult;", "getScannedList", "()Ljava/util/List;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"})
public final class ScannerUiState {
    @org.jetbrains.annotations.NotNull()
    private final com.attendease.app.ui.teacher.scanner.ScanPhase phase = null;
    @org.jetbrains.annotations.Nullable()
    private final com.attendease.app.data.model.QrScanResult result = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.attendease.app.data.model.QrScanResult> scannedList = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String error = null;
    
    public ScannerUiState(@org.jetbrains.annotations.NotNull()
    com.attendease.app.ui.teacher.scanner.ScanPhase phase, @org.jetbrains.annotations.Nullable()
    com.attendease.app.data.model.QrScanResult result, @org.jetbrains.annotations.NotNull()
    java.util.List<com.attendease.app.data.model.QrScanResult> scannedList, @org.jetbrains.annotations.Nullable()
    java.lang.String error) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.attendease.app.ui.teacher.scanner.ScanPhase getPhase() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.attendease.app.data.model.QrScanResult getResult() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.attendease.app.data.model.QrScanResult> getScannedList() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getError() {
        return null;
    }
    
    public ScannerUiState() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.attendease.app.ui.teacher.scanner.ScanPhase component1() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.attendease.app.data.model.QrScanResult component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.attendease.app.data.model.QrScanResult> component3() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.attendease.app.ui.teacher.scanner.ScannerUiState copy(@org.jetbrains.annotations.NotNull()
    com.attendease.app.ui.teacher.scanner.ScanPhase phase, @org.jetbrains.annotations.Nullable()
    com.attendease.app.data.model.QrScanResult result, @org.jetbrains.annotations.NotNull()
    java.util.List<com.attendease.app.data.model.QrScanResult> scannedList, @org.jetbrains.annotations.Nullable()
    java.lang.String error) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}