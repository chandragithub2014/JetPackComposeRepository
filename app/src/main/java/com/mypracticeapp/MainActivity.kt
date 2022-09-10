package com.mypracticeapp

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mypracticeapp.list.DisplayUserList
import com.mypracticeapp.login.Login
import com.mypracticeapp.login.LoginViewModel
import com.mypracticeapp.navigation.Destination
import com.mypracticeapp.ui.theme.MyPracticeAppTheme
////https://www.youtube.com/watch?v=NvIisC97_9Q
class MainActivity : ComponentActivity() {
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            MyPracticeAppTheme {
                AppNavigator()
                }

        }
    }
}


@Composable
fun AppNavigator(){
val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Destination.getStartDestination()
    ) {
        composable(route = Destination.Login.route) {
            Login(navController = navController)
        }

        composable(route = Destination.UserList.route) {
            DisplayUserList()
        }
    }

}

