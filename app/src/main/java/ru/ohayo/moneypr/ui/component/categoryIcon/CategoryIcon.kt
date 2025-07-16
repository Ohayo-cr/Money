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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.ohayo.moneypr.models.categoryIcon.IconTypeMapper

@Composable
fun CategoryIcon(
    iconResId: Int,
    backgroundColor: Color,
    modifier: Modifier = Modifier.size(60.dp),
    onClick: (() -> Unit)? = null,
    isSelected: Boolean = false,
    scale: Float = 1f,
    padding: Dp = 8.dp,
) {
    val isPicture = IconTypeMapper.isNoTint(iconResId)
    val iconTint = if (!isPicture) Color.White else Color.Unspecified
    val shape = RoundedCornerShape(percent = 20)

    Box(
        modifier = modifier
            .scale(scale)
            .background(
                color = backgroundColor,
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