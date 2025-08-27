package ru.ohayo.moneypr.ui.component.top_app_panel

import androidx.compose.foundation.background
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
    iconPainter: Painter? = null,
    onIconClick: (() -> Unit)? = null,
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
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Кнопка "назад" или балансирующий элемент слева
            if (showBackButton && navController != null) {
                BackButton(navController)
            } else if (iconPainter == null || onIconClick == null) {
                Spacer(modifier = Modifier.width(24.dp))
            } else {
                Spacer(modifier = Modifier.width(0.dp))
            }

            // Заголовок по центру
            Text(
                text = title,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp),
                style = MaterialTheme.typography.titleMedium.copy(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                ),
                color = MaterialTheme.colorScheme.onBackground,
                maxLines = 1,
                textAlign = TextAlign.Center
            )

            // Иконка справа (если передана)
            if (iconPainter != null && onIconClick != null) {
                IconButton(
                    onClick = onIconClick,
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        painter = iconPainter,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
            } else {
                Spacer(modifier = Modifier.width(24.dp))
            }
        }
    }
}