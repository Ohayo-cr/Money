package ru.ohayo.moneypr.ui.theme.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp


@Composable
fun ChooseCategory(
    iconResId: Int,
    backgroundColor: Color,
    name: String? = null,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .size(90.dp)
            .clickable(onClick = onClick)
            .padding(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(54.dp)
                .background(
                    color = backgroundColor,
                    shape = CircleShape
                )
                .border(
                    width = 1.dp,
                    color = Color.Black,
                    shape = CircleShape
                )
                .padding(8.dp)
        ) {
            Icon(
                painter = painterResource(id = iconResId),
                contentDescription = null,
                tint = Color.Black
            )
        }
        name?.let {
            Text(
                text = it,
                modifier = Modifier.padding(top = 4.dp),
                maxLines = 1
            )
        }
    }
}