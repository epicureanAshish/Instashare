package com.app.instashare.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.instashare.R

//Dashboard
@Composable
fun DashboardView(navController: NavHostController) {
    val bottomNavController = rememberNavController()
    Scaffold(
        content = {
            NavHost(
                modifier = Modifier.padding(it),
                navController = bottomNavController, startDestination = "home",
            ){
                composable("home") {
                    HomeView()
                }
                composable("explore") {
                    ExploreView()
                }
                composable("reels") {
                    ReelsView()
                }
                composable("profile") {
                    ProfileView()
                }
            }
        },
                bottomBar = {
            BottomNavigationBarComponent(bottomNavController)
        },

        )

}

data class IconsData(
    val activeIcon: Int,
    val inactiveIcon: Int
)

@Composable
fun BottomNavigationBarComponent(navController: NavHostController) {
    val selectedItem = remember { mutableStateOf(0) }
    val items = listOf(
        IconsData(
            inactiveIcon = R.drawable.home_outlined,
            activeIcon = R.drawable.home_filled,
        ),
        IconsData(
            inactiveIcon = R.drawable.search_outlined,
            activeIcon = R.drawable.search_filled,
        ),
        IconsData(
            inactiveIcon = R.drawable.add_outlined,
            activeIcon = R.drawable.add_filled,
        ),
        IconsData(
            inactiveIcon = R.drawable.reel_outlined,
            activeIcon = R.drawable.reel_filled,
        ),
        IconsData(
            inactiveIcon = R.drawable.instagram_logo,
            activeIcon = R.drawable.instagram_logo,
        ),
    )
    BottomNavigation(
        backgroundColor = Color.White
    ) {
        items.forEachIndexed { index, s ->
            BottomNavigationItem(
                icon = {
                    Image(
                        painter = painterResource(
                            if (selectedItem.value == index) s.activeIcon else s.inactiveIcon
                        ),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier =
                        if (selectedItem.value == index && index == 4)
                            Modifier
                                .size(25.dp)
                                .clip(shape = CircleShape)
                                .border(width = 2.dp, color = Color.Black)
                        else if (index == 4)
                            Modifier
                                .size(25.dp)
                                .clip(shape = CircleShape)
                        else
                            Modifier
                                .size(25.dp)

                    )
                },
                selected = selectedItem.value == index,
                onClick = {
                    selectedItem.value = index
                    when(index){
                        1 -> navController.navigate("explore")
                        3 -> navController.navigate("reels")
                        4 -> navController.navigate("profile")
                        else -> navController.navigate("home")
                    }
                })
        }
    }
}
