package com.mypracticeapp.navigation

sealed class Destination(val route: String) {
    object Login : Destination(route = "login")
    object UserList : Destination(route = "userlist")

    companion object {
        fun getStartDestination() = Login.route
    }
}