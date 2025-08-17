package ru.ohayo.moneypr.ui.component.customeKeyboard


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.ohayo.moneypr.R

@Composable
fun ArrowButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    enabled: Boolean = true,
    backgroundColor: Color = colorScheme.tertiary,
    shape: Shape = RoundedCornerShape(2.dp),
    icon: Painter = painterResource(id = R.drawable.ic_default_arrow_back),
    iconColor: Color = colorScheme.onPrimary
) {

        Box(
            modifier = modifier
                .padding(2.dp)
                .height(46.dp)
                .clip(shape)
                .clickable(enabled = enabled, onClick = onClick)
                .background(
                    color = backgroundColor ,
                    shape = shape
                ),
            contentAlignment = Alignment.Center
        ) {
        Icon(
            painter = icon,
            contentDescription = null,
            tint = iconColor,
            modifier = Modifier
                .size(22.dp)
                .rotate(-90f) // Поворот на 90 градусов влево
        )
    }
}