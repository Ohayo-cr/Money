package ru.ohayo.moneypr.ui.component.categoryIcon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.ohayo.moneypr.utils.categoryIcon.IconTypeMapper

@Composable
fun CategoryIcon(
    iconResId: Int,
    backgroundColor: Color,
    size: Dp = 60.dp,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    isSelected: Boolean = false,
    scale: Float = 1f,
) {
    val isPicture = IconTypeMapper.isNoTint(iconResId)
    val iconTint = if (!isPicture) Color.White else Color.Unspecified
    val shape = RoundedCornerShape(percent = 20)

    // Пропорциональный паддинг: базовое соотношение 8.dp при 60.dp
    val basePadding = 8.dp
    val baseSize = 60.dp
    val padding = with(LocalDensity.current) {
        val calculatedPadding = basePadding * (size / baseSize)
        calculatedPadding.roundToPx().toDp()
    }

    Box(
        modifier = modifier
            .size(size)
            .scale(scale)
            .background(
                color = if (isPicture) Color.Transparent else backgroundColor,
                shape = shape
            )
            .then(
                if (isSelected) {
                    Modifier.border(
                        width = 2.dp,
                        color = MaterialTheme.colorScheme.inversePrimary,
                        shape = shape
                    )
                } else {
                    Modifier
                }
            )
            .padding(if (isPicture) 0.dp else padding)
    ) {
        Image(
            painter = painterResource(id = iconResId),
            contentDescription = null,
            colorFilter = if (isPicture) null else ColorFilter.tint(iconTint),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .matchParentSize()
                .clip(shape)
        )
    }
}
