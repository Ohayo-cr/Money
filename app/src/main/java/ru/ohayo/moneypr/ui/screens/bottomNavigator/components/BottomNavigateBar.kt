package ru.ohayo.moneypr.ui.screens.bottomNavigator.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import ru.ohayo.moneypr.ui.screens.bottomNavigator.BottomNavViewModel
import ru.ohayo.moneypr.ui.navController.Screen
import ru.ohayo.moneypr.ui.component.others.NoRippleInteractionSource


@Composable
fun BottomNavigationBar(navController: NavController,
                        viewModel: BottomNavViewModel = hiltViewModel()) {
    val items = listOf(
        BottomItem.Screen1,
        BottomItem.Screen2,
        BottomItem.Screen4,
        BottomItem.Screen5
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val selectedItem by viewModel.selectedItem

    LaunchedEffect(currentRoute) {
        val newSelectedItem = currentRoute ?: items.first().route
        if (newSelectedItem != selectedItem) {
            viewModel.selectItem(newSelectedItem)
        }
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
            .background(colorScheme.surface)

            .height(64.dp),
        contentAlignment = Alignment.BottomEnd
    // Высота NavigationBar + место для FAB
    ) {
        // Нижняя навигация
        NavigationBar(
            contentColor = Color.Transparent,
            containerColor = colorScheme.surface,
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp) // Высота NavigationBar

        ) {


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Первые два элемента слева
                items.take(2).forEach { item ->
                    NavigationBarItem(
                        icon = {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.SpaceEvenly
                            ) {
                                Icon(
                                    painterResource(id = item.iconId),
                                    contentDescription = "icon",
                                    modifier = Modifier
                                        .size(35.dp)
                                        .padding(bottom = 4.dp)
                                )
                                Text(
                                    text = item.title,
                                    fontSize = 10.sp,
                                    modifier = Modifier.padding(top = 2.dp)
                                )
                            }
                        },
                        selected = selectedItem == item.route,
                        onClick = {
                            val newRoute = item.route
                            viewModel.updateNavigation(newRoute) // <-- обновляем флаг до перехода
                            navController.navigate(newRoute) {
                                launchSingleTop = true
                                restoreState = true
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                    inclusive = false
                                }
                            }
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = colorScheme.inversePrimary,
                            unselectedIconColor = colorScheme.onSurface,
                            selectedTextColor = colorScheme.inversePrimary,
                            unselectedTextColor =colorScheme.onSurface,
                            indicatorColor = colorScheme.surface
                        ),
                        interactionSource = remember { NoRippleInteractionSource }
                    )
                }

                // Пропускаем место для центральной кнопки
                Spacer(modifier = Modifier.size(80.dp))

                // Последние два элемента справа
                items.drop(2).forEach { item ->
                    NavigationBarItem(
                        icon = {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.SpaceEvenly
                            ) {
                                Icon(
                                    painterResource(id = item.iconId),
                                    contentDescription = "icon",
                                    modifier = Modifier
                                        .size(35.dp)
                                        .padding(bottom = 4.dp)
                                )
                                Text(
                                    text = item.title,
                                    fontSize = 10.sp,
                                    modifier = Modifier.padding(top = 2.dp)
                                )
                            }
                        },
                        selected = selectedItem == item.route,
                        onClick = {
                            val newRoute = item.route
                            viewModel.updateNavigation(newRoute) // <-- обновляем флаг до перехода
                            navController.navigate(newRoute) {
                                launchSingleTop = true
                                restoreState = true
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                    inclusive = false
                                }
                            }
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = colorScheme.inversePrimary,
                            unselectedIconColor = colorScheme.onSurface,
                            selectedTextColor = colorScheme.inversePrimary,
                            unselectedTextColor =colorScheme.onSurface,
                            indicatorColor = colorScheme.surface
                        ),
                        interactionSource = remember { NoRippleInteractionSource }
                    )
                }
            }
        }

        // Центральная кнопка (выступающая)
        FloatingActionButton(
            onClick = {

                navController.navigate(Screen.AddTransaction.route) {
                    launchSingleTop = true
                    restoreState = true

                }
            },
            modifier = Modifier
                .align(Alignment.BottomCenter) // Выравниваем по центру
                .size(62.dp) // Размер кнопки
                .padding(2.dp) // Поднимаем кнопку над NavigationBar
                .clip(CircleShape), // Закругляем углы
            containerColor = colorScheme.inversePrimary,
            contentColor = Color.Black
        ) {
            Icon(
                painterResource(id = BottomItem.Screen3.iconId),
                contentDescription = "Add",
                tint = colorScheme.onSecondary,
                modifier = Modifier.size(50.dp)
            )
        }
    }
}
