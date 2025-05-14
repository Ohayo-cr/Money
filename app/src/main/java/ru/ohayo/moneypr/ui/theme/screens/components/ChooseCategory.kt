package ru.ohayo.moneypr.ui.theme.screens.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay


@Composable
fun ChooseCategory(
    iconResId: Int,
    backgroundColor: Color,
    name: String? = null,
    onClick: () -> Unit,
    isSelected: Boolean
) {
    var isClickAnimating by remember { mutableStateOf(false) }

    val animatedScale by animateFloatAsState(
        targetValue = if (isClickAnimating) 1.1f else 1.0f,
        animationSpec = tween(durationMillis = 500),
        label = ""
    )

    Column(
        modifier = Modifier
            .size(100.dp)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = {
                    onClick()
                    isClickAnimating = true
                }
            )
            .padding(2.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = if (name != null) Arrangement.SpaceEvenly else Arrangement.Center
    ) {
        CategoryIcon(
            iconResId = iconResId,
            backgroundColor = backgroundColor,
            onClick = onClick,
            isSelected = isSelected,
            scale = animatedScale
        )

        if (name != null) {
            Text(
                text = name,
                modifier = Modifier
                    .widthIn(max = 100.dp)
                    .wrapContentWidth(),
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                softWrap = true,
                lineHeight = 14.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = if (isSelected) MaterialTheme.colorScheme.inversePrimary else LocalContentColor.current
            )
        }
    }

    // Анимация после клика
    LaunchedEffect(isClickAnimating) {
        if (isClickAnimating) {
            delay(500L)
            isClickAnimating = false
        }
    }
}