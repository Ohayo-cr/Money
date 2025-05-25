package ru.ohayo.moneypr.ui.theme.screens.components.componentsCategory

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun CategoryIcon(
    iconResId: Int,
    backgroundColor: Color,
    onClick: (() -> Unit)? = null,
    isSelected: Boolean = false,
    scale: Float = 1f
) {
    val isPicture = IconTypeMapper.isNoTint(iconResId)
    val iconTint = if (!isPicture) Color.White else Color.Unspecified

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
            .padding(if (isPicture) 0.dp else 8.dp)
    ) {
        Image(
            painter = painterResource(id = iconResId),
            contentDescription = null,
            colorFilter = if (isPicture) null else ColorFilter.tint(iconTint),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .matchParentSize()
                .clip(RoundedCornerShape(percent = 20))
        )
    }
}