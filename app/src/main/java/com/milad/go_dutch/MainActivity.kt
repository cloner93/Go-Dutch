package com.milad.go_dutch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.milad.go_dutch.data.groupList
import com.milad.go_dutch.ui.theme.GoDutchTheme
import com.milad.go_dutch.view.CreateGroupScreen
import com.milad.go_dutch.view.HomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GoDutchTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "group") {
                        composable("group") { HomeScreen(navController, groupList) }
                        composable("createGroup") { CreateGroupScreen(navController) }
                    }
                }
            }
        }
    }

}