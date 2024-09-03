package com.example.jetpackcompose_navigation

sealed class Destinations (val route : String) {
    object FirstScreen : Destinations("First Screen")
    object SecondScreen : Destinations("Second Screen")
}