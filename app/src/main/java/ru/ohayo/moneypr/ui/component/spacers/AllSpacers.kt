package ru.ohayo.moneypr.ui.component.spacers

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

object Spacers {
    @Composable
    fun Micro2() {
        Spacer(
            modifier = Modifier
                .height(2.dp)
                .fillMaxWidth()
        )
    }
    @Composable
    fun Small8() {
        Spacer(
            modifier = Modifier
                .height(8.dp)
                .fillMaxWidth()
        )
    }

    @Composable
    fun Medium16() {
        Spacer(
            modifier = Modifier
                .height(16.dp)
                .fillMaxWidth()
        )
    }

    @Composable
    fun Large200() {
        Spacer(
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
        )
    }
}