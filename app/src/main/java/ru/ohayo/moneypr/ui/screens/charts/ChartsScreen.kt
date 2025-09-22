package ru.ohayo.moneypr.ui.screens.charts


import androidx.compose.material3.DatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ru.ohayo.moneypr.R
import ru.ohayo.moneypr.ui.screens.charts.components.data.CategorySummaryFromDb
import ru.ohayo.moneypr.ui.component.categoryIcon.CategoryIcon
import ru.ohayo.moneypr.ui.component.spacers.Spacers
import ru.ohayo.moneypr.ui.component.top_app_panel.TopAppPanel
import ru.ohayo.moneypr.ui.screens.charts.components.DonutChartSample
import ru.ohayo.moneypr.ui.screens.charts.components.PeriodSelector
import ru.ohayo.moneypr.ui.screens.charts.components.data.PeriodType
import ru.ohayo.moneypr.utils.formate.NumberFormatter


@Composable
fun ChartsScreen(navController: NavController,
                 viewModel: ChartsVM = hiltViewModel()) {

    val categorySummaries by viewModel.categorySummaries.collectAsState()
    val periodLabel by viewModel.periodLabel.collectAsState()
    val pieChartData by viewModel.pieChartData.collectAsState()
    val periodType by viewModel.periodType.collectAsState()

    var showPeriodDialog by remember { mutableStateOf(false) }


    Column(modifier = Modifier.fillMaxSize()) {
        TopAppPanel(
            title = "Charts Screen",
            navController = navController,
            showBackButton = true,
            noClip = true

        )
        PeriodSelector(
            periodLabel = periodLabel,
            periodType = periodType,
            onPrevClick = { viewModel.prevPeriod() },
            onNextClick = { viewModel.nextPeriod() },
            onPeriodClick = { showPeriodDialog = true }
        )
        if (pieChartData.isEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_default_none),
                    contentDescription = "No data",
                    tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                    modifier = Modifier.size(64.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Нет данных",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
            }
        } else {

            Spacer(modifier = Modifier.height(8.dp))

            DonutChartSample(pieChartData = pieChartData)
            Spacer(modifier = Modifier.height(8.dp))

            // Список категорий
            LazyColumn(modifier = Modifier.padding(horizontal = 8.dp)) {
                items(categorySummaries) { category ->
                    CategoryItem(category = category)
                }
                item {
                    Spacers.Large200()
                }
            }

        }
        // Диалог выбора периода
        if (showPeriodDialog) {
            PeriodSelectionDialog(
                currentPeriodType = periodType,
                onPeriodSelected = { type ->
                    viewModel.setPeriodType(type)
                    showPeriodDialog = false
                },
                onCustomRangeSelected = { start, end ->
                    viewModel.setCustomRange(start, end)
                    showPeriodDialog = false
                },
                onDismiss = { showPeriodDialog = false }
            )
        }
    }
}

@Composable
fun CategoryItem(category: CategorySummaryFromDb) {
val color =  MaterialTheme.colorScheme.onPrimary
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp)
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(category.color).copy(alpha = 0.01f),
                        Color(category.color).copy(alpha = 0.1f),
                        Color(category.color).copy(alpha = 0.4f),
                        Color(category.color).copy(alpha = 0.5f),
                        Color(category.color).copy(alpha = 0.5f),
                        Color(category.color).copy(alpha = 0.3f),
                        Color(category.color).copy(alpha = 0.1f)
                    ),

                ),
                shape = RoundedCornerShape(
                    topStart = 10.dp,
                    bottomStart = 10.dp
                )
            ),
                verticalAlignment = Alignment.CenterVertically
    ) {
        CategoryIcon(iconResId = category.iconResId,
            backgroundColor = Color(category.color),
            modifier = Modifier.size(48.dp) )
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
        ) {
            Text(
                text = category.categoryName,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold,
                color = color
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = category.percentage.let {
                    "%.2f%%".format(it)
                },
                color = color
            )
        }


        Text(
            text = NumberFormatter.format(category.totalAmount),
            modifier = Modifier.padding(end = 8.dp),  color = color)

    }
}
@Composable
fun PeriodSelectionDialog(
    currentPeriodType: PeriodType,
    onPeriodSelected: (PeriodType) -> Unit,
    onCustomRangeSelected: (Long, Long) -> Unit,
    onDismiss: () -> Unit
) {
    var selectedType by remember { mutableStateOf(currentPeriodType) }
    var showDatePicker by remember { mutableStateOf(false) }
    var isStartDateSelected by remember { mutableStateOf(true) }
    var startDate by remember { mutableStateOf(System.currentTimeMillis()) }
    var endDate by remember { mutableStateOf(System.currentTimeMillis()) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Select Period") },
        text = {
            Column {
                PeriodType.entries.forEach { type ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .selectable(
                                selected = (type == selectedType),
                                onClick = {
                                    selectedType = type
                                    if (type != PeriodType.CUSTOM) {
                                        onPeriodSelected(type)
                                        onDismiss()
                                    } else {
                                        showDatePicker = true
                                    }
                                }
                            )
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = (type == selectedType),
                            onClick = {
                                selectedType = type
                                if (type != PeriodType.CUSTOM) {
                                    onPeriodSelected(type)
                                    onDismiss()
                                } else {
                                    showDatePicker = true
                                }
                            }
                        )
                        Text(
                            text = type.name.lowercase().replaceFirstChar { it.uppercase() },
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
            }
        },
        confirmButton = {
            if (selectedType != PeriodType.CUSTOM) {
                Button(
                    onClick = {
                        onPeriodSelected(selectedType)
                        onDismiss()
                    }
                ) {
                    Text("OK")
                }
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )

    // Диалог выбора дат для кастомного периода
    if (showDatePicker) {
        CustomDateRangePicker(
            isStartDateSelected = isStartDateSelected,
            onStartDateSelected = { date ->
                startDate = date
                isStartDateSelected = false
            },
            onEndDateSelected = { date ->
                endDate = date
                if (startDate <= endDate) {
                    onCustomRangeSelected(startDate, endDate)
                    onDismiss()
                } else {
                    // Можно показать ошибку, что дата начала должна быть <= дате окончания
                }
            },
            onDismiss = {
                showDatePicker = false
                onDismiss()
            }
        )
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDateRangePicker(
    isStartDateSelected: Boolean,
    onStartDateSelected: (Long) -> Unit,
    onEndDateSelected: (Long) -> Unit,
    onDismiss: () -> Unit
) {
    val datePickerState = rememberDatePickerState()

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                if (isStartDateSelected) "Select Start Date" else "Select End Date"
            )
        },
        text = {
            DatePicker(state = datePickerState)
        },
        confirmButton = {
            Button(
                onClick = {
                    datePickerState.selectedDateMillis?.let { selectedDate ->
                        if (isStartDateSelected) {
                            onStartDateSelected(selectedDate)
                        } else {
                            onEndDateSelected(selectedDate)
                        }
                    }
                }
            ) {
                Text("Select")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}



