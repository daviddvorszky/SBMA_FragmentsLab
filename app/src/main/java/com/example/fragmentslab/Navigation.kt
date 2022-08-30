package com.example.fragmentslab

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.fragmentslab.ui.theme.Screen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(route = Screen.MainScreen.route) {
            MainScreen(navController = navController)
        }
        composable(
            route = Screen.DetailScreen.route + "/{name}",
            arguments = listOf(
                navArgument("name"){
                    type = NavType.StringType
                    defaultValue = "World"
                    nullable = true
                }
            )
        ){ entry ->
            DetailScreen(name = entry.arguments?.getString("name"))
        }
    }
}

@Composable
fun MainScreen(navController: NavController) {
    val presidents = DataProvider.presidents
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        presidents.forEach{president ->
            Text(
                text = president.name,
                fontSize = 20.sp,
                modifier = Modifier
                    .selectable(
                        selected = true,
                        onClick = {
                            navController.navigate(Screen.DetailScreen.withArgs(president.name))
                        }
                    )
            )
        }
    }
}

@Composable
fun DetailScreen(name: String?) {
    val president = DataProvider.getPresidentByName(name ?: "")
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text("Name: ${president.name}", fontSize = 20.sp)
        Text("Start duty: ${president.from}", fontSize = 20.sp)
        Text("End duty: ${president.to}", fontSize = 20.sp)
        Text("Description: ${president.desc}", fontSize = 20.sp)
    }
}