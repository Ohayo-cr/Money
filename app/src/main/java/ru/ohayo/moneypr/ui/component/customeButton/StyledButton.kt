package ru.ohayo.moneypr.ui.component.customeButton

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun StyledButton(
    text: String,
    onClick: () -> Unit,
    color: Color = colorScheme.surface,
    borderColor: Color = Color.Black,
    contentColor: Color = colorScheme.onSurface,
    modifier: Modifier = Modifier
) {
    val buttonShape = RoundedCornerShape(percent = 20)

    Box(
        modifier = modifier
            .clip(buttonShape)
            .background(color)
            .border(BorderStroke(1.dp, borderColor), shape = buttonShape)
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = text, color = contentColor)
    }
}