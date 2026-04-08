package com.attendease.app.utils;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\r\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J&\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lcom/attendease/app/utils/Routes;", "", "()V", "ADMIN_CLASSES", "", "ADMIN_STATS", "ADMIN_STUDENTS", "ADMIN_TEACHERS", "LOGIN", "SCANNER", "TEACHER_DASH", "TEACHER_STATS", "scannerRoute", "courseFirestoreId", "courseName", "courseRoom", "courseTime", "app_debug"})
public final class Routes {
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String LOGIN = "login";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String TEACHER_DASH = "teacher_dashboard";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String TEACHER_STATS = "teacher_stats";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ADMIN_TEACHERS = "admin_teachers";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ADMIN_CLASSES = "admin_classes";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ADMIN_STUDENTS = "admin_students";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ADMIN_STATS = "admin_stats";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SCANNER = "scanner/{courseFirestoreId}/{courseName}/{courseRoom}/{courseTime}";
    @org.jetbrains.annotations.NotNull()
    public static final com.attendease.app.utils.Routes INSTANCE = null;
    
    private Routes() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String scannerRoute(@org.jetbrains.annotations.NotNull()
    java.lang.String courseFirestoreId, @org.jetbrains.annotations.NotNull()
    java.lang.String courseName, @org.jetbrains.annotations.NotNull()
    java.lang.String courseRoom, @org.jetbrains.annotations.NotNull()
    java.lang.String courseTime) {
        return null;
    }
}