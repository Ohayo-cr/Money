package ru.ohayo.moneypr.ui.theme.screens.components.customeKeyboard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun KeyboardButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    enabled: Boolean = true,
    backgroundColor: Color = colorScheme.tertiary,
    shape: Shape = RoundedCornerShape(2.dp)
) {
    Box(
        modifier = modifier
            .padding(2.dp)
            .height(46.dp)
            .clip(shape)
            .clickable(enabled = enabled, onClick = onClick)
            .background(
                color = if (enabled) backgroundColor else colorScheme.onSurface.copy(alpha = 0.12f),
                shape = shape
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = if (enabled) colorScheme.onTertiary else colorScheme.onSurface,
            textAlign = TextAlign.Center,
            fontSize = when (text) {
                "OK" -> 16.sp
                else -> 26.sp
            },
            fontWeight = FontWeight.Bold
        )
    }
}
