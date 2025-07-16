package ru.ohayo.moneypr.ui.screens.addCategory.components

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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import ru.ohayo.moneypr.R
import ru.ohayo.moneypr.ui.component.categoryIcon.CategoryIcon
import ru.ohayo.moneypr.ui.component.categoryIcon.IconTypeMapper

@Composable
fun ChooseColorCategory(
    iconItem: Int,
    backgroundColor: Color,
    name: String,
    onClick: () -> Unit,
    size: Dp = 100.dp,
) {
    val isPicture = IconTypeMapper.isNoTint(iconItem)
    val validIconResId = if (isPicture && backgroundColor != Color.Transparent) R.drawable.ic_color else iconItem
    Column(
        modifier = Modifier
            .size(size)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = {
                    onClick()
                }
            )
            .padding(2.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        CategoryIcon(
            iconResId = validIconResId,
            backgroundColor = backgroundColor,
            onClick = onClick
        )


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
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }

