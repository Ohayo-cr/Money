package ru.ohayo.moneypr.ui.screens.addTransaction.componens


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.commandiron.wheel_picker_compose.WheelDateTimePicker
import com.commandiron.wheel_picker_compose.core.TimeFormat
import com.commandiron.wheel_picker_compose.core.WheelPickerDefaults
import ru.ohayo.moneypr.ui.component.customeKeyboard.millisToLocalDateTime
import ru.ohayo.moneypr.ui.component.others.FullWidthButton
import ru.ohayo.moneypr.ui.component.others.StyledButton
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerSheet(
    onDismiss: () -> Unit,
    onDateSelected: (LocalDateTime) -> Unit,
    initialDateTime: LocalDateTime,
    lastSelectedDate: Long? = null
) {
    var selectedDateTime = initialDateTime
    var lastDB = lastSelectedDate
    val lastDBAsLocalDateTime = if (lastDB != null) {
        millisToLocalDateTime(lastDB)
    } else {
        null
    }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        content = {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(1.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(colorScheme.surface)
            ) {


                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp)
                ) {

                    if (lastDBAsLocalDateTime != null) {
                        val formattedDate =
                            lastDBAsLocalDateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"))
                        FullWidthButton(
                            text = "Выбрать последнюю дату: $formattedDate",
                            onClick = {
                                onDateSelected(lastDBAsLocalDateTime)
                                onDismiss()
                            }
                        )
                    }

                    WheelDateTimePicker(
                        modifier = Modifier.fillMaxWidth(),
                        startDateTime = initialDateTime,
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



                    Row(
                        modifier = Modifier.fillMaxWidth()
                            .padding(horizontal = 4.dp),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.Bottom
                    ) {
                        // Кнопка "Отмена"
                        StyledButton(
                            text = "Отмена",
                            onClick = onDismiss,
                            modifier = Modifier
                                .padding(end = 8.dp, bottom = 4.dp)
                                .weight(1f)
                        )

                        // Кнопка "OK"
                        StyledButton(
                            text = "OK",
                            onClick = {
                                selectedDateTime?.let { date ->
                                    onDateSelected(date)
                                }
                                onDismiss()
                            },
                            color = colorScheme.inversePrimary,
                            contentColor = colorScheme.onPrimary,
                            modifier = Modifier
                                .padding(bottom = 4.dp)
                                .weight(2f)
                        )
                    }
                }

            }
            Spacer(modifier = Modifier.height(50.dp))

        }
    )
}



