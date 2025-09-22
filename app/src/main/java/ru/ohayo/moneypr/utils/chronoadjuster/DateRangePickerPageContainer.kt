package ru.ohayo.moneypr.utils.chronoadjuster

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp



@Composable
fun DateRangePickerPageContainer(content: @Composable BoxScope.() -> Unit) = Box(
    modifier = Modifier
        .fillMaxSize()
        .border(
            2.dp,
            MaterialTheme.colorScheme.onPrimary,
            RoundedCornerShape(50),
        )
    ,
    contentAlignment = Alignment.Center,
    content = content,
)