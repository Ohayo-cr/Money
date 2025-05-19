package ru.ohayo.moneypr.ui.theme.screens.components.componentsCategory

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun CategoryIcon(
    iconResId: Int,
    backgroundColor: Color,
    onClick: () -> Unit,
    isSelected: Boolean = false,
    scale: Float = 1f
) {
    val isBackgroundTransparent = backgroundColor.alpha <= 0.1f
    Box(
        modifier = Modifier
            .size(60.dp)
            .scale(scale)
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(percent = 20)
            )
            .border(
                width = if (isSelected) 2.dp else 1.dp,
                color = if (isSelected) MaterialTheme.colorScheme.inversePrimary else Color.Black,
                shape = RoundedCornerShape(percent = 20)
            )
            .padding(if (isBackgroundTransparent) 0.dp else 8.dp)

    ) {
        val iconTint = if (!isBackgroundTransparent) Color.White else Color.Unspecified

        Icon(
            painter = painterResource(id = iconResId),
            contentDescription = null,
            tint = iconTint,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(percent = 20))
        )
    }
}