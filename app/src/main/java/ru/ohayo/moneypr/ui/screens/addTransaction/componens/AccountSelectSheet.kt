package ru.ohayo.moneypr.ui.screens.addTransaction.componens


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.ohayo.moneypr.data.room.account.AccountDbo
import ru.ohayo.moneypr.utils.formate.NumberFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountSelectSheet(
    account: List<AccountDbo>,
    selectedAccountDbo: AccountDbo?,
    onDismiss: () -> Unit,
    onAccountSelected: (AccountDbo) -> Unit,
    title: String = "Выберите счет"
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        windowInsets = WindowInsets(0),
        content = {
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 16.dp,
                    start = 8.dp,
                    end = 8.dp,
                    bottom = WindowInsets.safeDrawing.asPaddingValues().calculateBottomPadding() + 16.dp
                )
            ) {
                Text(
                    text = title,
                    style = typography.titleMedium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(12.dp))

                LazyColumn {
                    items(account) { account ->
                        val isSelected = selectedAccountDbo?.id == account.id

                        FilterChip(
                            selected = isSelected,
                            onClick = { onAccountSelected(account) },
                            label = {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(text = account.name, style = typography.bodyLarge)
                                    Text(
                                        text = "${NumberFormatter.format(account.balance)} ${account.currency}",
                                        style = typography.bodySmall
                                    )
                                }
                            },
                            leadingIcon = if (isSelected) {
                                {
                                    Icon(
                                        imageVector = Icons.Default.CheckCircle,
                                        contentDescription = null,
                                        tint = colorScheme.primary
                                    )
                                }
                            } else null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(52.dp)
                                .padding(vertical = 4.dp),

                            colors = FilterChipDefaults.filterChipColors(
                                containerColor = colorScheme.surface,
                                selectedContainerColor = colorScheme.inversePrimary ,
                                labelColor = if (isSelected)
                                    colorScheme.inversePrimary
                                else
                                    colorScheme.onSurface
                            )
                        )

                    }
                    if (account.isEmpty()) {
                        item {
                            Text(
                                text = "Счета не найдены",
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxWidth(),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }


            }

        }

    )
}