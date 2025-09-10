package ru.ohayo.moneypr.ui.screens.addTransaction



import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ru.ohayo.moneypr.data.room.category.CategoryType
import ru.ohayo.moneypr.ui.component.customeTab.CategoryTabRow
import ru.ohayo.moneypr.ui.component.categoryIcon.ChooseCategory
import ru.ohayo.moneypr.ui.navController.Screen
import ru.ohayo.moneypr.ui.screens.addTransaction.componens.AccountSelectSheet
import ru.ohayo.moneypr.ui.screens.addTransaction.componens.AccountSelectionType
import ru.ohayo.moneypr.ui.screens.addTransaction.componens.TransferBox
import ru.ohayo.moneypr.ui.screens.categoryList.CategoryViewModel



@Composable
fun AddTransaction(
    navController: NavController,
    categoryViewModel: CategoryViewModel = hiltViewModel(),
    transactionViewModel: AddTransactionViewModel = hiltViewModel()
) {

    var selectedTab by rememberSaveable { mutableStateOf(CategoryType.Expense) }
    var showAddTransactionForm by rememberSaveable { mutableStateOf(false) }
    var selectedCategoryId by rememberSaveable { mutableStateOf<Long?>(null) }
    val listState = rememberLazyGridState()
    var isFirstSelection by rememberSaveable { mutableStateOf(true) }
    val filteredCategories = categoryViewModel.filterCategoriesByType(selectedTab)
    val screenHeight = LocalConfiguration.current.screenHeightDp
    val bottomPaddingPercentage = 0.5f
    val bottomPadding = (screenHeight * bottomPaddingPercentage).dp



    Column(modifier = Modifier.fillMaxSize()) {

        CategoryTabRow(
            selectedType = selectedTab,
            onTypeSelected = { type ->
                // Сброс состояний только если выбрана другая вкладка
                if (selectedTab != type) {
                    selectedTab = type
                    selectedCategoryId = null
                    showAddTransactionForm = false
                    isFirstSelection = true
                }
            }
        )
        when (selectedTab) {
            CategoryType.Expense, CategoryType.Income -> {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)

                ) {

                    LazyVerticalGrid(
                        state = listState,
                        columns = GridCells.Fixed(4),
                        contentPadding = PaddingValues(
                            top = 20.dp,
                            bottom = bottomPadding
                        ),
                        horizontalArrangement = Arrangement.spacedBy(2.dp),
                        verticalArrangement = Arrangement.spacedBy(2.dp)
                    ) {
                        items(filteredCategories) { category ->
                            ChooseCategory(
                                iconItem = category.iconResId,
                                backgroundColor = Color(category.color),
                                name = category.categoryName,
                                onClick = {
                                    selectedCategoryId = category.id
                                    showAddTransactionForm = true
                                    transactionViewModel.selectCategoryName(category.categoryName)
                                },
                                isSelected = selectedCategoryId == category.id
                            )
                        }
                    }

                    val density = LocalDensity.current
                    LaunchedEffect(key1 = selectedCategoryId) {
                        if (isFirstSelection && showAddTransactionForm) {
                            val index =
                                filteredCategories.indexOfFirst { it.id == selectedCategoryId }
                            if (index != -1) {

                                val bottomPaddingPx = density.run { bottomPadding.roundToPx() }

                                listState.layoutInfo.visibleItemsInfo.getOrNull(0)
                                    ?.let { firstItem ->
                                        val itemSizePx = firstItem.size.height / 2
                                        val visibleHeight = listState.layoutInfo.viewportSize.height

                                        val scrollOffset =
                                            visibleHeight - bottomPaddingPx - itemSizePx

                                        listState.animateScrollToItem(
                                            index = index,
                                            scrollOffset = -scrollOffset
                                        )
                                        isFirstSelection = false
                                    }
                            }
                        }
                    }




                    if (showAddTransactionForm && selectedCategoryId != null) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.BottomCenter)
                        ) {
                            AddTransactionForm(
                                categoryId = selectedCategoryId!!.toInt(),
                                onTransactionAdded = {
                                    navController.navigate(Screen.Records.route) {
                                        popUpTo(0)
                                    }
                                },
                                modifier = Modifier
                                    .align(Alignment.BottomCenter)
                                    .wrapContentHeight() // Чтобы не занимала всё пространство
                            )
                        }
                    }
                }
            }

            CategoryType.AccountTransfer -> {
                var showAccountSelection by remember { mutableStateOf(false) }
                var selectingAccountType by remember { mutableStateOf<AccountSelectionType?>(null) }
                val accounts by transactionViewModel.accounts.collectAsState(initial = emptyList())

                // Получаем значения из ViewModel
                val fromAccount by transactionViewModel.fromAccount
                val toAccount by transactionViewModel.toAccount

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(top = 4.dp, start = 4.dp, end = 4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TransferBox(
                        fromAccount = fromAccount,
                        toAccount = toAccount,
                        onFromAccountClick = {
                            selectingAccountType = AccountSelectionType.From
                            showAccountSelection = true
                        },
                        onToAccountClick = {
                            selectingAccountType = AccountSelectionType.To
                            showAccountSelection = true
                        }
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.End)
                ) {
                    AddTransactionForm(
                        categoryId = 999,
                        onTransactionAdded = {

                            navController.navigate(Screen.Records.route) {
                                popUpTo(0)
                            }
                        },
                        transferMod = true,
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .wrapContentHeight()
                    )
                }

                if (showAccountSelection) {
                    AccountSelectSheet(
                        account = accounts,
                        selectedAccountDbo = when (selectingAccountType) {
                            AccountSelectionType.From -> fromAccount
                            AccountSelectionType.To -> toAccount
                            null -> null
                        },
                        onDismiss = {
                            showAccountSelection = false
                            selectingAccountType = null
                        },
                        onAccountSelected = { selectedAccount ->
                            when (selectingAccountType) {
                                AccountSelectionType.From -> transactionViewModel.selectFromAccount(selectedAccount)
                                AccountSelectionType.To -> transactionViewModel.selectToAccount(selectedAccount)
                                null -> {}
                            }
                            showAccountSelection = false
                            selectingAccountType = null
                        }
                    )
                }




            }
        }
    }
}








