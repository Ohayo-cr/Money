package ru.ohayo.moneypr.ui.component.top_app_panel

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ru.ohayo.moneypr.ui.component.customeButton.BackButton


@Composable
fun TopAppPanel(
    title: String,
    leftIcon1: Painter? = null,
    onIconClick1: (() -> Unit)? = null,
    rightIcon2: Painter? = null,
    onIconClick2: (() -> Unit)? = null,
    showBackButton: Boolean = false,
    navController: NavController? = null,
    noClip: Boolean = false,
    modifier: Modifier = Modifier
) {
    val shape = RoundedCornerShape(
        topStart = 0.dp,
        topEnd = 0.dp,
        bottomStart = 8.dp,
        bottomEnd = 8.dp
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .let { if (!noClip) it.clip(shape) else it }
            .background(MaterialTheme.colorScheme.primary)
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        // ЛЕВАЯ ЧАСТЬ — кнопка назад или пустое место
        if (showBackButton && navController != null) {
            BackButton(navController)
        }

        // ЦЕНТР — заголовок (всегда по центру!)
        Text(
            text = title,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 8.dp), // внутренние отступы текста
            style = MaterialTheme.typography.titleMedium.copy(
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            ),
            color = MaterialTheme.colorScheme.onBackground,
            maxLines = 1,
            textAlign = TextAlign.Center
        )

        // ПРАВАЯ ЧАСТЬ — иконки
        Row(
            modifier = Modifier.align(Alignment.CenterEnd),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            if (leftIcon1 != null && onIconClick1 != null) {
                Icon(
                    painter = leftIcon1,
                    contentDescription = "Left icon",
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable(onClick = onIconClick1)

                )
            }

            if (rightIcon2 != null && onIconClick2 != null) {
                Spacer(modifier = Modifier.width(16.dp)) // отступ между иконками
                Icon(
                    painter = rightIcon2,
                    contentDescription = "Right icon",
                    tint = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable(onClick = onIconClick2)

                )

            }
        }
    }
}