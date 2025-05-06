package ru.ohayo.moneypr.ui.theme.screens.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.commandiron.wheel_picker_compose.WheelDateTimePicker
import com.commandiron.wheel_picker_compose.core.TimeFormat
import com.commandiron.wheel_picker_compose.core.WheelPickerDefaults
import java.time.LocalDateTime

@Composable
fun DatePickerScroll() {
    var selectedDateTime by remember { mutableStateOf<LocalDateTime?>(null) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 5.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(modifier = Modifier
            .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally) {


            WheelDateTimePicker(
                modifier = Modifier.fillMaxWidth(),
                yearsRange = 1900..2130,
                timeFormat = TimeFormat.HOUR_24,
                size = DpSize(330.dp, 140.dp),
                rowCount = 3,
                textStyle = MaterialTheme.typography.titleMedium.copy(fontSize = 16.sp),
                textColor = Color.DarkGray,
                selectorProperties = WheelPickerDefaults.selectorProperties(
                    color = MaterialTheme.colorScheme.inversePrimary.copy(alpha = 0.2f),
                    border = BorderStroke(2.dp, MaterialTheme.colorScheme.inversePrimary)
                ),
                onSnappedDateTime = { dateTime ->
                    selectedDateTime = dateTime
                    println("Выбрано: $dateTime")
                }
            )


            // Кнопка под WheelDateTimePicker
            Button(
                onClick = {
                    if (selectedDateTime != null) {
                        println("Дата выбрана: $selectedDateTime")
                        // Здесь можно вызвать диалог или другое действие
                    } else {
                        println("Сначала выберите дату")
                    }
                },
                modifier = Modifier
                    .fillMaxWidth(0.8f) // ширина 80% от родительского контейнера
                    .padding(bottom = 20.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.inversePrimary,
                    contentColor = Color.White
                ),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp)
            ) {
                Text(
                    text = "Выбрать дату",
                    fontSize = 18.sp, // больший размер текста
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}