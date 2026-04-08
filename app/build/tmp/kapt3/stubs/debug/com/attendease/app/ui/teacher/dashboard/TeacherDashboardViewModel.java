package com.attendease.app.ui.teacher.dashboard;

import androidx.lifecycle.ViewModel;
import com.attendease.app.data.model.Course;
import com.attendease.app.data.repository.AttendanceRepository;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.*;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0011R\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\n0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0010\u001a\u00020\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013\u00a8\u0006\u0017"}, d2 = {"Lcom/attendease/app/ui/teacher/dashboard/TeacherDashboardViewModel;", "Landroidx/lifecycle/ViewModel;", "repo", "Lcom/attendease/app/data/repository/AttendanceRepository;", "(Lcom/attendease/app/data/repository/AttendanceRepository;)V", "_courses", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "Lcom/attendease/app/data/model/Course;", "_isLoading", "", "courses", "Lkotlinx/coroutines/flow/StateFlow;", "getCourses", "()Lkotlinx/coroutines/flow/StateFlow;", "isLoading", "todayDate", "", "getTodayDate", "()Ljava/lang/String;", "loadCourses", "", "teacherId", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class TeacherDashboardViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.attendease.app.data.repository.AttendanceRepository repo = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.attendease.app.data.model.Course>> _courses = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.attendease.app.data.model.Course>> courses = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _isLoading = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isLoading = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String todayDate = null;
    
    @javax.inject.Inject()
    public TeacherDashboardViewModel(@org.jetbrains.annotations.NotNull()
    com.attendease.app.data.repository.AttendanceRepository repo) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.attendease.app.data.model.Course>> getCourses() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isLoading() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTodayDate() {
        return null;
    }
    
    public final void loadCourses(@org.jetbrains.annotations.NotNull()
    java.lang.String teacherId) {
    }
}