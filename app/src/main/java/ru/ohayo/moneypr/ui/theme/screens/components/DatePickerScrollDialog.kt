package ru.ohayo.moneypr.ui.theme.screens.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import com.commandiron.wheel_picker_compose.WheelDateTimePicker
import com.commandiron.wheel_picker_compose.core.TimeFormat
import com.commandiron.wheel_picker_compose.core.WheelPickerDefaults
import ru.ohayo.moneypr.viewModel.TransactionViewModel
import java.time.LocalDateTime

@Composable
fun DatePickerScrollDialog(
    onDismiss: () -> Unit,
    onDateSelected: (LocalDateTime) -> Unit,
    initialDateTime: LocalDateTime,
) {
    var selectedDateTime = initialDateTime

    Popup(
        onDismissRequest = onDismiss,
        alignment = Alignment.BottomCenter
    ) {
        // Обёртка для фона и внутреннего содержимого
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(1.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(colorScheme.surface)
        ) {

            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)) {

                WheelDateTimePicker(
                    modifier = Modifier.fillMaxWidth(),
                   startDateTime  = initialDateTime,
                    yearsRange = 1900..2130,
                    timeFormat = TimeFormat.HOUR_24,
                    size = DpSize(330.dp, 200.dp),
                    rowCount = 5,
                    textStyle = MaterialTheme.typography.titleMedium.copy(fontSize = 16.sp),
                    textColor = colorScheme.onPrimary,
                    selectorProperties = WheelPickerDefaults.selectorProperties(
                        color = colorScheme.inversePrimary.copy(alpha = 0.2f),
                        border = BorderStroke(2.dp, colorScheme.inversePrimary)
                    ),
                    onSnappedDateTime = { dateTime ->
                        selectedDateTime = dateTime
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                   Button(
                        onClick = onDismiss,
                        modifier = Modifier.padding(end = 8.dp)
                    ) {
                        Text("Отмена")
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .padding(bottom = 20.dp, end = 8.dp)
                            .background(
                                color = colorScheme.inversePrimary,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .clickable {
                                selectedDateTime?.let { date ->
                                    onDateSelected(date)
                                }
                                onDismiss()
                            }
                            .padding(horizontal = 16.dp, vertical = 12.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "OK",
                            color = colorScheme.onPrimary
                        )
                    }
                }
            }
        }
    }
}