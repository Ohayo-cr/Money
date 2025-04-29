package ru.ohayo.moneypr.data.data_source.navigation


import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import kotlinx.coroutines.flow.emptyFlow


@Composable
fun BottomNavigation(navController: NavController) {
    val items = listOf(
        BottomItem.Screen1,
        BottomItem.Screen2,
        BottomItem.Screen4,
        BottomItem.Screen5 // Убираем Screen3 из списка
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp),
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
                .shadow(
                    elevation = 8.dp, // Размер тени
                    shape = MaterialTheme.shapes.large.copy(
                        bottomStart = ZeroCornerSize,
                        bottomEnd = ZeroCornerSize
                    ), // Форма тени
                    clip = false // Отключаем обрезку, чтобы тень была видна
                )
        ) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

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
                        selected = currentRoute == item.route,
                        onClick = {
                            navController.navigate(item.route) {
                                launchSingleTop = true // Не создаем новый экземпляр экрана
                                restoreState = true // Восстанавливаем состояние экрана
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true // Сохраняем состояние текущего экрана
                                    inclusive = false // Оставляем начальный экран в стеке
                                }
                            }
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color.Yellow,
                            unselectedIconColor = Color.DarkGray,
                            selectedTextColor = Color.Yellow,
                            unselectedTextColor = Color.DarkGray,
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
                        selected = currentRoute == item.route,
                        onClick = {
                            navController.navigate(item.route) {
                                launchSingleTop = true
                                restoreState = true
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                    inclusive = false // Оставляем начальный экран в стеке
                                }
                            }
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color.Yellow,
                            unselectedIconColor = Color.DarkGray,
                            selectedTextColor = Color.Yellow,
                            unselectedTextColor = Color.DarkGray,
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
                navController.navigate(BottomItem.Screen3.route) {
                    launchSingleTop = true
                    restoreState = true
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                }
            },
            modifier = Modifier
                .align(Alignment.BottomCenter) // Выравниваем по центру
                .size(60.dp) // Размер кнопки
                .padding(2.dp) // Поднимаем кнопку над NavigationBar
                .clip(CircleShape), // Закругляем углы
            containerColor = Color.Yellow,
            contentColor = Color.Black
        ) {
            Icon(
                painterResource(id = BottomItem.Screen3.iconId),
                contentDescription = "Add",
                tint = Color.Black,
                modifier = Modifier.size(55.dp)
            )
        }
    }
}
// Пустой InteractionSource для отключения эффекта пульсации
private object NoRippleInteractionSource : MutableInteractionSource {
    override val interactions = emptyFlow<Interaction>()
    override suspend fun emit(interaction: Interaction) {}
    override fun tryEmit(interaction: Interaction) = true
}