package com.attendease.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument

import com.attendease.app.ui.login.LoginScreen
import com.attendease.app.ui.teacher.dashboard.TeacherDashboardScreen
import com.attendease.app.ui.teacher.scanner.ScannerScreen
import com.attendease.app.ui.teacher.statistics.TeacherStatsScreen
import com.attendease.app.ui.admin.teachers.AdminTeachersScreen
import com.attendease.app.ui.admin.classes.AdminClassesScreen
import com.attendease.app.ui.admin.students.AdminStudentsScreen
import com.attendease.app.ui.admin.statistics.AdminStatsScreen
import com.attendease.app.utils.Routes

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.LOGIN
    ) {

        // LOGIN
        composable(Routes.LOGIN) {
            LoginScreen(
                onSuccess = { role ->
                    if (role == "TEACHER") {
                        navController.navigate(Routes.TEACHER_DASH)
                    } else {
                        navController.navigate(Routes.ADMIN_TEACHERS)
                    }
                }
            )
        }

        // TEACHER DASH
        composable(Routes.TEACHER_DASH) {
            TeacherDashboardScreen(
                onCourseClick = { course ->
                    navController.navigate(
                        Routes.scannerRoute(
                            course.firestoreId,
                            course.name,
                            course.room,
                            course.time
                        )
                    )
                },
                onLogout = {
                    navController.navigate(Routes.LOGIN) {
                        popUpTo(0) { inclusive = true }
                    }
                },
                onStatsClick = {
                    navController.navigate(Routes.TEACHER_STATS)
                }
            )
        }

        // SCANNER
        composable(
            route = Routes.SCANNER,
            arguments = listOf(
                navArgument("courseFirestoreId") { type = NavType.StringType },
                navArgument("courseName") { type = NavType.StringType },
                navArgument("courseRoom") { type = NavType.StringType },
                navArgument("courseTime") { type = NavType.StringType },
            )
        ) { backStack ->

            val id = backStack.arguments?.getString("courseFirestoreId") ?: ""
            val name = backStack.arguments?.getString("courseName") ?: ""
            val room = backStack.arguments?.getString("courseRoom") ?: ""
            val time = backStack.arguments?.getString("courseTime") ?: ""

            ScannerScreen(
                courseFirestoreId = id,
                courseName = name,
                courseRoom = room,
                courseTime = time,
                onBack = { navController.popBackStack() }
            )
        }

        // TEACHER STATS
        composable(Routes.TEACHER_STATS) {
            TeacherStatsScreen(
                onBack = { navController.popBackStack() }
            )
        }

        // ADMIN
        composable(Routes.ADMIN_TEACHERS) {
            AdminTeachersScreen(
                onLogout = {
                    navController.navigate(Routes.LOGIN) {
                        popUpTo(0) { inclusive = true }
                    }
                },
                onNavigate = { navController.navigate(it) }
            )
        }

        composable(Routes.ADMIN_CLASSES) {
            AdminClassesScreen(
                onNavigate = { navController.navigate(it) }
            )
        }

        composable(Routes.ADMIN_STUDENTS) {
            AdminStudentsScreen(
                onNavigate = { navController.navigate(it) }
            )
        }

        composable(Routes.ADMIN_STATS) {
            AdminStatsScreen(
                onNavigate = { navController.navigate(it) }
            )
        }
    }
}