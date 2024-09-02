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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
fun DisplayNav(){
val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = "First Screen",
         ) {
        composable(route = "First Screen"){
FirstScreen(navController = navController)
        }
        composable(route = "Second Screen"){
            SecondScreen(navController = navController)
        }
    }
}
@Composable
fun FirstScreen(navController:NavController){
    Column() {
        Text(text = "Welcome to First Screen", fontSize = 30.sp, color = Color.Red, lineHeight = 50.sp)
    Button(onClick = { navController.navigate("Second Screen")}) {
       Text(text = "Go to second screen")
    }
    }
}
@Composable
fun SecondScreen(navController:NavController){
Column (){
    Text(text = "Welcome to second screen", fontSize = 30.sp, color = Color.Green, lineHeight = 50.sp)
    Button(onClick = { navController.navigate("First Screen") }) {
        Text(text = "Go to First Screen")
    }
}

}

