package ru.ohayo.moneypr.ui.component.customeButton


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ru.ohayo.moneypr.R
import ru.ohayo.moneypr.ui.component.others.NoRippleInteractionSource

@Composable
fun BackButton(navController: NavController?) {
    Box(
        modifier = Modifier
            .size(24.dp)
            .clickable(
                interactionSource = NoRippleInteractionSource,
                indication = null
            ) {
                navController?.popBackStack()
            },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_default_arrow_back),
            contentDescription = "Back",
            tint = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.fillMaxSize()
        )
    }
}