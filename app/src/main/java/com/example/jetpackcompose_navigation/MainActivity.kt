package com.example.jetpackcompose_navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.jetpackcompose_navigation.ui.theme.JetpackCompose_navigationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackCompose_navigationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                )
                {
                    DisplayNav()
                }
            }
        }
    }
}

@Composable
fun DisplayNav() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Destinations.FirstScreen.toString(),
    ) {
        composable(route = Destinations.FirstScreen.toString()) {
            FirstScreen(navController = navController)
        }
        composable(route = Destinations.SecondScreen.toString() + "/{username}") {
            val user = it.arguments?.getString("username")
            SecondScreen(user, navController = navController)
        }
        composable("profile/{userID}",
            arguments = listOf(navArgument("userID") {
                type = NavType.StringType
                defaultValue = "0"
            })
        )
        {
            val user = it.arguments?.getString("userID","0")!! // !! is useed to remove error, we can do user: String? = null also in thirdscreen function arguments
            ThirdScreen(
                userId = user,
                navController = navController
            )
        }
    }
}

@Composable
fun FirstScreen(navController: NavController) {
    var username by remember {
        mutableStateOf("")
    }
    Column() {
        Text(
            text = "Welcome to First Screen",
            fontSize = 30.sp,
            color = Color.Red,
            lineHeight = 50.sp
        )
        TextField(value = username,
            onValueChange = { newUser -> username = newUser })

        Button(onClick = { navController.navigate(Destinations.SecondScreen.toString() + "/$username") }) {
            Text(text = "Go to second screen")
        }
    }
}

@Composable
fun SecondScreen(user: String? = null, navController: NavController) {
    Column() {
        Text(
            text = "Welcome $user, to second screen",
            fontSize = 30.sp,
            color = Color.Green,
            lineHeight = 50.sp
        )
//        Button(onClick = { navController.navigate(Destinations.FirstScreen.toString()) }) {
//            Text(text = "Go to First Screen")

        Button(onClick = { navController.navigate("profile/77") }) {
            Text(text = "Go to Third screen")

        }
    }

}

@Composable
fun ThirdScreen(userId: String, navController: NavController) {
    Text(text = "UserId is $userId")
}

